package com.spells.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name= "spells")
@Getter
@Setter
@NoArgsConstructor
public class Spell {
  @Id
  private UUID id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String incantation;
  @Column(nullable = false)
  private String effect;
  @Column(nullable = false)
  private Boolean canBeVerbal;
  @Column(nullable = false)
  private String type;
  @Column(nullable = false)
  private String light;
  @Column(nullable = true)
  private String creator;
}