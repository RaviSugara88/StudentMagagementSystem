package com.ravitech.ignounew.ui.dashboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravitech.ignounew.databinding.ItemViewDashboardBinding
import com.ravitech.ignounew.model.SemData

class DashboardAdapter(private val context: Context,private val onClick:(entity: SemData, type:Int, po:Int)->Unit)
    :RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private var semList = mutableListOf<SemData>()

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(semList:List<SemData>){
        this.semList.clear()
        this.semList.addAll(semList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DashboardViewHolder = DashboardViewHolder(
        ItemViewDashboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

            )

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(semList[position])
    }

    override fun getItemCount(): Int =semList.size



    inner class DashboardViewHolder(val binding:ItemViewDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(entity: SemData){
                binding.apply {
                    tvName.text = entity.name
                    itemView.setOnClickListener {
                     onClick(entity,0,adapterPosition)
                    }
                    executePendingBindings()
                }
            }
    }

}