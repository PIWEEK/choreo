package kaleidos.piweek.controller.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.List;

import org.json.*;

@Introspected
public class BoardSaveCommand {
  
  @NotBlank
  private String name;
  
  private List<Long> taskIds;
  
  private JSONArray people;
  
  public BoardSaveCommand() {
  }
  
  public BoardSaveCommand(String name, List<Long> taskIds, JSONArray people) {
    JSONArray jsonArray = null;
    try {
      jsonArray = new JSONArray(people);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  
    this.name = name;
    this.taskIds = taskIds;
    this.people = jsonArray;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<Long> getTaskIds() {
    return taskIds;
  }
  
  public void setTaskIds(List<Long> taskIds) {
    this.taskIds = taskIds;
  }
  
  public JSONArray getPeople() {
    return people;
  }
  
  public void setPeople(JSONArray people) {
    this.people = people;
  }
}

