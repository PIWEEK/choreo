package kaleidos.piweek.repository;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import kaleidos.piweek.domain.ScheduledTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonRepository {
  
  Optional<Person> findById(@NotNull Long id);
  
  Person save(@NotBlank String name, @NotBlank String avatarId, @NotBlank Board board,
              Set<ScheduledTask> scheduledTasks);
  
  Set<Person> saveAll(@NotBlank Set<Person> people);
  
  Person saveWithException(@NotBlank String name, @NotBlank String avatarId, @NotBlank Board board,
                           Set<ScheduledTask> scheduledTasks);
  
  void deleteById(@NotNull Long id);
  
  List<Person> findAll(@NotNull SortingAndOrderArguments args);
}
