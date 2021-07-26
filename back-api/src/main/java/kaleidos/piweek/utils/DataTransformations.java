package kaleidos.piweek.utils;

import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataTransformations {
  
  public static String idListToJpaParam(List<Long> list) {
    return list.stream()
             .map(n -> String.valueOf(n))
             .collect(Collectors.joining(", ", "(", ")"));
  }
}
