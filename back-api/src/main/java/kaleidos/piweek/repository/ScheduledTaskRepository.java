package kaleidos.piweek.repository;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduledTaskRepository {
  
  Optional<ScheduledTask> findById(@NotNull Long id);
  
  ScheduledTask save(@NotNull String name, @NotNull String iconUrl, @NotNull LocalDateTime scheduled_at, Float duration,
            Boolean isDone,  String notes, Task task);
  
  ScheduledTask saveWithException(@NotNull String name, @NotNull String iconUrl, @NotNull LocalDateTime scheduled_at, Float duration,
                         Boolean isDone,  String notes, Task task);
  
  void deleteById(@NotNull Long id);
  
  List<ScheduledTask> findAll(@NotNull SortingAndOrderArguments args);
  
  List<ScheduledTask> findAllByDate(@NotNull LocalDateTime date, @NotNull Board board, @NotNull SortingAndOrderArguments args);
  
  int update(@NotNull Long id, @NotNull String name, @NotNull String iconUrl, @NotNull LocalDateTime scheduled_at,
             Float duration, Boolean isDone, String notes, Task task);
}
