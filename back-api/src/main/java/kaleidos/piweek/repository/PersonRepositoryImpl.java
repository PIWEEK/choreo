package kaleidos.piweek.repository;

import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.ApplicationConfiguration;
import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.*;
import kaleidos.piweek.domain.Person;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;


@Singleton
public class PersonRepositoryImpl implements PersonRepository {
  
  private final EntityManager entityManager;
  private final ApplicationConfiguration applicationConfiguration;
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  
  public PersonRepositoryImpl(EntityManager entityManager,
                              ApplicationConfiguration applicationConfiguration) {
    this.entityManager = entityManager;
    this.applicationConfiguration = applicationConfiguration;
  }
  
  @Override
  
  @ReadOnly
  public Optional<Person> findById(@NotNull Long id) {
    return Optional.ofNullable(entityManager.find(Person.class, id));
  }
  
  @Override
  @Transactional
  public Person save(@NotBlank String name, @NotBlank String avatarId, @NotBlank Board board,
                     Set<ScheduledTask> scheduledTasks) {
    Person person = new Person(name, avatarId, board, scheduledTasks);
    entityManager.persist(person);
    return person;
  }
  
  @Override
  @Transactional
  public Set<Person> saveAll(@NotBlank Set<Person> people) {
    Set<Person> personSet = new HashSet<>();
    for(Person person : people){
      entityManager.persist(person);
    }
    return personSet;
  }
  
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(entityManager::remove);
  }
  
  @ReadOnly
  public List<Person> findAll(@NotNull SortingAndOrderArguments args) {
    String qlString = "SELECT b FROM Person as b";
    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
      qlString += " ORDER BY b." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
    }
    TypedQuery<Person> query = entityManager.createQuery(qlString, Person.class);
    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
    args.getOffset().ifPresent(query::setFirstResult);
    
    return query.getResultList();
  }
  
  @Override
  @Transactional
  public Person saveWithException(@NotBlank String name, @NotBlank String avatarId, @NotBlank Board board,
                                  Set<ScheduledTask> scheduledTasks) {
    save(name, avatarId, board, scheduledTasks);
    throw new PersistenceException();
  }
}