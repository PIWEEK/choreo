package kaleidos.piweek.repository;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BoardRepository {
  
  Optional<Board> findById(@NotNull Long id);
  
  Board save(@NotBlank String name, @NotBlank String pinCode, Set<Task> tasks, Set<User> users);
  
  Board saveWithException(@NotBlank String name, @NotBlank String pinCode, Set<Task> tasks, Set<User> users);
  
  void deleteById(@NotNull Long id);
  
  List<Board> findAll(@NotNull SortingAndOrderArguments args);
  
  int update(@NotNull Long id, @NotBlank String name, Set<Task> tasks, Set<User> users);
}