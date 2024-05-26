package co.stone.androidbootcamp.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import co.stone.androidbootcamp.databinding.ActivityListCharacterBinding

class ListCharacterActivity : AppCompatActivity() {
    lateinit var binding: ActivityListCharacterBinding
    private var items: MutableList<Item> = mutableListOf()

    private val isCharacter by lazy {intent?.extras?.getBoolean(IS_CHARACTER, true)}

    private val listAdapter: ListAdapter by lazy {
        ListAdapter().apply{
            onClick = this@ListCharacterActivity:: onItemSelected
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {

        val divider =   DividerItemDecoration(this@ListCharacterActivity, DividerItemDecoration.VERTICAL)
        binding.apply {
            listItems.addItemDecoration(divider)
            listItems.adapter = listAdapter

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        if (isCharacter == true){
            setupDataCharacter()
        } else {
            setupDataPlace()
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

    private fun setupDataCharacter() {
        items.add(
            Item(
            "1",
            "Rick Sanchez",
            "humano",
            "vivo",
            "Earth (C-137)"
            )
        )
        items.add(
            Item(
            "2",
            "Morty Smith",
            "humano",
            "vivo",
            "Earth (C-137)",
            )
        )
        items.add(
            Item(
                "3",
                "Armothy",
                "desconhecido",
                "morto",
                "Post-Apocalyptic Earth"
                )
        )
        items.add(
            Item(
                "4",
                "Mr. Sneezy",
                "humano",
                "vivo",
                "Interdimensional Cable"
            )

        )
        items.add(
            Item(
                "5",
                "Mr. Sneezy",
                "humano",
                "vivo",
                "Interdimensional Cable"
            )

        )
        items.add(
            Item(
                "6",
                "Yaarb",
                "alien",
                "vivo",
                "Desconhecido"
            )

        )
        items.add(
            Item(
                "7",
                "Michael Denny",
                "humano",
                "vivo",
                "Desconhecido"
            )

        )
        items.add(
            Item(
                "8",
                "Hepatitis A",
                "doença",
                "morto",
                "Ruben"
            )

        )
        items.add(
            Item(
                "9",
                "Chair-waiter",
                "humanoid",
                "vivo",
                "Earth (Chair Dimension)"
            )

        )
        items.add(
            Item(
                "10",
                "snake Solidier",
                "animal",
                "vivo",
                "Snake Planet"
            )

        )

        listAdapter.addItems(items)
    }

    private fun setupDataPlace() {
        items.add(
            Item(
                "1",
                "Earth",
                "planeta",
                "Dimension C-137",
            )
        )
        items.add(
            Item(
                "2",
                "Citadel of Ricks",
                "estação espacial",
                "Dimension V-470",
            )
        )
        items.add(
            Item(
                "3",
                "Worldender's lair",
                "planeta",
                "Dimension M-193",
            )
        )
        items.add(
            Item(
                "4",
                "Anatomy Park",
                "micro universo",
                "Prime Dimension",
            )
        )
        items.add(
            Item(
                "5",
                "Interdimensional Cable",
                "televisão",
                "Dimension M-193",
            )
        )
        items.add(
            Item(
                "6",
                "Immortality Field Resort",
                "luxury resort",
                "Resort Planet",
            )
        )
        items.add(
            Item(
                "6",
                "Post-Apocalyptic Earth",
                "planeta",
                "Dimension",
            )
        )
        items.add(
            Item(
                "7",
                "Purge Planet",
                "planeta",
                "Dimension C-131",
            )
        )
        items.add(
            Item(
                "8",
                "Abadango",
                "space cluster",
                "Desconhecido",
            )
        )


        listAdapter.addItems(items)
    }

    private fun onItemSelected(item: Item){
        val intent = Intent(this, DetailsActivity:: class.java)
        intent.putExtra(DetailsActivity.CHARACTER, item)
        intent.putExtra(IS_CHARACTER, isCharacter == true)
        startActivity(intent)
    }

    companion object {
        const val IS_CHARACTER = "is_character"
    }
}