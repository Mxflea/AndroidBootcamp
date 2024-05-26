package co.stone.androidbootcamp.Data

import retrofit2.Retrofit

class CharacterService {

    private val gateway by lazy {
        GatewayBuilder.build<CharacterGateway>()
    }

   suspend fun getCharacters(): List<CharacterResponse> =
        gateway
            .getCharacters()
            .results

        suspend fun getCharacter(id: Int): CharacterResponse =
            gateway.getCharacter(id)
}