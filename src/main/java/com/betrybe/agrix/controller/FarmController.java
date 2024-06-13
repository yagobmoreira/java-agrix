package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmCreationDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
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
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')"
      + " or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_USER')")
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.findAll();

    return allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(id)
    );
  }

  /**
   * Create crop crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@PathVariable Long farmId, @RequestBody CropCreationDto cropCreationDto)
      throws FarmNotFoundException {

    return CropDto.fromEntity(
        cropService.create(farmId, cropCreationDto.toEntity())
    );
  }

  /**
   * Gets all crops by farm id.
   *
   * @param farmId the farm id
   * @return the all crops by farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) throws FarmNotFoundException {
    List<Crop> allCrops = cropService.findAllByFarmId(farmId);

    return allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }
}
