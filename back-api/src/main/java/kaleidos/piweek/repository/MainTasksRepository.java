package kaleidos.piweek.repository;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MainTasksRepository {
  
  Optional<MainTask> findById(@NotNull Long id);
  
  MainTask save(@NotBlank String name, String iconUrl, Float duration);
  
  MainTask saveWithException(@NotBlank String name, @NotBlank String iconUrl, Float duration);
  
  void deleteById(@NotNull Long id);
  
  List<MainTask> findAll(@NotNull SortingAndOrderArguments args);
  
  int update(@NotNull Long id, @NotBlank String name, @NotBlank String iconUrl, Float duration);
}
