package com.spells.api.mapper;

import com.spells.api.dto.SpellDto;
import com.spells.api.entity.Spell;

public class SpellMapper {

	public static SpellDto toDto(Spell spell) {
		var spellDto = new SpellDto();
		spellDto.setName(spell.getName());
		spellDto.setCanBeVerbal(spell.getCanBeVerbal());
		spellDto.setCreator(spell.getCreator());
		spellDto.setEffect(spell.getEffect());
		spellDto.setId(spell.getId());
		spellDto.setIncantation(spell.getIncantation());
		spellDto.setLight(spell.getLight());
		spellDto.setType(spell.getType());
		return spellDto;
	};

	public static Spell fromDto(SpellDto spellDto) {
		var spell = new Spell();
		spell.setName(spellDto.getName());
		spell.setCanBeVerbal(spellDto.getCanBeVerbal());
		spell.setCreator(spellDto.getCreator());
		spell.setEffect(spellDto.getEffect());
		spell.setId(spellDto.getId());
		spell.setIncantation(spellDto.getIncantation());
		spell.setLight(spellDto.getLight());
		spell.setType(spellDto.getType());
		return spell; 
	};

}