package br.com.dionataferraz.vendas.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.databinding.ItemListBinding

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun addList(list: List<String>) {
        listItem.addAll(list)

    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(name: String) {
        binding.tvName.text = name
        binding.root.setOnClickListener { listener.onItemClick(name) }

    }
}