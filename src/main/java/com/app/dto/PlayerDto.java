package com.app.dto;

/**
 * A Data Transfer Object (DTO) representing a player with their ID and login information.
 *
 * @param id    The unique identifier of the player.
 * @param login The login name of the player.
 */
public record PlayerDto(Long id, String login) {
}