package kaleidos.piweek.utils;

import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class JsonParser {
  
  public static Set<Person> getListOfPerson(JSONArray people, Board board) {
    Set<Person> peopleSet = new HashSet<>();
    
    try {
      for (int i = 0; i < people.length(); i++) {
        JSONObject personJson = people.getJSONObject(i);
        String personName = personJson.get("name").toString();
        String personAvatarId = personJson.get("avatarId").toString();
        Person person = new Person(personName, personAvatarId, board, null);
        peopleSet.add(person);
      }
  
    } catch (JSONException e) {
      e.printStackTrace();
    }
  
    return peopleSet;
  }
}
