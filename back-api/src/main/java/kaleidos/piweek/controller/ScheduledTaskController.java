package kaleidos.piweek.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.repository.BoardRepository;
import kaleidos.piweek.repository.MainTaskRepository;
import kaleidos.piweek.repository.PersonRepository;
import kaleidos.piweek.repository.TaskRepository;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.net.URI;

@ExecuteOn(TaskExecutors.IO)
@Controller("/boards/{pinCodeId}/scheduledTasks")
public class ScheduledTaskController {
  
  protected final BoardRepository boardRepository;
  
  protected final TaskRepository taskRepository;
  
  public ScheduledTaskController(BoardRepository boardRepository, MainTaskRepository mainTaskRepository, TaskRepository taskRepository, PersonRepository personRepository) {
    this.boardRepository = boardRepository;
    this.taskRepository = taskRepository;
  }
  
  @Get(value = "/{?args*}")
  public Board list(String pinCodeId, @Valid SortingAndOrderArguments args) {
    try {
      return boardRepository
               .findByPinCode(pinCodeId)
               .orElse(null);
    } catch(NoResultException e) {
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