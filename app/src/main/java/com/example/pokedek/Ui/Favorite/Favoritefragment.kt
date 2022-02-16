package com.example.pokedek.Ui.Favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedek.R
import com.example.pokedek.Ui.Favorite.Adapter.Favoritervadapter
import com.example.pokedek.Viewmodel.Roomviewmodel
import com.example.pokedek.databinding.FragmentFavoritefragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class Favoritefragment : Fragment() {
    private var _binding: FragmentFavoritefragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var roomviewmodel: Roomviewmodel
    lateinit var adapter : Favoritervadapter
    private var type = ""
    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritefragmentBinding.inflate(layoutInflater)

        //adapter
        adapter = Favoritervadapter()
        val recview = binding.Favoriterecylerview
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(requireContext())


        binding.btnTogglesort.setOnClickListener {
            binding.Wishlistspinner.visibility =
                if (binding.Wishlistspinner.visibility == View.GONE)
                    View.VISIBLE
                else
                    View.GONE
        }

        binding.tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                when(position){
                    0->{
                        type = ""
                        favoritelistnew("")
                    }
                    1->{
                        type = "pokemon"
                        favoritelistnew("pokemon")
                    }
                    2->{
                        type = "item"
                        favoritelistnew("item")
                    }

                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.Wishlistspinner.onItemSelectedListener = object : AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var item = p0?.getItemAtPosition(p2)
                when(item){
                    "New Items"->{
                        favoritelistnew(type)
                    }
                    "Old Items"->{
                        favoritelistold(type)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                favoritelistnew("caterpie")
            }
        }

        //room
        roomviewmodel = ViewModelProvider(this).get(Roomviewmodel::class.java)

        favoritelistnew("")

        return binding.root
    }

    fun favoritelistnew(tipe : String){
        if(tipe.isNotEmpty()){
            roomviewmodel.readnew(tipe).observe(viewLifecycleOwner, Observer { favrespon ->
                try {
                    if (favrespon.isNotEmpty()){
                        adapter.setdata(favrespon)
                    }else if (favrespon.isEmpty()){
                        binding.Favoriterecylerview.setBackgroundResource(R.drawable.emptyview)
                    }
                }catch (e : Exception){
                    Log.d("favoritelist",e.toString())
                }
            })
        }else if (tipe.isEmpty()){
            roomviewmodel.readfavlistbynew.observe(viewLifecycleOwner, Observer { favrespon ->
                try {
                    if (favrespon.isNotEmpty()){
                        adapter.setdata(favrespon)
                    }else if (favrespon.isEmpty()){
                        binding.Favoriterecylerview.setBackgroundResource(R.drawable.emptyview)
                    }
                }catch (e : Exception){
                    Log.d("favoritelist",e.toString())
                }
            })

        }
    }

    fun favoritelistold(tipe: String){
        if (tipe.isNotEmpty()){
            roomviewmodel.readold(tipe).observe(viewLifecycleOwner, Observer { favrespon ->
                try {
                    if (favrespon.isNotEmpty()){
                        adapter.setdata(favrespon)
                    }else if (favrespon.isEmpty()){
                        binding.Favoriterecylerview.setBackgroundResource(R.drawable.emptyview)
                    }
                }catch (e : Exception){
                    Log.d("favoritelist",e.toString())
                }
            })
        }else if (tipe.isEmpty()){
            roomviewmodel.readfavlistbyold.observe(viewLifecycleOwner, Observer { favrespon ->
                try {
                    if (favrespon.isNotEmpty()){
                        adapter.setdata(favrespon)
                    }else if (favrespon.isEmpty()){
                        binding.Favoriterecylerview.setBackgroundResource(R.drawable.emptyview)
                    }
                }catch (e : Exception){
                    Log.d("favoritelist",e.toString())
                }
            })

        }
    }
}