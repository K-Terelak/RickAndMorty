package presentation.mapper

import domain.model.DomainCharacter
import presentation.model.CharacterUi

fun DomainCharacter.toCharacterUi(): CharacterUi = CharacterUi(
    id = id,
    created = created.orEmpty(),
    episode = episode.orEmpty(),
    gender = gender.orEmpty(),
    image = image.orEmpty(),
    name = name.orEmpty(),
    species = species.orEmpty(),
    status = status.orEmpty(),
    type = type.orEmpty(),
    url = url.orEmpty()
)
