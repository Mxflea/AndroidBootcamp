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
    val origin: Place,
    val image: String,
)

@Serializable
data class Place (
    val name: String
)

@Serializable
data class LocationsResponse(
    val results: List<LocationResponse>
)

@Serializable
data class LocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)