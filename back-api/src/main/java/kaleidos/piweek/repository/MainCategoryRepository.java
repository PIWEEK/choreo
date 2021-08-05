package kaleidos.piweek.repository;

import io.micronaut.data.repository.PageableRepository;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.MainCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MainCategoryRepository extends PageableRepository<MainCategory, Long> {
  
  Optional<MainCategory> findById(@NotNull Long id);
  
  MainCategory save(@NotBlank String name);
  
  MainCategory saveWithException(@NotBlank String name);
  
  void deleteById(@NotNull Long id);
  
  int update(@NotNull Long id, @NotBlank String name);
}
