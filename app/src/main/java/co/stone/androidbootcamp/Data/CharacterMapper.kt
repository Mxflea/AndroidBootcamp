package co.stone.androidbootcamp.Data

import co.stone.androidbootcamp.domain.Character
import co.stone.androidbootcamp.domain.CharacterStatus

object CharacterMapper {

    fun toDomain(response: CharacterResponse) =
        with(response) {
            Character(
                id = id,
                name = name,
                status = CharacterStatus.toStatus(status),
                species = species,
            )
        }

}