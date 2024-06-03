package co.stone.androidbootcamp.Data.Character

import co.stone.androidbootcamp.Data.CharacterResponse
import co.stone.androidbootcamp.Data.CharactersResponse
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