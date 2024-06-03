package co.stone.androidbootcamp.UI.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.stone.androidbootcamp.databinding.ItemRowBinding
import co.stone.androidbootcamp.domain.Location

class ListAdapterLocation: RecyclerView.Adapter<ListAdapterLocation.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    var onClickLocation: ((Location) -> Unit)? = null

    private var items: MutableList<Location> = mutableListOf()
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
            binding.title.text = item.name
            binding.description.text = item.type

            itemView.setOnClickListener{
                onClickLocation?.invoke(item)
            }
        }
    }

    fun addItemsLocation(itemsToAdd: List<Location>) {
        this.items.addAll(itemsToAdd)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}