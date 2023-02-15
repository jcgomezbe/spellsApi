package com.spells.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.spells.api.controller.SpellController;
import com.spells.api.service.SpellService;

@ExtendWith(MockitoExtension.class)
class SpellControllerTest {

  @InjectMocks
  private SpellController spellController;

  @Mock
  private SpellService spellService;

  @Test
  void testFindAll_invalid_light() {
    var light = "Green";
    assertThrows(ResponseStatusException.class, () -> spellController.findAll(null, light));
  }
}