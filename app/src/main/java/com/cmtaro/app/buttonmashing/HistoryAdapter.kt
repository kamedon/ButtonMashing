package com.cmtaro.app.buttonmashing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cmtaro.app.buttonmashing.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryItemViewHolder>() {

  var histories = listOf<Int>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
    val binding = DataBindingUtil.inflate<ItemHistoryBinding>(
      LayoutInflater.from(parent.context),
      R.layout.item_history,
      parent,
      false
    )

    return HistoryItemViewHolder(binding)
  }

  override fun getItemCount(): Int = histories.size

  override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
    val history = histories[position]
    holder.bind(history)
  }

  fun update(list: List<Int>) {
    histories = list
    notifyDataSetChanged()
  }

}

class HistoryItemViewHolder(val binding: ItemHistoryBinding) :
  RecyclerView.ViewHolder(binding.root) {
  fun bind(count: Int) {
    binding.count = count
  }
}
