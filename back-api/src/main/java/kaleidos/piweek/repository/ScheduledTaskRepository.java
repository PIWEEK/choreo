package kaleidos.piweek.repository;

import io.micronaut.data.repository.PageableRepository;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduledTaskRepository extends PageableRepository<ScheduledTask, Long> {
  
  Optional<ScheduledTask> findById(@NotNull Long id);
 
  void deleteById(@NotNull Long id);
  
  List<ScheduledTask> findAllByTask(@NotNull Task task);
  
  List<ScheduledTask> findAllByDate(@NotNull LocalDateTime date, @NotNull Board board, @NotNull SortingAndOrderArguments args);
}
