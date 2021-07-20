package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "main_categories")
public class MainCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER)
  @OrderBy("id")
  @JoinColumn(name="mainCategory_id")
  private Set<MainTask> tasks = new HashSet<>();
  
  public MainCategory() {}
  
  public MainCategory(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return "MainCategory{" +
             "id=" + id +
             ", name='" + name + '\'' +
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
  
  public void setTasks(Set<MainTask> tasks) {
    this.tasks = tasks;
  }
  
  public Set<MainTask> getTasks() {
    return tasks;
  }
}
