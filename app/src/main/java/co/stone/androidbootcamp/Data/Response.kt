package co.stone.androidbootcamp.Data

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val results: List<CharacterResponse>
)

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val origin: Location,
    val image: String,
)

@Serializable
data class Location (
    val name: String
)