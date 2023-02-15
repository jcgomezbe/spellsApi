package com.spells.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spells.api.dto.SpellDto;
import com.spells.api.service.SpellService;

@Component
public class DataLoader implements CommandLineRunner {

  private final SpellService spellService;

  public DataLoader(SpellService spellService) {
    this.spellService = spellService;
  }

  @Override
  public void run(String... args) throws Exception {
    String url = "https://wizard-world-api.herokuapp.com/Spells";

    RestTemplate restTemplate = new RestTemplate();
    SpellDto[] spells = restTemplate.getForObject(url, SpellDto[].class);

    for (SpellDto spell : spells) {
    	try {
        	spellService.createSpell(spell);    		
    	}catch(Exception ex) {
    		System.out.println("Error al crear spell");
    	}

    }
  }
}