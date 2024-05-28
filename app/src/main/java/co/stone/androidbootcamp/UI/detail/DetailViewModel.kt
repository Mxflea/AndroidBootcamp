package co.stone.androidbootcamp.UI.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.stone.androidbootcamp.Data.CharacterService
import co.stone.androidbootcamp.domain.Character
import java.io.Serializable

class DetailViewModel(
   private val service: CharacterService
): ViewModel() {

    suspend fun getCharacter(id: Serializable): Character =
        service.getCharacter(id)

    object  Factory {

        fun build() =
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                   DetailViewModel(
                        service = CharacterService()
                    ) as T
            }
    }

}