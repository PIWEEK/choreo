package kaleidos.piweek.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.*;
import kaleidos.piweek.domain.Person;
import kaleidos.piweek.repository.common.BaseRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;


@Singleton
@Repository
@Transactional
public abstract class PersonRepositoryImpl extends BaseRepository implements PersonRepository {
  
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public PersonRepositoryImpl(EntityManager entityManager,
                              ApplicationConfiguration applicationConfiguration) {
    super(entityManager);
    this.applicationConfiguration = applicationConfiguration;
  }
  
  
  
  @Override
  public Optional<Person> findByNameAndBoard(String name, Board board) {
     List<Person> personList = getEntityManager().createQuery(
       "select p from Person p where p.name = :name and p.board.id = :boardId", Person.class)
                      .setParameter("name", name)
                      .setParameter("boardId", board.getId())
                      .getResultList();
    
    if (personList.isEmpty()) {
      return Optional.empty();
    }
  
    return Optional.of(personList.get(0));
  }
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(getEntityManager()::remove);
  }
  
  @ReadOnly
  public List<Person> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT b FROM Person as b";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY b." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<Person> query = getEntityManager().createQuery(qlString, Person.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
}