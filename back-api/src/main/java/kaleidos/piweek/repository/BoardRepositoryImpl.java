package kaleidos.piweek.repository;

import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.domain.User;

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
import java.util.Set;


@Singleton
public class BoardRepositoryImpl implements BoardRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public BoardRepositoryImpl(EntityManager entityManager,
                             ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<Board> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(Board.class, id));
  }
  
  @Override
  @Transactional
  public Board save(@NotBlank String name, @NotBlank String pinCode, Set<Task> tasks, Set<User> users) {
    Board board = new Board(name, pinCode, tasks, users);
    entityManager.persist(board);
    return board;
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<Board> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT b FROM Board as b";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY b." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<Board> query = entityManager.createQuery(qlString, Board.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  @Transactional
  public int update(@NotNull Long id, @NotBlank String name, Set<Task> tasks, Set<User> users) {
    return entityManager.createQuery("UPDATE Board g SET name = :name, tasks = :tasks, users = :users " +
                                       "where id = :id")
             .setParameter("id", id)
             .setParameter("name", name)
             .setParameter("tasks", tasks)
             .setParameter("users", users)
             .executeUpdate();
  }
  
  @Override
  @Transactional
  public Board saveWithException(@NotBlank String name, @NotBlank String pinCode, Set<Task> tasks, Set<User> users) {
    save(name, pinCode, tasks, users);
    throw new PersistenceException();
  }
}