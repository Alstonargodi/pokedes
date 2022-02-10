package com.example.pokedek.Ui.Favorite.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedek.Model.Room.Entity.Favorite.Favoritelist
import com.example.pokedek.Model.Room.Entity.Pokemon.Pokemonlist
import com.example.pokedek.R
import com.example.pokedek.Ui.Search.Adapter.Searchrvadapter
import com.example.pokedek.databinding.CvCardfavoriteBinding
import com.example.pokedek.databinding.CvrecSearchBinding

class Favoritervadapter: RecyclerView.Adapter<Favoritervadapter.viewhold>() {
    var listdata = emptyList<Favoritelist>().distinct()
    lateinit var mContext: Context

    class viewhold(var view: CvCardfavoriteBinding): RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewhold {
        val binding = CvCardfavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = Favoritervadapter.viewhold(binding)
        mContext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: viewhold, position: Int) {
        val holder = holder as viewhold
        val item = listdata[position]
        holder.view.FavnameTvcardfav.text = item.name

        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(item.link)
            .into(holder.view.imgTvcardfav)

    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    fun setdata(list : List<Favoritelist>){
        listdata = list.distinct()
        notifyDataSetChanged()
    }


}