package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Farm creation dto.
 */
public record FarmCreationDto(String name, Double size) {

  public Farm toEntity() {
    return new Farm(name, size);
  }
}
