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
import kotlinx.coroutines.launch

class ListItemsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListCharacterBinding

    private val viewModel by viewModels<ListViewModel>(ListViewModel.Factory:: build)

    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    private val listAdapter: ListAdapter by lazy {
        ListAdapter().apply{
            onClick = this@ListItemsActivity:: onItemSelected
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        lifecycleScope.launch{
            viewModel.getCharacters().forEach(::println)
        }
    }

    private fun setup() {

        val divider =   DividerItemDecoration(this@ListItemsActivity, DividerItemDecoration.VERTICAL)
        binding.apply {
            listItems.addItemDecoration(divider)
            listItems.adapter = listAdapter

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        if (isCharacter == true){
            setupDataCharacter()
        } else {
            //Todo
        }
    }

    private fun setupDataCharacter(){
        lifecycleScope.launch{
            viewModel.getCharacters()
                .let(listAdapter::addItems)
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

    companion object {
        const val IS_CHARACTER = "is_character"
    }
}