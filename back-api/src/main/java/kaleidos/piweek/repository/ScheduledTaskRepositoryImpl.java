package kaleidos.piweek.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.repository.common.BaseRepository;
import kaleidos.piweek.utils.DataTransformations;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Singleton
@Repository
public abstract class ScheduledTaskRepositoryImpl extends BaseRepository implements ScheduledTaskRepository {
  
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public ScheduledTaskRepositoryImpl(EntityManager entityManager,
                                     ApplicationConfiguration applicationConfiguration) {
    super(entityManager);
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(getEntityManager()::remove);
  }
  
  public List<ScheduledTask> findAllByTask(LocalDateTime date, Task task, SortingAndOrderArguments args) {
    String qlString = "SELECT st FROM ScheduledTask st WHERE st.task.id = " + task.getId();
  
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY st." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
  
    TypedQuery<ScheduledTask> query = getEntityManager().createQuery(qlString, ScheduledTask.class);
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
    
    TypedQuery<ScheduledTask> query = getEntityManager().createQuery(qlString, ScheduledTask.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
}