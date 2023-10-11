package com.example.coordinatechart.ui.result_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coordinatechart.databinding.ItemViewHolderBinding
import com.example.coordinatechart.domen.entity.PointItem

class CoordinateRecyclerViewAdapter(
    private val items: List<PointItem>,
) : RecyclerView.Adapter<CoordinateRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PointItem) {
            binding.coordinateX.text = item.coordinateX.toString()
            binding.coordinateY.text = item.coordinateY.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinateRecyclerViewAdapter.ViewHolder {
        val binding: ItemViewHolderBinding =
            ItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoordinateRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}