package io.indrian.whatmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.indrian.whatmovies.databinding.ItemLayoutBinding

class ItemAdapter(private val onItemCallbackListener: OnItemCallbackListener) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 15

    interface OnItemCallbackListener {
        fun onClickItem()
    }

    inner class ViewHolder(private val itemBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {
            with(itemBinding) {
                root.setOnClickListener {
                    onItemCallbackListener.onClickItem()
                }
            }
        }
    }
}