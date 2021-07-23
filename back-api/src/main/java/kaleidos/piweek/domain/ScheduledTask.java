package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

/**
 * An scheduled board task
 */
@Entity
@Table(name = "scheduled_tasks")
public class ScheduledTask {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String name;
  
  @NotNull
  private String iconUrl;
  
  @NotNull
  private LocalDateTime scheduled_at;
  
  private Boolean isDone;
  
  private String notes;
  
  /**
   * How long does it take the user to finish the scheduled task
   */
  private Float duration;
 
  /**
   *  Day, or days of the week when the task is scheduled
   *  Example: [1,7] would mean every Monday and Sunday; DayOfWeek.of(7) SUNDAY
   */
  private String period;
  
  @OneToOne
  @JoinColumn(name = "task_id", updatable = false, nullable = false)
  private Task task;
  
  @JsonManagedReference
  @ManyToMany(mappedBy = "scheduledTasks", fetch = FetchType.EAGER)
  private Set<Person> assignedPeople = new HashSet<>();
  
  public ScheduledTask() {}
  
  public ScheduledTask(@NotNull String name, @NotNull String iconUrl, @NotNull LocalDateTime scheduled_at, Float duration,
                       Boolean isDone,  String notes, Task task) {
    this.scheduled_at = scheduled_at;
    this.duration = duration;
    this.isDone = isDone;
    this.notes = notes;
    this.task = task;
  }
  
  @Override
  public String toString() {
    return "ScheduledTask{" +
             "id=" + id +
             ", scheduled_at='" + scheduled_at + '\'' +
             ", duration='" + duration + '\'' +
             ", is_done='" + isDone + '\'' +
             ", period='" + period + '\'' +
             ", notes='" + notes + '\'' +
             ", task='" + task + '\'' +
             '}';
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public LocalDateTime getScheduled_at() {
    return scheduled_at;
  }
  
  public void setScheduled_at(LocalDateTime scheduled_at) {
    this.scheduled_at = scheduled_at;
  }
  
  public Boolean getDone() {
    return isDone;
  }
  
  public void setDone(Boolean done) {
    isDone = done;
  }
  
  public String getNotes() {
    return notes;
  }
  
  public void setNotes(String notes) {
    this.notes = notes;
  }
  
  public Float getDuration() {
    return duration;
  }
  
  public void setDuration(Float duration) {
    this.duration = duration;
  }
  
  public String getPeriod() {
    return period;
  }
  
  public void setPeriod(String period) {
    this.period = period;
  }
  
  public Task getTask() {
    return task;
  }
  
  public void setTask(Task task) {
    this.task = task;
  }
  
  public Set<Person> getAssignedPeople() {
    return assignedPeople;
  }
  
  public void setAssignedPeople(Set<Person> assignedPeople) {
    this.assignedPeople = assignedPeople;
  }
}