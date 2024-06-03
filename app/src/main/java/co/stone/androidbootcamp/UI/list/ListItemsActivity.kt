package co.stone.androidbootcamp.UI.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import co.stone.androidbootcamp.UI.detail.DetailsActivity
import co.stone.androidbootcamp.databinding.ActivityListCharacterBinding
import co.stone.androidbootcamp.domain.Character
import co.stone.androidbootcamp.domain.Location
import kotlinx.coroutines.launch

class ListItemsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListCharacterBinding

    private val viewModelCharacter by viewModels<ListViewModelCharacter>(ListViewModelCharacter.Factory:: build)
    private val viewModelLocation by viewModels<ListViewModelLocation>(ListViewModelLocation.Factory:: build)

    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    private val listAdapter: ListAdapter by lazy {
        ListAdapter().apply{
            onClick = this@ListItemsActivity:: onItemSelected
        }
    }

    private val listAdapterLocation: ListAdapterLocation by lazy {
        ListAdapterLocation().apply{
            onClickLocation = this@ListItemsActivity:: onItemSelectedLocation
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        lifecycleScope.launch{
            viewModelCharacter.getCharacters().forEach(::println)
        }

        lifecycleScope.launch{
            viewModelLocation.getLocations().forEach(::println)
        }
    }

    private fun setup() {

        val divider =   DividerItemDecoration(this@ListItemsActivity, DividerItemDecoration.VERTICAL)
        binding.apply {
            listItems.addItemDecoration(divider)

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        if (isCharacter == true){
            setupDataCharacter()
            binding.listItems.adapter = listAdapter
        } else {
            setupDataLocation()
            binding.listItems.adapter = listAdapterLocation

        }
    }

    private fun setupDataCharacter(){
        lifecycleScope.launch{
            viewModelCharacter.getCharacters()
                .let(listAdapter::addItems)
        }
    }

    private fun setupDataLocation(){
        lifecycleScope.launch{
            viewModelLocation.getLocations()
                .let(listAdapterLocation::addItemsLocation)
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

    private fun onItemSelected(item: Character){
        val intent = Intent(this, DetailsActivity:: class.java)
        intent.putExtra(DetailsActivity.CHARACTER_ID, item.id)
        intent.putExtra(IS_CHARACTER, isCharacter == true)
        startActivity(intent)
    }

    private fun onItemSelectedLocation(item: Location){
        val intent = Intent(this, DetailsActivity:: class.java)
        intent.putExtra(DetailsActivity.CHARACTER_ID, item.id)
        intent.putExtra(IS_CHARACTER, isCharacter == false)
        startActivity(intent)
    }

    companion object {
        const val IS_CHARACTER = "is_character"
    }
}