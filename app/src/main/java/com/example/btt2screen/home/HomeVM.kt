package com.example.btt2screen.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btt2screen.DataStore
import com.example.btt2screen.R
import com.example.btt2screen.fragmentscreen.FragmentHome
import com.example.btt2screen.services.MovieRestClient
import com.example.btt2screen.model.Movie
import com.example.btt2screen.tabview.TabView
import kotlinx.coroutines.launch

class HomeVM : ViewModel() {
    private var _movieData: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val movieData: LiveData<List<Movie>>
        get() = _movieData

    private val _menuLayoutType: MutableLiveData<Int> = MutableLiveData(R.id.menu_item_linear)
    val menuLayoutType: LiveData<Int>
        get() = _menuLayoutType

    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp = MovieRestClient.getInstance().api.listNowPlayMovies(
                language = "en-US",
                page = 1,)
            _movieData.postValue(movieResp.results!!)
        }
    }

    fun getTopRateMovie() {
        viewModelScope.launch {
            val movieResp =
                MovieRestClient.getInstance().api.listTopRateMovies(
                    language = "en-US",
                    page = 1)
            _movieData.postValue(movieResp.results!!)
        }
    }

    //menu
    fun getLayout(Recycleview: TabView): RecyclerView.LayoutManager {
        return when (_menuLayoutType.value) {
            R.id.menu_item_linear -> LinearLayoutManager(Recycleview)
            else -> {
                GridLayoutManager(Recycleview, 2)
            }
        }
    }
    fun setLayout(itemId: Int) {
        _menuLayoutType.postValue(itemId)
    }
    fun handleItemWhenClicked(item: View, restaurant: Movie) {

    }

    fun handleItemWhenLongClicked(item: View) {
        /// TODO
    }

}