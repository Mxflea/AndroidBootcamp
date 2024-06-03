package co.stone.androidbootcamp.Data.Location

import co.stone.androidbootcamp.Data.LocationResponse
import co.stone.androidbootcamp.Data.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.Serializable

interface LocationGateway {

    @GET("location")
    suspend fun getLocations(): LocationsResponse

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: Serializable
    ): LocationResponse
}