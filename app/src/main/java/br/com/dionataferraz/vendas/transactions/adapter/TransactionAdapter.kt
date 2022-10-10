package br.com.dionataferraz.vendas.transactions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dionataferraz.vendas.domain.OperationTypeEnum
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.data.model.Transaction
import br.com.dionataferraz.vendas.databinding.ItemListBinding
import java.text.SimpleDateFormat

class TransactionAdapter(private val listener: Listener) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    interface Listener {
        fun onItemClick(text: String)
    }

    private val listItem: MutableList<Transaction> = mutableListOf()

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

    fun addList(list: List<Transaction>) {
        listItem.addAll(list)
    }

}

class TransactionViewHolder(
    private val binding: ItemListBinding,
    private val listener: TransactionAdapter.Listener
) : RecyclerView.ViewHolder(binding.root) {

    private val formatter = SimpleDateFormat("hh:mm")

    fun bind(TransactionItem: Transaction) {
        val value = TransactionItem.operationValue.toString()

        binding.tvTransactionTime.text = TransactionItem.date

        if (TransactionItem.operationTypeEnum == OperationTypeEnum.DEPOSITAR) {
            binding.tvTransactionTitle.text = "Deposit"
            binding.tvTransactionValue.text = "R$ +$value"
        } else {
            binding.tvTransactionTitle.text = "Withdraw"
            binding.tvTransactionValue.text = "R$ -$value"
        }

        when (TransactionItem.operationTypeEnum) {
            OperationTypeEnum.SACAR -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_money_off_24)
            OperationTypeEnum.DEPOSITAR -> binding.ivTransaction.setImageResource(R.drawable.ic_baseline_attach_money_24)
        }

        binding.root.setOnClickListener {
            listener.onItemClick(TransactionItem.operationTypeEnum.name)
        }
    }
}