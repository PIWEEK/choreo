package kaleidos.piweek.cron;

import javax.inject.Singleton;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;

import kaleidos.piweek.SortingAndOrderArguments;
import kaleidos.piweek.domain.Board;
import kaleidos.piweek.domain.ScheduledTask;
import kaleidos.piweek.domain.Task;
import kaleidos.piweek.repository.BoardRepository;
import kaleidos.piweek.repository.ScheduledTaskRepository;
import kaleidos.piweek.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Singleton
public class TaskScheduler {

  private static final Logger LOG = LoggerFactory.getLogger(TaskScheduler.class);
  
  protected final BoardRepository boardRepository;
  protected final TaskRepository taskRepository;
  protected final ScheduledTaskRepository scheduledTaskRepository;
  
  public TaskScheduler(BoardRepository boardRepository, TaskRepository taskRepository,
                       ScheduledTaskRepository scheduledTaskRepository) {
    this.boardRepository = boardRepository;
    this.taskRepository = taskRepository;
    this.scheduledTaskRepository = scheduledTaskRepository;
  }
  
  public void schedule() {
    LOG.info("Checking tasks to schedule: {}", new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
    
    try {
      Set<Task> notYetScheduledTasks = taskRepository.findAllNotTodayScheduled();
      List<Long> notYetScheduledTasksIds =  new ArrayList<>();
      for(Task task : notYetScheduledTasks) {
        notYetScheduledTasksIds.add(task.getId());
      }
      boardRepository
        .findAll(new SortingAndOrderArguments())
        .stream()
        .forEach((board) -> {
          String now = new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date());
          LOG.info("    Checking board \"{}\": {}", board.getPinCode(),
            new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date()));
  
          Integer today = ZonedDateTime
                            .now( ZoneId.of( "Europe/Madrid" ))
                            .getDayOfWeek().getValue();
          Set<Task> boardTasks = board.getTasks();;

          for(Task task : boardTasks) {
            if (task.getPeriod().contains(today) && notYetScheduledTasksIds.contains(task.getId())) {
              ScheduledTask scheduledTask = scheduledTaskRepository
                                              .save(task.getName(), task.getIconUrl(), LocalDateTime.now(),
                                                task.getDuration(), false, null, task);
              LOG.info("        Scheduling task{} : {}", task.getId(), now);
              }
          }
        }
      );
    } catch(NoResultException e) {
      LOG.info("An exception has occurred: {}", e.getMessage());
    }
  }
}
