package com.spells.api.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.spells.api.dto.SpellDto;
import com.spells.api.entity.Spell;

public interface SpellService {

	List<SpellDto> findAll(Specification<Spell> spec);
	List<SpellDto> createSpell(SpellDto spell);

}
