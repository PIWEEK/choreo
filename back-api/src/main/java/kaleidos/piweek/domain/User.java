package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The task a board may have
 */
@Entity
@Table(name = "tasks")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String iconUrl;
  
  private Float duration;
  
  @JsonBackReference
  @ManyToOne
  private MainCategory mainCategory;
  
  public User() {}
  
  public User(String name, String iconUrl, Float duration) {
    this.name = name;
    this.iconUrl = iconUrl;
    this.duration = duration;
  }
  
  @Override
  public String toString() {
    return "MainTask{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", duration='" + duration + '\'' +
             ", icon_url='" + iconUrl + '\'' +
             ", categoryId='" + mainCategory.getId() + '\'' +
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
  
  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }
  
  public String getIconUrl() {
    return iconUrl;
  }
  
  public Float getDuration() { return duration;
  }
  
  public void setDuration(Float duration) { this.duration = duration;
  }
  
  public MainCategory getMainCategory() {
    return mainCategory;
  }
  
  public void setMainCategory(MainCategory mainCategory) {
    this.mainCategory = mainCategory;
  }
  
}
