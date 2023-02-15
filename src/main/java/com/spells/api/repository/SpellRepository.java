package com.spells.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spells.api.entity.Spell;

@Repository
public interface SpellRepository extends JpaRepository<Spell, UUID>, JpaSpecificationExecutor<Spell> {
	List<Spell> findAll(Specification<Spell> spec);
	Optional<Spell> findByNameEquals(String name);
}