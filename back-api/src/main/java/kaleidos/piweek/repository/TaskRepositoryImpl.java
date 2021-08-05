package kaleidos.piweek.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.repository.common.BaseRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Singleton
@Transactional
@Repository
public abstract class TaskRepositoryImpl extends BaseRepository implements TaskRepository {
  
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public TaskRepositoryImpl(EntityManager entityManager,
                            ApplicationConfiguration applicationConfiguration) {
    super(entityManager);
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(getEntityManager()::remove);
  }
  
  @ReadOnly
  public List<Task> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT mt FROM Task as mt";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY mt." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<Task> query = getEntityManager().createQuery(qlString, Task.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @ReadOnly
  public Set<Task> findAllNotTodayScheduled(){
    return new HashSet<Task>(getEntityManager().createQuery(
      "select t from Task t FULL JOIN ScheduledTask st ON t.id = st.task.id " +
        "where st.task.id IS NULL OR EXTRACT(DOY FROM st.scheduled_at) != EXTRACT(DOY FROM now())").getResultList());
  }
  
  @Override
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name, String iconUrl, Float duration, Boolean isRecursive,
                    String period) {
    //TODO: change the qery to update all fields
    return getEntityManager().createQuery("UPDATE Task g SET name = :name, iconurl = :iconUrl where id = :id")
             .setParameter("name", name)
             .setParameter("id", id)
             .setParameter("iconUrl", iconUrl)
             .setParameter("duration", duration)
             .executeUpdate();
  }
}