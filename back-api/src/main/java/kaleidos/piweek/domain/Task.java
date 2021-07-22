package kaleidos.piweek.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.*;

/**
 * The task, or a chore, a board may have
 */
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String iconUrl;
  
  private Float duration;
  
  private Boolean isRecursive;
  
  /**
   *  Day, or days of the week when the task is scheduled
   *  Example: "1,7" would mean every Monday and Sunday; DayOfWeek.of(7) SUNDAY
   */
  private String period;
  
  @OneToOne
  @JoinColumn(name = "main_task_id", updatable = false, nullable = false)
  private MainTask mainTask;
  
  public Task() {}
  
  public Task(@NotNull String name, @NotNull String iconUrl, Float duration, Boolean isRecursive, MainTask mainTask,
              String period) {
    this.name = name;
    this.iconUrl = iconUrl;
    this.duration = duration;
    this.isRecursive = isRecursive;
    this.mainTask = mainTask;
    this.period = period;
  }
  
  @Override
  public String toString() {
    return "Task{" +
             "id=" + id +
             ", name='" + name + '\'' +
             ", duration='" + duration + '\'' +
             ", icon_url='" + iconUrl + '\'' +
             ", is_recursive='" + isRecursive + '\'' +
             ", period='" + period + '\'' +
             ", mainTask='" + mainTask + '\'' +
             ", mainCategory='" + mainTask.getMainCategory() + '\'' +
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
  
  public Float getDuration() { return duration; }
  
  public void setDuration(Float duration) { this.duration = duration; }
  
  public MainTask getMainTask() { return mainTask; }
  
  public void setMainTask(MainTask mainTask) { this.mainTask = mainTask; }
  
  public Boolean getRecursive() { return isRecursive; }
  
  public void setRecursive(Boolean recursive) { isRecursive = recursive; }
  
  public List<Integer> getPeriod() {
    Scanner scanner = new Scanner(period).useDelimiter(",");
    List<Integer> list = new ArrayList<>();
    
    while (scanner.hasNextInt()) {
      list.add(scanner.nextInt());
    }
    
    return list;
  }
  
  public List<DayOfWeek> getDayPeriod() {
    Scanner scanner = new Scanner(period).useDelimiter(",");
    List<DayOfWeek> list = new ArrayList<>();
    
    while (scanner.hasNextInt()) {
      list.add(DayOfWeek.of(scanner.nextInt()));
    }
    
    return list;
  }
  
  public void setPeriod(String period) {
    this.period = period;
  }
}