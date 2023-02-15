package com.spells.api.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spells.api.dto.SpellDto;
import com.spells.api.entity.Spell;
import com.spells.api.service.SpellService;

@RestController
@RequestMapping("/Spells")
public class SpellController {

	@Autowired
	private SpellService spellService;
	@Autowired
	private Validator validator;

	@GetMapping
	public List<SpellDto> findAll(@RequestParam(required = false) String type,
			@RequestParam(required = false) String light) {
		Specification<Spell> spec = null;
		if (type != null) {
			//Este query solo retorna gvalores con filtro type
			spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
		}
		if (light != null) {
			//valida si es blue o red
			if (!light.equals("Blue") && !light.equals("Red")) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Invalid value for light parameter. It should be either Blue or Red");
			}
			if (spec == null) {
				//Este query solo retorna gvalores con filtro light
				spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("light"), light);
			} else {
			    //Si spec es diferente de null agrega un and
				Specification<Spell> lightSpec = (root, query, criteriaBuilder) -> criteriaBuilder
						.equal(root.get("light"), light);
				spec = spec.and(lightSpec);
			}
		}
		return spellService.findAll(spec);
	}

	@PostMapping
	public ResponseEntity<List<SpellDto>> addSpell(@Valid @RequestBody SpellDto spellDTO) {
		// Validación de errores
		Set<ConstraintViolation<SpellDto>> violations = validator.validate(spellDTO);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		List<SpellDto> spells = spellService.createSpell(spellDTO);
		return new ResponseEntity<>(spells, HttpStatus.CREATED);
	}
}