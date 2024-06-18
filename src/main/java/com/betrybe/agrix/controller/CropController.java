package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
  @Operation(summary = "Buscar plantações", description = "Listar todas as plantações")
  @ApiResponse(
      responseCode = "200",
      description = "Retorno de todas as plantações",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class)))
  )
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  @Operation(summary = "Buscar uma plantação", description = "Informações de uma plantação")
  @ApiResponse(
      responseCode = "200",
      description = "Retorno de uma plantação especifica",
      content = @Content(schema = @Schema(implementation = CropDto.class))
  )
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(id)
    );
  }

  /**
   * Search crops by harvest date range list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  @GetMapping("/search")
  @Operation(summary = "Buscar plantações por data",
      description = "Lista plantações por faixa de tempo")
  @ApiResponse(
      responseCode = "200",
      description = "Retorno de uma lista de plantações com filtradas",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class)))
  )
  public List<CropDto> searchCropsByHarvestDateRange(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> allCrops = cropService.findAllByHarvestDateRange(start, end);
    return allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Associar fertilizante a plantação",
      description = "Associa um fertilizante a uma plantação")
  @ApiResponse(
      responseCode = "200",
      description = "Fertilizante e plantação associados com sucesso!"
  )
  public String setCropFertilizer(@PathVariable Long cropId, @PathVariable Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    cropService.setCropFertizer(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Gets fertilizers.
   *
   * @param cropId the crop id
   * @return the fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  @Operation(summary = "Buscar fertilizantes da plantação",
      description = "Lista de fertilizantes associados a plantação específica")
  @ApiResponse(
      responseCode = "200",
      description = "Retorno de todos os fertilizantes de uma plantação válida",
      content = @Content(array = @ArraySchema(
          schema = @Schema(implementation = FertilizerDto.class)
      ))
  )
  public List<FertilizerDto> getFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    List<Fertilizer> allFertilizers = cropService.findAllFertilizersByCropId(cropId);
    return allFertilizers.stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }
}
