package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String avatarUrl;
  
  @JsonBackReference
  @ManyToMany
  @JoinTable(
    name = "people_scheduledTask",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "scheduledTask_id"))
  private Set<ScheduledTask> scheduledTasks = new HashSet<>();
  
  @JsonBackReference
  @ManyToOne
  @NotNull
  private Board board;
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="board_id")
  private Set<Person> people = new HashSet<>();
  
  public Person() {}
  
  public Person(@NotBlank String name, @NotBlank String avatarId, @NotBlank Board board, Set<ScheduledTask> scheduledTasks) {
    this.name = name;
    //TODO: extract to a method
    this.avatarUrl = "http://localhost:8080/static/avatars/" + avatarId + ".svg";
    this.scheduledTasks = scheduledTasks;
    this.board = board;
  }
  
  @Override
  public String toString() {
    return "Person{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", avatarUrl='" + avatarUrl + '\'' +
             ", scheduledTasks='" + scheduledTasks + '\'' +
             ", board='" + board + '\'' +
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
  
  public String getAvatarUrl() {
    return avatarUrl;
  }
  
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
  
  public Set<ScheduledTask> getScheduledTasks() {
    return scheduledTasks;
  }
  
  public void setScheduledTasks(Set<ScheduledTask> scheduledTasks) {
    this.scheduledTasks = scheduledTasks;
  }
  
  public Board getBoard() {
    return board;
  }
  
  public void setBoard(Board board) {
    this.board = board;
  }
  
}
