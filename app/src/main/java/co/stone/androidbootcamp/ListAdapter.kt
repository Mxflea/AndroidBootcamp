package co.stone.androidbootcamp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.stone.androidbootcamp.databinding.ItemRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    var onClick: ((Item) -> Unit)? = null

    private var items: MutableList<Item> = mutableListOf()
    set(value){
        field = ArrayList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(items.isEmpty())
            return

        holder.apply {
            val item = items[position]
            binding.title.text = item.secondField
            binding.description.text = item.thirdField

            itemView.setOnClickListener{
                onClick?.invoke(item)
            }
        }
    }

    fun addItems(itemsToAdd: List<Item>) {
        this.items.addAll(itemsToAdd)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}