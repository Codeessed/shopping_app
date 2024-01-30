package com.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.model.ShipmentModel
import com.android.shipmentapp.R
import com.android.shipmentapp.databinding.AvailableVehicleItemBinding
import com.android.shipmentapp.databinding.ShipmentItemBinding

class AvailableVehicleAdapter(): RecyclerView.Adapter<AvailableVehicleAdapter.AvailableVehicleViewHolder>() {

    inner class AvailableVehicleViewHolder(var binding: AvailableVehicleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){

//            val shipment = differ.currentList[position]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableVehicleViewHolder {
        return AvailableVehicleViewHolder(AvailableVehicleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AvailableVehicleViewHolder, position: Int) {
        holder.itemView    . animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_from_right)
        return holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 10
    }

    var differList = object : DiffUtil.ItemCallback<ShipmentModel>(){
        override fun areItemsTheSame(oldItem: ShipmentModel, newItem: ShipmentModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShipmentModel, newItem: ShipmentModel): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differList)

    interface DiscountItemClickListener{
        fun onClick(position: Int)
    }
}