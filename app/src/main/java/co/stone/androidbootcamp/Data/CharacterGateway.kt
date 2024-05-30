package co.stone.androidbootcamp.Data

import retrofit2.http.GET
import retrofit2.http.Path
import java.io.Serializable

interface CharacterGateway {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Serializable
    ): CharacterResponse
}