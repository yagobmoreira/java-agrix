package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.PersonCreationDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person person dto.
   *
   * @param personCreationDto the person creation dto
   * @return the person dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Criar usuário", description = "Cria novo usuário")
  @ApiResponse(
      responseCode = "201",
      description = "Usuário criado",
      content = @Content(schema = @Schema(implementation = PersonDto.class))
  )
  public PersonDto createPerson(@RequestBody PersonCreationDto personCreationDto) {
    return PersonDto.fromEntity(
        personService.create(personCreationDto.toEntity())
    );
  }
}
