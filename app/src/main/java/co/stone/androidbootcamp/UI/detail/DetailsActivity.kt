package co.stone.androidbootcamp.UI.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import co.stone.androidbootcamp.R
import co.stone.androidbootcamp.databinding.ActivityDetailsBinding
import coil.imageLoader
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    private val characterId by lazy { intent?.extras?.getSerializable(CHARACTER_ID) }
    lateinit var binding: ActivityDetailsBinding
    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    private val viewModel by viewModels<DetailViewModel>(DetailViewModel.Factory::build)
    private val viewModelLocation by viewModels<DetailViewModelLocation>(DetailViewModelLocation.Factory::build)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setup()
    }

    private fun setup(){
        characterId?.let {id ->
            lifecycleScope.launch {
                val character = viewModel.getCharacter(id)
                val location = viewModelLocation.getLocation(id)

                   binding.apply{
                     if (isCharacter == true){
                         image.visibility = View.VISIBLE
                         name.text = character.name
                         status.text = character.status.name
                         race.text = character.species
                         origin.text = character.origin.name
                         Picasso.get()
                           .load(character.image)
                           .resize(1000, 1000)
                           .into(image)

                     } else {
                         image.visibility = View.GONE
                         titleThirdField.visibility = View.GONE
                         titleSecondField.text = getString(R.string.detail_type)
                         titleFirstField.text = getString(R.string.detail_dimension)
                         name.text = location.name
                         status.text = location.type
                         race.text = location.dimension
                     }
//
                   }
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val CHARACTER_ID = "characterId"
        const val IS_CHARACTER = "is_character"
    }
}