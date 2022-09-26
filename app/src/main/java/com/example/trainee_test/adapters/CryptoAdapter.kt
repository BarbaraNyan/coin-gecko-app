package com.example.trainee_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainee_test.databinding.ItemUsdBinding
import com.example.trainee_test.model.CryptoItem

class CryptoAdapter(private val listener: OnItemClickListener): ListAdapter<CryptoItem, CryptoAdapter.CryptoItemViewHolder>(CryptoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder {
        val binding = ItemUsdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
        val curItem = getItem(position)
        holder.bind(curItem.cryptoNameFull, curItem.cryptoNameShort, curItem.cryptoPrice, curItem.cryptoPercent)
    }

    inner class CryptoItemViewHolder(private val binding: ItemUsdBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                TODO()
            }
        }

        private val cryptoItemNameFull = binding.cryptoNameFull
        private val cryptoItemNameShort = binding.cryptoNameShort
        private val cryptoItemPrice = binding.cryptoPrice
        private val cryptoItemPercent = binding.cryptoPercent

        fun bind(nameFull: String?, nameShort: String?, price: String?, percent: String?){
            cryptoItemNameFull.text = nameFull
            cryptoItemNameShort.text = nameShort
            cryptoItemPrice.text = price
            cryptoItemPercent.text = percent
        }

    }

    class CryptoComparator: DiffUtil.ItemCallback<CryptoItem>(){
        override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem) =
            oldItem.cryptoNameShort == newItem.cryptoNameShort

        override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem) =
            oldItem == newItem
    }

    interface OnItemClickListener {

    }

}