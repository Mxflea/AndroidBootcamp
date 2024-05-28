package co.stone.androidbootcamp.UI.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.stone.androidbootcamp.Data.CharacterService
import co.stone.androidbootcamp.domain.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListViewModel(
   private val service: CharacterService
): ViewModel() {

   suspend fun getCharacters(): List<Character> =
       withContext(Dispatchers.Default){
           service.getCharacters()
       }

    object  Factory {

        fun build() =
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    ListViewModel(
                        service = CharacterService()
                    ) as T
            }
    }

}