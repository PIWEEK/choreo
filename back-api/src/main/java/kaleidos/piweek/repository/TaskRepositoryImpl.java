package kaleidos.piweek.repository;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.MainTask;
import kaleidos.piweek.domain.Task;

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
public class TaskRepositoryImpl implements TaskRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public TaskRepositoryImpl(EntityManager entityManager,
                            ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<Task> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(Task.class, id));
  }
  
  @Override
  @Transactional
  public Task save(@NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
                   @NotNull MainTask mainTask, @Nullable String period) {
    if (period == null) {
      period = "1,2,3,4,5,6,7";
    }
    Task task = new Task(name, iconUrl, duration, isRecursive, mainTask, period);
    
    entityManager.persist(task);
    return task;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<Task> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT mt FROM Task as mt";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY mt." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<Task> query = entityManager.createQuery(qlString, Task.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
                    String period) {
    //TODO: change the qery to update all fields
    return entityManager.createQuery("UPDATE Task g SET name = :name, iconurl = :iconUrl where id = :id")
             .setParameter("name", name)
             .setParameter("id", id)
             .setParameter("iconUrl", iconUrl)
             .setParameter("duration", duration)
             .executeUpdate();
  }
  
  @Override
  @Transactional
  public Task saveWithException(@NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
                                @NotNull MainTask mainTask, String period) {
    save(name, iconUrl, duration, isRecursive, mainTask, period);
    throw new PersistenceException();
  }
}