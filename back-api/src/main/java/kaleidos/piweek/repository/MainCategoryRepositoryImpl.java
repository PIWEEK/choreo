package kaleidos.piweek.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainCategory;
import kaleidos.piweek.repository.common.BaseRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Singleton
@Repository
public abstract class MainCategoryRepositoryImpl extends BaseRepository implements MainCategoryRepository {
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public MainCategoryRepositoryImpl(EntityManager entityManager) {
    super(entityManager);;
  }
  
  @Override
  @ReadOnly
  public Optional<MainCategory> findById(@NotNull Long id) {
    return Optional.ofNullable(getEntityManager().find(MainCategory.class, id));
  }
  
  @Override
  @Transactional
  public MainCategory save(@NotBlank String name) {
    MainCategory mainCategory = new MainCategory(name);
    getEntityManager().persist(mainCategory);
    return mainCategory;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(getEntityManager()::remove);
  }
  
  @Override
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name) {
    return getEntityManager().createQuery("UPDATE MainCategory g SET name = :name where id = :id")
             .setParameter("name", name)
             .setParameter("id", id)
             .executeUpdate();
  }
  
  @Override
  @Transactional
  public MainCategory saveWithException(@NotBlank String name) {
    save(name);
    throw new PersistenceException();
  }
}