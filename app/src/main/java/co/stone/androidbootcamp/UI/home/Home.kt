package co.stone.androidbootcamp.UI.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import co.stone.androidbootcamp.Data.Character.CharacterService
import co.stone.androidbootcamp.Data.Location.LocationService
import co.stone.androidbootcamp.UI.list.ListItemsActivity
import co.stone.androidbootcamp.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch


class Home : AppCompatActivity() {

    private val isCharacter by lazy {intent?.extras?.getBoolean(ListItemsActivity.IS_CHARACTER, true)}

    private val serviceCharacter by lazy {
        CharacterService()
    }

    private val serviceLocation by lazy {
       LocationService()
    }

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

       setup()

        if (isCharacter == true){
            setupDataCharacter()
        } else {
            setupDataLocation()
        }

    }

    private fun setupDataCharacter(){
        lifecycleScope.launch{
            serviceCharacter.getCharacters()
                .forEach {println(it)}

        }
    }

    private fun setupDataLocation(){
        lifecycleScope.launch{
            serviceLocation.getLocations()
                .forEach {println(it)}
        }
    }

    private fun setup() {

        val intentList = Intent(this, ListItemsActivity::class.java)
        binding.apply {
            button1.setOnClickListener{
                intentList.putExtra(ListItemsActivity.IS_CHARACTER, true)
                startActivity(intentList)
            //leva para a lista de personagens
            }
            
            button2.setOnClickListener {
                intentList.putExtra(ListItemsActivity.IS_CHARACTER, false)
                startActivity(intentList)
            }
        }
    }
}

