package kaleidos.piweek.controller;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainCategory;
import kaleidos.piweek.repository.MainCategoryRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/main-categories")
public class MainCategoryController {
  
  protected final MainCategoryRepository mainCategoryRepository;
  
  public MainCategoryController(MainCategoryRepository mainCategoryRepository) {
    this.mainCategoryRepository = mainCategoryRepository;
  }
  
  @Get("/{id}")
  public MainCategory show(Long id) {
    return mainCategoryRepository
             .findById(id)
             .orElse(null);
  }
  
  @Get(value = "/{?args*}")
  public Iterable<MainCategory> list(@Valid SortingAndOrderArguments args) {
    return mainCategoryRepository.findAll();
  }
  
  protected URI location(Long id) {
    return URI.create("/main-categories/" + id);
  }
  
  protected URI location(MainCategory mainCategory) {
    return location(mainCategory.getId());
  }
}