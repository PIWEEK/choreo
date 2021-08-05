package kaleidos.piweek.repository;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.repository.PageableRepository;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.MainTask;
import kaleidos.piweek.domain.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository extends PageableRepository<Task, Long> {
  
  void deleteById(@NotNull Long id);
  
  Set<Task> findAllNotTodayScheduled();
  
  int update(@NotNull Long id, @NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
             String period);
}
