package kaleidos.piweek.repository;

import io.micronaut.core.annotation.Nullable;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainTask;
import kaleidos.piweek.domain.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {
  
  Optional<Task> findById(@NotNull Long id);
  
  Task save(@NotBlank String name, String iconUrl, Float duration, Boolean isRecursive, @NotNull MainTask mainTask,
            @Nullable String period);
  
  Task saveWithException(@NotBlank String name, String iconUrl, Float duration, Boolean isRecursive, @NotNull MainTask mainTask,
                         String period);
  
  void deleteById(@NotNull Long id);
  
  List<Task> findAll(@NotNull SortingAndOrderArguments args);
  
  Set<Task> findAllNotTodayScheduled();
  
  int update(@NotNull Long id, @NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
             String period);
}
