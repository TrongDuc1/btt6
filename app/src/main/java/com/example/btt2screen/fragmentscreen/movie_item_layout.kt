package com.example.btt2screen.fragmentscreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.btt2screen.databinding.FragmentMovieItemLayoutBinding
import com.example.btt2screen.home.HomeVM
import com.example.btt2screen.home.MovieAdapter
import com.example.btt2screen.model.Movie


class movie_item_layout : Fragment() {
    lateinit var binding: FragmentMovieItemLayoutBinding
    lateinit var adapter: MovieAdapter
    lateinit var vm: HomeVM
    lateinit var  item: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieItemLayoutBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(HomeVM::class.java)
        return binding.root
    }
    companion object {
        fun newInstance(): NowPlaying = NowPlaying()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
    }

    private fun setUpMovieList() {

        binding.description.text = item.title?.trim()
        binding.status.text = item.overview?.trim()
        val urlImage = "https://image.tmdb.org/t/p/w500${item.posterPath}"
        this.context?.let {
            Glide.with(it).load(urlImage)
                .into(binding.avatar)
        }
    }

    override fun onStart() {
        super.onStart()
        vm.getNowPlaying()
    }
    private fun registerMovieList() {
        vm.movieData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}