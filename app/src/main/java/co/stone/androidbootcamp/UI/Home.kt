package co.stone.androidbootcamp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import co.stone.androidbootcamp.Data.CharacterService
import co.stone.androidbootcamp.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch


class Home : AppCompatActivity() {

    private val service by lazy {
        CharacterService()
    }

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

       setup()

        lifecycleScope.launch{
            service.getCharacters()
                .forEach {println(it)}

        }
    }

    private fun setup() {

        val intentList = Intent(this, ListCharacterActivity::class.java)
        binding.apply {
            button1.setOnClickListener{
                intentList.putExtra(ListCharacterActivity.IS_CHARACTER, true)
                startActivity(intentList)
            //leva para a lista de personagens
            }
            
            button2.setOnClickListener {
                intentList.putExtra(ListCharacterActivity.IS_CHARACTER, false)
                startActivity(intentList)
            }
        }
    }
}

