package kaleidos.piweek.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.controller.command.BoardSaveCommand;
import kaleidos.piweek.cron.TaskScheduler;
import kaleidos.piweek.domain.*;
import kaleidos.piweek.repository.*;
import kaleidos.piweek.utils.RandomStrings;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExecuteOn(TaskExecutors.IO)
@Controller("/boards")
public class BoardController {
  
  protected final BoardRepository boardRepository;
  protected final MainTaskRepository mainTaskRepository;
  protected final TaskRepository taskRepository;
  protected final ScheduledTaskRepository scheduledTaskRepository;
  protected final PersonRepository personRepository;
  protected final TaskScheduler taskScheduler;
  
  public BoardController(BoardRepository boardRepository, MainTaskRepository mainTaskRepository,
                         TaskRepository taskRepository, ScheduledTaskRepository scheduledTaskRepository, PersonRepository personRepository, TaskScheduler taskScheduler) {
    this.boardRepository = boardRepository;
    this.mainTaskRepository = mainTaskRepository;
    this.taskRepository = taskRepository;
    this.scheduledTaskRepository = scheduledTaskRepository;
    this.personRepository = personRepository;
    this.taskScheduler = taskScheduler;
  }
  
  @Get("/{pinCode}")
  public Board show(String pinCode) {
    List<Board> boardList = boardRepository.findAllByPinCode(pinCode);
    if (boardList.size() > 0) {
      return boardList.get(0);
    } else {
      return null;
    }
  }
  
  @Get(value = "/")
  public Iterable<Board> list() {
    return boardRepository.findAll();
  }
  
  @Post
  public HttpResponse<Board> save(@Body @Valid BoardSaveCommand cmd) {
    // TODO: avoid reusing the same pinCode between boards
    String pinCode = RandomStrings.getPinCode();
    
    Set<Task> tasks = new HashSet<>();
    
    for (Long taskId : cmd.getTaskIds()) {
      Optional<MainTask> optMainTask = mainTaskRepository.findById(taskId);
      if (optMainTask.isPresent()) {
        MainTask mainTask = optMainTask.get();
        Task task = new Task(mainTask.getName(), mainTask.getIconUrl(), mainTask.getDuration(), true,
          mainTask, "1,2,3,4,5,6,7");
        taskRepository.save(task);
        tasks.add(task);
      }
    }
  
    Board board = new Board();
    List<Person> people = boardRepository.setBoardPeople(cmd.getPeople(), board);
    personRepository.saveAll(people);
    
    board.setPinCode(pinCode);
    board.setName(cmd.getName());
    board.setPeople(people);
    board.setTasks(tasks);
    boardRepository.save(board);
  
    return HttpResponse
             .created(board)
             .headers(headers -> headers.location(location(board.getId())));
  }
  
  @Patch("/{pinCode}")
  @Transactional
  public HttpResponse<Board> update(@NotBlank String pinCode, @Body @Valid BoardSaveCommand cmd) {
    
    List<Board> boardList = boardRepository.findAllByPinCode(pinCode);
    if (boardList.size() == 0) {
      return HttpResponse.notFound();
    }
    
    Board board = boardList.get(0);
    Set<Task> tasks = new HashSet<>();
    Set<Task> oldBoardTasks = board.getTasks();
    List<Person> oldBoardPeople = board.getPeople();
  
    for (Task oldTask : oldBoardTasks) {
      List<ScheduledTask> oldSchTasks = scheduledTaskRepository.findAllByTask(oldTask);
      for (ScheduledTask oldSchTask : oldSchTasks) {
        scheduledTaskRepository.delete(oldSchTask);
      }
      taskRepository.delete(oldTask);
    }
  
    for (Person oldPerson : oldBoardPeople) {
      personRepository.delete(oldPerson);
    }
    
    for (Long taskId : cmd.getTaskIds()) {
      Optional<MainTask> optMainTask = mainTaskRepository.findById(taskId);
      if (optMainTask.isPresent()) {
        MainTask mainTask = optMainTask.get();
        Task task = new Task(mainTask.getName(), mainTask.getIconUrl(), mainTask.getDuration(), true,
          mainTask, "1,2,3,4,5,6,7");
        taskRepository.save(task);
        tasks.add(task);
      }
    }
  
    List<Person> people = boardRepository.setBoardPeople(cmd.getPeople(), board);
    Iterable<Person> boardPeople = personRepository.saveAll(people);
    
    board.setName(cmd.getName());
    board.setTasks(tasks);
    board.setPeople(people);
    boardRepository.save(board);
   
    return HttpResponse.ok(board);
  }
  
  protected URI location(Long id) {
    return URI.create("/boards/" + id);
  }
  
  protected URI location(Board board) {
    return location(board.getId());
  }
}