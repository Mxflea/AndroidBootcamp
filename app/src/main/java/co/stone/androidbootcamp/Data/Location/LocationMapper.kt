package co.stone.androidbootcamp.Data.Location

import co.stone.androidbootcamp.Data.LocationResponse
import co.stone.androidbootcamp.domain.Location


object LocationMapper {

    fun toDomain(response: LocationResponse) =
        with(response) {
            Location(
                id = id,
                name = name,
                type = type,
                dimension = dimension,
            )
        }
}