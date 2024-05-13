package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Fertilize dto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition) {

  /**
   * From entity fertilize dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilize dto
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
