package kaleidos.piweek.repository;

import io.micronaut.data.repository.PageableRepository;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import kaleidos.piweek.domain.ScheduledTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonRepository extends PageableRepository<Person, Long> {
  
  Optional<Person> findById(@NotNull Long id);
  
  Optional<Person> findByNameAndBoard(String name, Board board);
  
  void deleteById(@NotNull Long id);
  
  List<Person> findAll(@NotNull SortingAndOrderArguments args);
}
