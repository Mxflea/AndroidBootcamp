package co.stone.androidbootcamp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import co.stone.androidbootcamp.R
import co.stone.androidbootcamp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val character by lazy { intent?.extras?.getSerializable(CHARACTER) }
    lateinit var binding: ActivityDetailsBinding
    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setup()
    }

    private fun setup(){
       // val validaCharacter = isCharacter
        character?.let {
            val item = it  as? Item
             binding.apply{

                 if (isCharacter == true){
                     armothy.visibility = View.VISIBLE
                     origin.visibility = View.VISIBLE
                     race.text = getString(R.string.detail_race)
                     status.text = getString(R.string.detail_status)
                 } else {
                     armothy.visibility = View.GONE
                     titleOrigin.visibility = View.GONE
                     titleRace.text = getString(R.string.detail_type)
                     titleStatus.text = getString(R.string.detail_dimension)
                 }
                 name.text = item?.secondField.orEmpty()
                 race.text = item?.thirdField.orEmpty()
                 status.text = item?.fourField.orEmpty()
                 origin.text = item?.fiveField.orEmpty()
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
        const val CHARACTER = "character"
        const val IS_CHARACTER = "is_character"
    }
}