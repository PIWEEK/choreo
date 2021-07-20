package kaleidos.piweek.controller.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public class MainTasksUpdateCommand {
  @NotNull
  private Long id;
  
  @NotBlank
  private String name;
  
  @NotBlank
  private String iconUrl;
  
  public MainTasksUpdateCommand() {
  }
  
  public MainTasksUpdateCommand(Long id, String name) {
    this.id = id;
    this.name = name;
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
  
  public String getIconUrl() {
    return iconUrl;
  }
  
  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }
}
