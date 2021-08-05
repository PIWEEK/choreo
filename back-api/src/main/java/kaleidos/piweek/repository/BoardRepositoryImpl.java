package kaleidos.piweek.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import kaleidos.piweek.repository.common.BaseRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.*;


@Repository
@Transactional
public abstract class BoardRepositoryImpl extends BaseRepository implements BoardRepository {
  
  private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name");
  protected final PersonRepository personRepository;
  
  /**
   * Initializes repository with {@link EntityManager}
   *
   * @param entityManager persistence {@link EntityManager} instance
   * @param personRepository
   */
  public BoardRepositoryImpl(EntityManager entityManager, PersonRepository personRepository) {
    super(entityManager);
    this.personRepository = personRepository;
  }
  
  @ReadOnly
  @Override
  public List<Board> findAllByPinCode(@NotNull String pinCode) {
    return getEntityManager()
        .createQuery("Select b FROM Board as b where pinCode = :pinCode", Board.class)
        .setParameter("pinCode", pinCode)
        .getResultList();
  }
 
  
  @Override
  @Transactional
  public void deleteById(@NotNull Long id) {
    findById(id).ifPresent(getEntityManager()::remove);
  }
  
//  @ReadOnly
//  public List<Board> findAll(@NotNull SortingAndOrderArguments args) {
//    String qlString = "SELECT b FROM Board as b";
//    if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
//      qlString += " ORDER BY b." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
//    }
//    TypedQuery<Board> query = entityManager.createQuery(qlString, Board.class);
//    query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
//    args.getOffset().ifPresent(query::setFirstResult);
//
//    return query.getResultList();
//  }

//  @Override
//  @Transactional
//  public int update(@NotNull Long id, @NotBlank String name, Set<Task> tasks, Set<Person> people) {
//    return entityManager.createQuery("UPDATE Board g SET name = :name, tasks = :tasks, people = :people " +
//                                       "where id = :id")
//             .setParameter("id", id)
//             .setParameter("name", name)
//             .setParameter("tasks", tasks)
//             .setParameter("people", people)
//             .executeUpdate();
//  }
  

  @Override
  public List<Person> setBoardPeople(JSONArray people, Board board) {
    List<Person> boardPeople = new ArrayList<>();
    
    try {
      for (int i = 0; i < people.length(); i++) {
        JSONObject personJson = people.getJSONObject(i);
        String personName = personJson.get("name").toString();
        String personAvatarId = personJson.get("avatarId").toString();
        
        Optional<Person> existingPerson = personRepository.findByNameAndBoard(personName, board);
        
        if (!existingPerson.isPresent()) {
          Person newPerson = new Person(personName, personAvatarId, null, null);
//          personRepository.save(newPerson);
          boardPeople.add(newPerson);
        } else {
          personRepository.delete(existingPerson.get());
        }
      }
      
    } catch (JSONException e) {
      e.printStackTrace();
    }
    
    return boardPeople;
  }
}