package co.stone.androidbootcamp.UI.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.stone.androidbootcamp.Data.Character.CharacterService
import co.stone.androidbootcamp.Data.Location.LocationService
import co.stone.androidbootcamp.domain.Character
import co.stone.androidbootcamp.domain.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListViewModelCharacter(
   private val service: CharacterService
): ViewModel() {

    suspend fun getCharacters(): List<Character> =
        withContext(Dispatchers.Default) {
            service.getCharacters()
        }

    object Factory {

        fun build() =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    ListViewModelCharacter(
                        service = CharacterService()
                    ) as T
            }
    }
}

    class ListViewModelLocation(
        private val service: LocationService
    ): ViewModel() {

        suspend fun getLocations(): List<Location> =
            withContext(Dispatchers.Default){
                service.getLocations()
            }

        object  Factory {

            fun build() =
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T =
                        ListViewModelLocation(
                            service = LocationService()
                        ) as T
                }
        }

}