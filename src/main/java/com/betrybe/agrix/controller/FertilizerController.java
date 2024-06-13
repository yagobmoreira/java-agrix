package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerCreationDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Ferlizer controller.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer fertilize dto.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilize dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.fromEntity(
        fertilizerService.create(fertilizerCreationDto.toEntity())
    );
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();

    return allFertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public FertilizerDto getFertilizerById(@PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(
        fertilizerService.findById(id)
    );
  }

}
