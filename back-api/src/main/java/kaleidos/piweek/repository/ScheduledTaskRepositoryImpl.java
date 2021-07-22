package kaleidos.piweek.repository;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Singleton
public class ScheduledTaskRepositoryImpl implements ScheduledTaskRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public ScheduledTaskRepositoryImpl(EntityManager entityManager,
                                     ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<ScheduledTask> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(ScheduledTask.class, id));
  }
  
  @Override
  @Transactional
  public ScheduledTask save(@NotNull String name, @NotNull String iconUrl, @NotNull Date scheduled_at, Float duration,
                            Boolean isDone, String notes, Task task) {
    ScheduledTask scheduledTask = new ScheduledTask(name, iconUrl, scheduled_at, duration, isDone, notes, task);
    entityManager.persist(scheduledTask);
    
    return scheduledTask;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<ScheduledTask> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT mt FROM ScheduledTask as mt";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY mt." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<ScheduledTask> query = entityManager.createQuery(qlString, ScheduledTask.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  
  @Transactional
  public int update(@NotNull Long id, @NotNull String name, @NotNull String iconUrl, @NotNull Date scheduled_at, Float duration,
                    Boolean isDone,  String notes, Task task) {
    //TODO: change the query to update all fields
    return entityManager.createQuery("UPDATE ScheduledTask g SET name = :name, iconurl = :iconUrl where id = :id")
             .setParameter("name", name)
             .setParameter("id", id)
             .setParameter("iconUrl", iconUrl)
             .setParameter("duration", duration)
             .executeUpdate();
  }
  
  @Override
  @Transactional
  public ScheduledTask saveWithException(@NotNull String name, @NotNull String iconUrl, @NotNull Date scheduled_at, Float duration,
                                         Boolean isDone,  String notes, Task task) {
    save(name, iconUrl, scheduled_at, duration, isDone, notes, task);
    throw new PersistenceException();
  }
}