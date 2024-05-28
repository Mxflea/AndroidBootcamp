package co.stone.androidbootcamp.Data

import co.stone.androidbootcamp.domain.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Serializable

class CharacterService {

    private val gateway by lazy {
        GatewayBuilder.build<CharacterGateway>()
    }

   suspend fun getCharacters(): List<Character> =
       withContext(Dispatchers.IO) {
           gateway
               .getCharacters()
               .results
               .map(CharacterMapper::toDomain)
       }

    suspend fun getCharacter(id: Serializable): Character =
            gateway.getCharacter(id)
                .let(CharacterMapper:: toDomain)
}