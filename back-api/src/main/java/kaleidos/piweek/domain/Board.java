package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kaleidos.piweek.utils.RandomStrings;

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
  private String pinCode = RandomStrings.randomString(5);
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="board_id")
  private Set<Task> tasks = new HashSet<>();
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="board_id")
  private Set<User> users = new HashSet<>();
  
  public Board() {}
  
  public Board(String name, String pinCode, Set<Task> tasks, Set<User> users) {
    this.id = id;
    this.name = name;
    this.pinCode = pinCode;
    this.tasks = tasks;
    this.users = users;
  }
  
  public Board(String name, Set<Task> tasks, Set<User> users) {
    this.name = name;
    this.tasks = tasks;
    this.users = users;
  }
  
  @Override
  public String toString() {
    return "Board{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", tasks='" + tasks + '\'' +
             ", users='" + users + '\'' +
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
}
