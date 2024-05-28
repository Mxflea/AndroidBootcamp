package co.stone.androidbootcamp.UI.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import co.stone.androidbootcamp.R
import co.stone.androidbootcamp.databinding.ActivityDetailsBinding
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    private val characterId by lazy { intent?.extras?.getSerializable(CHARACTER_ID) }
    lateinit var binding: ActivityDetailsBinding
    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    private val viewModel by viewModels<DetailViewModel> (DetailViewModel.Factory::build)

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

                   binding.apply{
                     if (isCharacter == true){
                         armothy.visibility = View.VISIBLE
                         origin.visibility = View.VISIBLE
                         titleSecondField.text = getString(R.string.detail_race)
                         titleFirstField.text = getString(R.string.detail_status)
                     } else {
                         armothy.visibility = View.GONE
                         titleOrigin.visibility = View.GONE
                         titleSecondField.text = getString(R.string.detail_type)
                         titleFirstField.text = getString(R.string.detail_dimension)
                     }
                       name.text = character.name
                       status.text = character.status.name
                       race.text = character.species
                      // origin.text = character.origin
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