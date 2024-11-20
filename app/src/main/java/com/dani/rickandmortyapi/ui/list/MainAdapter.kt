package com.dani.rickandmortyapi.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.domain.Mycharacter
import com.dani.rickandmortyapi.databinding.MycharacterListItemBinding

class MainAdapter(
    var itemList: List<Mycharacter> = emptyList(),
    private val listener: (Mycharacter) -> Unit,
    private val listenerReload:() -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MycharacterListItemBinding =
            MycharacterListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        val view: View = binding.root
        return ViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        if (position > 12 && position == itemList.size-6){
            listenerReload.invoke()
        }
        holder.itemView.setOnClickListener { listener(item) }
    }

    class ViewHolder(itemView: View, private val binding: MycharacterListItemBinding) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Mycharacter){
            binding.mycharacter = item
        }
    }
}
