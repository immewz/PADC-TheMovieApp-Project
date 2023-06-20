package com.padcmyanmar.mewz.mymovieapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.mewz.mymovieapp.adapters.MovieAdapter
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMovieDetailBinding
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMovieSearchBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.MovieViewHolderDelegate
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    private lateinit var binding: ActivityMovieSearchBinding
    private lateinit var mMovieAdapter: MovieAdapter

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context,MovieSearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
        setUpRecyclerView()
    }

    @SuppressLint("CheckResult")
    private fun setUpListeners() {
        binding.etSearch.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            },{
                showError(it.localizedMessage ?: "")
            })
    }

    private fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        binding.rvMovies.adapter = mMovieAdapter
        binding.rvMovies.layoutManager = GridLayoutManager(this,2)
    }

    override fun onTapMovie(movieId: Int) {
    }
}