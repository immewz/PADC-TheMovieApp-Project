package com.padcmyanmar.mewz.mymovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.adapters.BannerAdapter
import com.padcmyanmar.mewz.mymovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.mymovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMainBinding
import com.padcmyanmar.mewz.mymovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.mewz.mymovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.mewz.mymovieapp.mvp.views.MainView
import com.padcmyanmar.mewz.mymovieapp.rounters.navigateToMovieDetailActivity
import com.padcmyanmar.mewz.mymovieapp.rounters.navigateToMovieSearchActivity
import com.padcmyanmar.mewz.mymovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.mewz.mymovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(), MainView{

    private lateinit var binding: ActivityMainBinding

    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowCaseAdapter

    private lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod
    private lateinit var mMovieByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod

    private lateinit var mPresenter: MainPresenter // Presenter

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // Data
    private var mGenre: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter() // mvp

        setUpToolbar()
        setUpBannerViewPager()
        setUpListeners()
        setUpShowcaseRecyclerView()
        setUpViewPods()
        requestData()

        mPresenter.onUiReady(this) // mvp

    }

    // mvp
    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun requestData() {
        // Now Playing Movies
        mMovieModel.getNowPlayingMovies{
            showErrorMessage()
        }?.observe(this){
            mBannerAdapter.setNewData(it)
        }

        // Popular Movies
        mMovieModel.getPopularMovies{
            showErrorMessage()
        }?.observe(this){
            mBestPopularMovieListViewPod.setData(it)
        }

        // Top Rated Movies
        mMovieModel.getTopRatedMovies{
            showErrorMessage()
        }?.observe(this){
            mShowcaseAdapter.setNewData(it)
        }

        // Get Genre
        mMovieModel.getGenres(
            onSuccess = {
                mGenre = it
                setUpGenreTabLayout(it)

                // Get Movies By Genre For First Genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }

            },
            onFailure = {
                showErrorMessage()
            }
        )

        // Get Actor
        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )
    }

    private fun getMoviesByGenre(genreId: Int) {
        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
            onSuccess = {
                mMovieByGenreViewPod.setData(it)
            },
            onFailure = {
                showErrorMessage()
            })
    }

    private fun showErrorMessage() {
        Toast.makeText(this, "Failed",Toast.LENGTH_LONG).show()
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = binding.vpBestPopularMovieList.root
        mMovieByGenreViewPod = binding.vpMovieByGenre.root
        mActorListViewPod = binding.vpBestActor.root

        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)
        mActorListViewPod.setUpActorListViewPod()
    }

    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowCaseAdapter(mPresenter)
        binding.rvShowCases.adapter = mShowcaseAdapter
        binding.rvShowCases.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {
        binding.tabLayoutGenre.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position ?: 0) // mvp
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach{
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(mPresenter)
        binding.viewPagerBanner.adapter = mBannerAdapter

        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.search){
            navigateToMovieSearchActivity()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMoviesByGenre(movieByGenre: List<MovieVO>) {
        mMovieByGenreViewPod.setData(movieByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailScreen(movieId: Int) {
        navigateToMovieDetailActivity(movieId)
    }

    override fun showError(errorSting: String) {
        Toast.makeText(this, errorSting,Toast.LENGTH_LONG).show()
    }

}

