package kaleidos.piweek.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kaleidos.piweek.utils.RandomStrings;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phrases")
public class Phrases {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String text;

  public Phrases() {}
  
  public Phrases(String text) {
    this.text = text;
  }
  
  @Override
  public String toString() {
    return "Board{" +
             "text=" + text +
             '}';
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
}
