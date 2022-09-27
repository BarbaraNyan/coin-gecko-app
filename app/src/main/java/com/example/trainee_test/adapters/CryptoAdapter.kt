package com.example.trainee_test.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainee_test.R
import com.example.trainee_test.databinding.ItemUsdBinding
import com.example.trainee_test.model.CryptoItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.withContext

class CryptoAdapter(private val listener: OnItemClickListener, var cryptoList : ArrayList<CryptoItem>): ListAdapter<CryptoItem, CryptoAdapter.CryptoItemViewHolder>(CryptoComparator()) {

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<CryptoItem>) {
        this.cryptoList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder {
        val binding = ItemUsdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
//        val curItem = getItem(position)
        val curItem = cryptoList[position]
//        Picasso.get().load(curItem.cryptoImage).into(holder.cryptoImage)

        holder.bind(curItem.cryptoNameFull, curItem.cryptoNameShort, curItem.cryptoPrice, curItem.cryptoPercent, curItem.cryptoImage)
    }

    inner class CryptoItemViewHolder(private val binding: ItemUsdBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                val position = bindingAdapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val crypto = getItem(position)
                }
            }
        }

        private val res = itemView.context.resources

        private val cryptoItemNameFull = binding.cryptoNameFull
        private val cryptoItemNameShort = binding.cryptoNameShort
        private val cryptoItemPrice = binding.cryptoPrice
        private val cryptoItemPercent = binding.cryptoPercent
        val cryptoImage = binding.imgCrypto

        fun bind(nameFull: String?, nameShort: String?, price: Double?, percent: Double?, image: String?){
            Picasso.get().load(image).into(cryptoImage)
            cryptoItemNameFull.text = nameFull
            cryptoItemNameShort.text = nameShort
            cryptoItemPrice.text = String.format(res.getString(R.string.usd_price, price.toString()))
            if (percent != null) {
                if(percent > 0){
                    cryptoItemPercent.text = String.format(res.getString(R.string.usd_percent), "+", percent.toString())
                    cryptoItemPercent.setTextColor(res.getColor(R.color.green))
                }
                else{
                    cryptoItemPercent.text = String.format(res.getString(R.string.usd_percent), "", percent.toString())
                    cryptoItemPercent.setTextColor(res.getColor(R.color.red))
                }
            }

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