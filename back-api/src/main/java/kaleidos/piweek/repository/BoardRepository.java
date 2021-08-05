package kaleidos.piweek.repository;

import io.micronaut.data.repository.PageableRepository;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import org.json.JSONArray;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends PageableRepository<Board, Long> {
  
  Optional<Board> findById(@NotNull Long id);
  
  List<Board> findAllByPinCode(@NotNull String pinCode);
  
  void deleteById(@NotNull Long id);
  
  List<Person> setBoardPeople(JSONArray people, Board board);
}
