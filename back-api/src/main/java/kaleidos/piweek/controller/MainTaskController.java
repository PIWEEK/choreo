package kaleidos.piweek.controller;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.controller.command.MainTasksSaveCommand;
import kaleidos.piweek.controller.command.MainTasksUpdateCommand;
import kaleidos.piweek.domain.MainTask;
import kaleidos.piweek.repository.MainTasksRepository;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/main-tasks")
public class MainTaskController {
  
  protected final MainTasksRepository mainTasksRepository;
  
  public MainTaskController(MainTasksRepository mainTasksRepository) {
    this.mainTasksRepository = mainTasksRepository;
  }
  
  @Get("/{id}")
  public MainTask show(Long id) {
    return mainTasksRepository
             .findById(id)
             .orElse(null);
  }
  
  @Put
  public HttpResponse update(@Body @Valid MainTasksUpdateCommand command) {
    int numberOfEntitiesUpdated = mainTasksRepository.update(command.getId(), command.getName(), command.getIconUrl());
    
    return HttpResponse
             .noContent()
             .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
  }
  
  @Get(value = "/{?args*}")
  public List<MainTask> list(@Valid SortingAndOrderArguments args) {
    return mainTasksRepository.findAll(args);
  }
  
  @Post
  public HttpResponse<MainTask> save(@Body @Valid MainTasksSaveCommand cmd) {
    MainTask genre = mainTasksRepository.save(cmd.getName(), cmd.getIconUrl());
    
    return HttpResponse
             .created(genre)
             .headers(headers -> headers.location(location(genre.getId())));
  }
  
  @Post("/ex")
  public HttpResponse<MainTask> saveExceptions(@Body @Valid MainTasksSaveCommand cmd) {
    try {
      MainTask genre = mainTasksRepository.saveWithException(cmd.getName(), cmd.getIconUrl());
      return HttpResponse
               .created(genre)
               .headers(headers -> headers.location(location(genre.getId())));
    } catch(PersistenceException e) {
      return HttpResponse.noContent();
    }
  }
  
  @Delete("/{id}")
  public HttpResponse delete(Long id) {
    mainTasksRepository.deleteById(id);
    return HttpResponse.noContent();
  }
  
  protected URI location(Long id) {
    return URI.create("/main-tasks/" + id);
  }
  
  protected URI location(MainTask genre) {
    return location(genre.getId());
  }
}