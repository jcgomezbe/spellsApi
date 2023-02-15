package com.spells.api.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SpellDto {
	
	  private UUID id;
	  @NotNull
	  private String name;
	  @NotNull
	  private String incantation;
	  @NotNull
	  private String effect;
	  @NotNull
	  private Boolean canBeVerbal;
	  @NotNull
	  private String type;
	  @NotNull
	  private String light;
	  private String creator;
}