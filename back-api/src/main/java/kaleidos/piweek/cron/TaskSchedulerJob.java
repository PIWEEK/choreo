package kaleidos.piweek.cron;

import io.micronaut.scheduling.annotation.Scheduled;

import javax.inject.Singleton;

@Singleton
public class TaskSchedulerJob {
  protected final TaskScheduler taskScheduler;
  
  public TaskSchedulerJob(TaskScheduler taskScheduler) {
    this.taskScheduler = taskScheduler;
  }
  
  @Scheduled(fixedDelay = "60s")
  void execute() {
    taskScheduler.schedule();
  }
}
