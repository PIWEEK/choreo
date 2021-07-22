package kaleidos.piweek.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.controller.command.BoardSaveCommand;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.MainTask;
import kaleidos.piweek.domain.Person;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.repository.BoardRepository;
import kaleidos.piweek.repository.MainTaskRepository;
import kaleidos.piweek.repository.PersonRepository;
import kaleidos.piweek.repository.TaskRepository;
import kaleidos.piweek.utils.JsonParser;
import kaleidos.piweek.utils.RandomStrings;

import javax.persistence.NoResultException;
import javax.validation.Valid;
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
  
  protected final PersonRepository personRepository;
  
  public BoardController(BoardRepository boardRepository, MainTaskRepository mainTaskRepository, TaskRepository taskRepository, PersonRepository personRepository) {
    this.boardRepository = boardRepository;
    this.mainTaskRepository = mainTaskRepository;
    this.taskRepository = taskRepository;
    this.personRepository = personRepository;
  }
  
  @Get("/{pinCode}")
  public Board show(String pinCode) {
    try {
      return boardRepository
             .findByPinCode(pinCode)
             .orElse(null);
    } catch(NoResultException e) {
      return null;
    }
  }
  
  @Get(value = "/{?args*}")
  public List<Board> list(@Valid SortingAndOrderArguments args) {
    return boardRepository.findAll(args);
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
        Task task = taskRepository.save(mainTask.getName(), mainTask.getIconUrl(), mainTask.getDuration(),
          true, mainTask, null);
        tasks.add(task);
      }
    }
  
    Board board = boardRepository.save(cmd.getName(), pinCode, tasks, null);
    
    Set<Person> boardPeople = personRepository.saveAll(JsonParser.getListOfPerson(cmd.getPeople(), board));
  
    for(Person person : boardPeople){
      boardRepository.update(board.getId(), board.getName(), board.getTasks(), boardPeople);
    }
  
    return HttpResponse
             .created(board)
             .headers(headers -> headers.location(location(board.getId())));
  }
  
  protected URI location(Long id) {
    return URI.create("/boards/" + id);
  }
  
  protected URI location(Board board) {
    return location(board.getId());
  }
}