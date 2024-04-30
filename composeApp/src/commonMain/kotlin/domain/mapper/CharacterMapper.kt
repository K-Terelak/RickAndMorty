package domain.mapper

import data.remote.model.CharacterResponse
import domain.model.DomainCharacter

fun CharacterResponse.toDomainCharacter(): DomainCharacter =
    DomainCharacter(
        id = id ?: throw IllegalArgumentException("Character id is null"),
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type,
        url = url
    )
