package com.example.popularmovies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MovieAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

const val MOVIE = "MOVIE"

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) {movie ->
        startDetailActivity(movie)
    }

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModels()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            val year = etYear.text.toString()

            if (year.toInt() >= 1990 && year.toInt() <= 2022) viewModel.getMovies(year)
            else Snackbar.make(rvMovies, "Please choose year from 1995 till 2019", Snackbar.LENGTH_LONG).show()


        }
        rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = movieAdapter
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        //observe the movies
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })

        //observe errors
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }



    private fun startDetailActivity(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE, movie)
        startActivity(intent)
    }
}
