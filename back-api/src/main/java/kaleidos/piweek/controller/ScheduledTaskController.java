package kaleidos.piweek.controller;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.repository.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/boards/{pinCodeId}/scheduledTasks")
public class ScheduledTaskController {
  
  protected final BoardRepository boardRepository;
  protected final TaskRepository taskRepository;
  protected final ScheduledTaskRepository scheduledTaskRepository;
  
  public ScheduledTaskController(BoardRepository boardRepository, MainTaskRepository mainTaskRepository, TaskRepository taskRepository, PersonRepository personRepository, ScheduledTaskRepository scheduledTaskRepository) {
    this.boardRepository = boardRepository;
    this.taskRepository = taskRepository;
    this.scheduledTaskRepository = scheduledTaskRepository;
  }
  
  @Get(value = "/{?args*}")
  public List<ScheduledTask> listDate(@Parameter String pinCodeId,
                                      @NotNull @Format("dd-MM-yyyy'T'HH:mm:ss") LocalDateTime date,
                                      @Valid SortingAndOrderArguments args) {
      
    List<Board> boardList = boardRepository.findAllByPinCode(pinCodeId);
    if (boardList.size() > 0) {
      return scheduledTaskRepository.findAllByDate(date, boardList.get(0), args);
    } else {
      return null;
    }
  }
  
  protected URI location(Long id) {
    return URI.create("/boards/" + id);
  }
  
  protected URI location(Board board) {
    return location(board.getId());
  }
}