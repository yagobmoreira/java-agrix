package com.betrybe.agrix.service.exception;

/**
 * The type Farm not found exception.
 */
public class CropNotFoundException extends NotFoundException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
