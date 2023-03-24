package com.example.btt2screen.fragmentscreen

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btt2screen.DataStore
import com.example.btt2screen.R
import com.example.btt2screen.databinding.FragmentHomeBinding
import com.example.btt2screen.home.HomeVM
import com.example.btt2screen.home.MovieAdapter
import com.example.btt2screen.model.Movie
import com.example.btt2screen.model.MovieResp
import com.example.btt2screen.services.MovieApis
import com.example.btt2screen.tabview.TabView
import kotlinx.android.synthetic.main.activity_tab_view.*

class FragmentHome : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: MovieAdapter
    lateinit var vm: HomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(HomeVM::class.java)
        return binding.root
    }
    companion object {
        fun newInstance(): FragmentHome = FragmentHome()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
    }

    override fun onStart() {
        super.onStart()
        vm.getTopRateMovie()
    }

    private fun setUpMovieList() {
        adapter = MovieAdapter()
        val lm = LinearLayoutManager(context)
        binding.rvImage.layoutManager = lm
        binding.rvImage.adapter = adapter
    }

    private fun registerMovieList() {
        vm.movieData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        vm.menuLayoutType.observe(viewLifecycleOwner){
            binding.rvImage.layoutManager = vm.getLayout(Recycleview = TabView())
        }
    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        vm.setLayout(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}


