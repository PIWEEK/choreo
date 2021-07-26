package kaleidos.piweek.repository;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.utils.DataTransformations;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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
  public ScheduledTask save(@NotNull String name, @NotNull String iconUrl, @NotNull LocalDateTime scheduled_at,
                            Float duration,Boolean isDone, String notes, Task task) {
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
  
  @ReadOnly
  public List<ScheduledTask> findAllByDate(@NotNull LocalDateTime localDate, @NotNull Board board, @NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT st FROM ScheduledTask st WHERE " +
                      "EXTRACT(DOY FROM st.scheduled_at) = " + localDate.getDayOfYear() + " AND " +
                      "EXTRACT(YEAR FROM st.scheduled_at) = " + localDate.getYear();
  
    Set<Task> boardTasks = board.getTasks();
    if (!boardTasks.isEmpty()) {
      List<Long> boardTasksIds = new ArrayList<Long>();
      for (Task task : board.getTasks()) {
        boardTasksIds.add(task.getId());
      }
      qlString += " AND st.task.id IN " + DataTransformations.idListToJpaParam(boardTasksIds);
    }
    
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY st." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    
    TypedQuery<ScheduledTask> query = entityManager.createQuery(qlString, ScheduledTask.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  
  @Transactional
  public int update(@NotNull Long id, @NotNull String name, @NotNull String iconUrl,
                    @NotNull LocalDateTime scheduled_at, Float duration,
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
  public ScheduledTask saveWithException(@NotNull String name, @NotNull String iconUrl,
                                         @NotNull LocalDateTime scheduled_at, Float duration,
                                         Boolean isDone,  String notes, Task task) {
    save(name, iconUrl, scheduled_at, duration, isDone, notes, task);
    throw new PersistenceException();
  }
}