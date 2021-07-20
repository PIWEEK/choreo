package kaleidos.piweek.repository;

import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainCategory;

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
public class MainCategoryRepositoryImpl implements MainCategoryRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public MainCategoryRepositoryImpl(EntityManager entityManager,
                                    ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<MainCategory> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(MainCategory.class, id));
  }
  
  @Override
  @Transactional
  public MainCategory save(@NotBlank String name) {
    MainCategory mainCategory = new MainCategory(name);
    entityManager.persist(mainCategory);
    return mainCategory;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<MainCategory> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT mc FROM MainCategory as mc";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY mc." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<MainCategory> query = entityManager.createQuery(qlString, MainCategory.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name) {
    return entityManager.createQuery("UPDATE MainCategory g SET name = :name where id = :id")
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