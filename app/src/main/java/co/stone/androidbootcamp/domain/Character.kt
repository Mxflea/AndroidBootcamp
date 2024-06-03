package co.stone.androidbootcamp.domain

import co.stone.androidbootcamp.Data.Place


class Character (
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val origin: Place,
    val image: String
    )



enum class CharacterStatus(
    val status: String
) {
    Alive("alive"),
    Dead("dead"),
    Unknown("unknown");

    companion object{

        fun toStatus (status: String) =
            values().first{it.status.equals(status, ignoreCase = true)}
    }
}