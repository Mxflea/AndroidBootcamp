package co.stone.androidbootcamp.Data.Location

import co.stone.androidbootcamp.Data.Character.CharacterGateway
import co.stone.androidbootcamp.Data.Character.CharacterMapper
import co.stone.androidbootcamp.Data.GatewayBuilder
import co.stone.androidbootcamp.domain.Character
import co.stone.androidbootcamp.domain.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Serializable

class LocationService {
    private val gateway by lazy {
        GatewayBuilder.build<LocationGateway>()
    }

    suspend fun getLocations(): List<Location> =
        withContext(Dispatchers.IO) {
            gateway
                .getLocations()
                .results
                .map(LocationMapper::toDomain)
        }

    suspend fun getLocation(id: Serializable): Location =
        gateway.getLocation(id)
            .let(LocationMapper:: toDomain)
}