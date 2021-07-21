package kaleidos.piweek.controller.command;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class BoardSaveCommand {
  
  @NotBlank
  private String name;
  
  @NotBlank
  private String pinCode;
  
  public BoardSaveCommand() {
  }
  
  public BoardSaveCommand(String name, String pinCode) {
    this.name = name;
    this.pinCode = pinCode;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPinCode() {
    return pinCode;
  }
  
  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }
}