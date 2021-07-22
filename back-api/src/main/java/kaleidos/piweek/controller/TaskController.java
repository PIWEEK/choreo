package kaleidos.piweek.controller;

import io.micronaut.http.HttpResponse;
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
@Controller("/boards/{pinCodeId}/tasks")
public class TaskController {
  
  protected final BoardRepository boardRepository;
  
  protected final TaskRepository taskRepository;
  
  public TaskController(BoardRepository boardRepository, MainTaskRepository mainTaskRepository, TaskRepository taskRepository, PersonRepository personRepository) {
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