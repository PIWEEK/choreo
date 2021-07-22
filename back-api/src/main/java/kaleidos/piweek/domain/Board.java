package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boards")
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String pinCode;
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="board_id")
  private Set<Task> tasks = new HashSet<>();
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="board_id")
  private Set<Person> people = new HashSet<>();
  
  public Board() {}
  
  public Board(String name, String pinCode, Set<Task> tasks, Set<Person> people) {
    this.id = id;
    this.name = name;
    this.pinCode = pinCode;
    this.tasks = tasks;
    this.people = people;
  }
  
  public Board(String name, Set<Task> tasks, Set<Person> people) {
    this.name = name;
    this.tasks = tasks;
    this.people = people;
  }
  
  @Override
  public String toString() {
    return "Board{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", pinCode='" + pinCode + '\'' +
             ", people='" + people + '\'' +
             ", tasks='" + tasks + '\'' +
             '}';
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }
  
  public Set<Task> getTasks() {
    return tasks;
  }
  
  public String getPinCode() {
    return pinCode;
  }
  
  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }
  
  public Set<Person> getPeople() {
    return people;
  }
  
  public void setPeople(Set<Person> people) {
    this.people = people;
  }
}
