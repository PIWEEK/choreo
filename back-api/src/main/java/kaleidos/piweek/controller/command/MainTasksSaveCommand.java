package kaleidos.piweek.controller.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class MainTasksSaveCommand {
  
  @NotBlank
  private String name;
  
  @NotBlank
  private String iconUrl;
  
  public MainTasksSaveCommand() {
  }
  
  public MainTasksSaveCommand(String name, String iconUrl) {
    this.name = name;
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