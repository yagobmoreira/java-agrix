package com.betrybe.agrix.service.exception;

/**
 * The type Farm not found exception.
 */
public class FertilizerNotFoundException extends NotFoundException {

  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
