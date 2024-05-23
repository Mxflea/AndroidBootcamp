package co.stone.androidbootcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import co.stone.androidbootcamp.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

       setup()
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

