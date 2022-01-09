package com.example.pokedek.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedek.Model.Api.Repo.Apirepo
import com.example.pokedek.Model.Room.Entity.Pokemonlist
import com.example.pokedek.R
import com.example.pokedek.Ui.Adapter.Pokehomervadapter
import com.example.pokedek.Viewmodel.Apiviewmodel
import com.example.pokedek.Viewmodel.Roomviewmodel
import com.example.pokedek.Viewmodel.Vmodelfactory
import kotlinx.android.synthetic.main.fragment_fragmenthome.view.*


class Homefragment : Fragment() {
    lateinit var apiviewmodel: Apiviewmodel
    lateinit var roomviewmodel: Roomviewmodel

    var listsum = ArrayList<Pokemonlist>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragmenthome, container, false)

        val repo = Apirepo()
        var Vmodelfac = Vmodelfactory(repo)
        apiviewmodel = ViewModelProvider(this,Vmodelfac).get(Apiviewmodel::class.java)
        roomviewmodel = ViewModelProvider(this).get(Roomviewmodel::class.java)

        listsum = arrayListOf()
        val adapter = Pokehomervadapter()
        val recview = view.recpokehom
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(context)

        apiviewmodel.getpokelist()
        apiviewmodel.pokelistrespon.observe(viewLifecycleOwner, Observer { listrespon->
            val data = listrespon.body()?.results
            for(i in 0 until data!!.size){
                val nama = data[i].name
                apiviewmodel.getpokesum(nama)
                apiviewmodel.pokesumrespon.observe(viewLifecycleOwner, Observer { sumrespon ->
                    if (sumrespon.isSuccessful){

                        val sum = Pokemonlist(
                            sumrespon.body()?.name.toString(),
                            sumrespon.body()?.sprites!!.other.officialArtwork.frontDefault,
                            sumrespon.body()?.height.toString(),
                            sumrespon.body()?.weight.toString()
                        )
                        roomviewmodel.insertpoke(sum)
                    }
                })
            }
        })

        roomviewmodel.readpokelist.observe(viewLifecycleOwner, Observer {  roomrespon ->
            adapter.setdata(roomrespon)
        })

        view.btn_pokelist.setOnClickListener {
            findNavController().navigate(HomefragmentDirections.actionFragmenthomeToPokemon())
        }

        view.btn_gofind.setOnClickListener {
            findNavController().navigate(HomefragmentDirections.actionFragmenthomeToSearchfragment())
        }

        return view
    }

}