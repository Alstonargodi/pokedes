package com.example.pokedek.Ui.Berry.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedek.Model.Room.Entity.Berry.Flavourberrylist
import com.example.pokedek.R
import kotlinx.android.synthetic.main.cv_flavourberry.view.*

class Berryflavourrvadapter: RecyclerView.Adapter<Berryflavourrvadapter.viewholder>() {

    var flavourlist = emptyList<Flavourberrylist>().distinct()

    class viewholder(view : View): RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Berryflavourrvadapter.viewholder {
        return viewholder(LayoutInflater.from(parent.context).inflate(R.layout.cv_flavourberry,parent,false))
    }

    override fun onBindViewHolder(holder: Berryflavourrvadapter.viewholder, position: Int) {
        val item = flavourlist[position]

        holder.itemView.Berryflavour_name.text = item.name
        holder.itemView.Berryflavour_pot.text = item.potecny
    }

    override fun getItemCount(): Int {
        return flavourlist.size
    }

    fun setdata(list : List<Flavourberrylist>){
        flavourlist = list.distinct()
        notifyDataSetChanged()
    }

}