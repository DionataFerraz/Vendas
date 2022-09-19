package br.com.dionataferraz.vendas.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.TransactionItem
import br.com.dionataferraz.vendas.TransactionType
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<TransactionItem> = mutableListOf()

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

    fun addList(list: List<TransactionItem>) {
        listItem.addAll(list)
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
) : RecyclerView.ViewHolder(binding.root) {

    private val formatter = SimpleDateFormat("hh:mm")

    fun bind(TransactionItem: TransactionItem) {
        val value = TransactionItem.price.toString()

        binding.tvTransactionTitle.text = TransactionItem.title
        binding.tvTransactionTime.text = formatter.format(TransactionItem.time)
        binding.tvTransactionValue.text = "R$ $value"

        when (TransactionItem.type) {
            TransactionType.SERVICES_ON_DEMAND -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_video_library_24)
            TransactionType.GAS_STATION -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_local_gas_station_24)
            TransactionType.MARKET -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_shopping_cart_24)
            TransactionType.BAR -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_local_bar_24)
        }

        binding.root.setOnClickListener {
            listener.onItemClick(TransactionItem.title)
        }
    }
}