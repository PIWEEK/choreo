package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import javax.validation.constraints.NotNull;


@Entity
@Table(name = "main_tasks")
public class MainTask {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String iconUrl;
  
  @JsonBackReference
  @ManyToOne
  private MainCategory mainCategory;
  
  public MainTask() {}
  
  public MainTask(String name, String iconUrl) {
    this.name = name;
    this.iconUrl = iconUrl;
  }
  
  @Override
  public String toString() {
    return "MainTask{" +
             "id=" + id +
             ", name='" + name + '\'' +
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
  
  public MainCategory getMainCategory() {
    return mainCategory;
  }
  
  public void setMainCategory(MainCategory mainCategory) {
    this.mainCategory = mainCategory;
  }
  
}
