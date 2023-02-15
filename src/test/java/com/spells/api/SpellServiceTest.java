package com.spells.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;

import com.spells.api.dto.SpellDto;
import com.spells.api.entity.Spell;
import com.spells.api.repository.SpellRepository;
import com.spells.api.service.SpellServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SpellServiceTest {

    @Mock
    private SpellRepository spellRepository;
    @InjectMocks
    private SpellServiceImpl spellService;

    @Test
    public void testFindAll() {
        Specification<Spell> spec = mock(Specification.class);
        List<Spell> spellList = new ArrayList<>();
        var spell1 = new Spell();
        spell1.setName("Expelliarmus");
        spell1.setType("Charm");
        spell1.setLight("Red");
        spellList.add(spell1);
        when(spellRepository.findAll(spec)).thenReturn(spellList);
        List<SpellDto> result = spellService.findAll(spec);

        assertThat(result).hasSize(1);
        SpellDto spellDto = result.get(0);
        assertThat(spellDto.getName()).isEqualTo("Expelliarmus");
        assertThat(spellDto.getType()).isEqualTo("Charm");
        assertThat(spellDto.getLight()).isEqualTo("Red");
    }

 
}