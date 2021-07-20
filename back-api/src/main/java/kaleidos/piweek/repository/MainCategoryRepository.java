package kaleidos.piweek.repository;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface MainCategoryRepository {
  
  Optional<MainCategory> findById(@NotNull Long id);
  
  MainCategory save(@NotBlank String name);
  
  MainCategory saveWithException(@NotBlank String name);
  
  void deleteById(@NotNull Long id);
  
  List<MainCategory> findAll(@NotNull SortingAndOrderArguments args);
  
  int update(@NotNull Long id, @NotBlank String name);
}
