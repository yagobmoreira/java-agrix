package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
    String username,
    String password,
    Role role
) {

  public Person toEntity() {
    return new Person(username, password, role);
  }
}
