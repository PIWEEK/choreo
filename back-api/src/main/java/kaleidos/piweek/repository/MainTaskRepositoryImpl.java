package kaleidos.piweek.repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainCategory;
import kaleidos.piweek.domain.MainTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Singleton
public class MainTaskRepositoryImpl implements MainTasksRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public MainTaskRepositoryImpl(EntityManager entityManager,
                                ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<MainTask> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(MainTask.class, id));
  }
  
  @Override
  @Transactional
  public MainTask save(@NotBlank String name, @NotBlank String iconUrl, Float duration) {
    MainTask mainTask = new MainTask(name, iconUrl, duration);
    entityManager.persist(mainTask);
    return mainTask;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<MainTask> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT mt FROM MainTask as mt";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY mt." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<MainTask> query = entityManager.createQuery(qlString, MainTask.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name, @NotBlank String iconUrl, Float duration) {
    return entityManager.createQuery("UPDATE MainTask g SET name = :name, iconurl = :iconUrl where id = :id")
             .setParameter("name", name)
             .setParameter("id", id)
             .setParameter("iconUrl", iconUrl)
             .setParameter("duration", duration)
             .executeUpdate();
  }
  
  @Override
  @Transactional
  public MainTask saveWithException(@NotBlank String name, String iconUrl, Float duration) {
    save(name, iconUrl, duration);
    throw new PersistenceException();
  }
}