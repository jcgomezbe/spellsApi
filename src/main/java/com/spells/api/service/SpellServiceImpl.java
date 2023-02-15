package com.spells.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spells.api.dto.SpellDto;
import com.spells.api.entity.Spell;
import com.spells.api.mapper.SpellMapper;
import com.spells.api.repository.SpellRepository;

@Service
public class SpellServiceImpl implements SpellService {

	private final SpellRepository spellRepository;

	@Autowired
	public SpellServiceImpl(SpellRepository spellRepository) {
		this.spellRepository = spellRepository;
	}

	@Override
	public List<SpellDto> findAll(Specification<Spell> spec) {
		//Captura cada entidad y retorna el dto mapeado
		return spellRepository.findAll(spec).stream().map(SpellMapper::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<SpellDto> createSpell(SpellDto spell) {

		Optional<Spell> existingSpell = spellRepository.findByNameEquals(spell.getName());
		if (existingSpell.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Spell with name "+ spell.getName()+" already exists");
		}
		var spellEntity = SpellMapper.fromDto(spell);
	    spellEntity.setId(UUID.randomUUID());
		spellRepository.save(spellEntity);
		return spellRepository.findAll().stream().map(SpellMapper::toDto).collect(Collectors.toList());
	}
}