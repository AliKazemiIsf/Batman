package com.test.batman.view.dashboard.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.test.batman.R
import com.test.batman.data.local.entity.Movie
import com.test.batman.enums.Status
import com.test.batman.extensions.isConnected
import com.test.batman.view.base.BaseFragment
import com.test.batman.view.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.layout_no_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment(
    private val callback: (String, String, String, String) -> Unit
) : BaseFragment(), View.OnClickListener {

    private val viewModel by viewModel<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun setup() {
        rclMovie.layoutManager = GridLayoutManager(activity, 3)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        btnRefresh.setOnClickListener(this)
    }

    private fun getData() {
        val activity = activity ?: return

        if (activity.isConnected) {
            viewModel.getMoviesOnline()
            viewModel.listMovie.observe(viewLifecycleOwner, {
                it.search?.let { responseList ->
                    status(Status.SUCCESS)
                    rclMovie.adapter = Adapter(
                        mutableListOf<Movie>().apply {
                            responseList.forEach { item ->
                                add(Movie().apply {
                                    this.xTitle = item.title
                                    this.xType = item.type
                                    this.xImdbId = item.imdbId ?: ""
                                    this.xPosterUrl = item.posterUrl
                                    this.xYear = item.year
                                })
                            }
                        })
                    { id, title, posterUrl, year ->
                        callback.invoke(id, title, posterUrl, year)
                    }

                } ?: kotlin.run {
                    status(Status.FAILURE)
                }
            })
        } else {
            viewModel.moviesOffline().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    status(Status.SUCCESS)
                    rclMovie.adapter = Adapter(it) { id, title, posterUrl, year ->
                        callback.invoke(id, title, posterUrl, year)
                    }
                } else
                    status(Status.FAILURE)
            })
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnRefresh -> {
                status(Status.LOADING)
                getData()
            }
        }
    }

    override fun handleError() {
        viewModel.error.observe(viewLifecycleOwner, {
            status(Status.FAILURE)
        })
    }

    private fun status(status: Status) {
        when (status) {
            Status.FAILURE ->{
                prcMovie.visibility = View.GONE
                rclMovie.visibility = View.GONE
                noData.visibility = View.VISIBLE
            }
            Status.SUCCESS ->{
                prcMovie.visibility = View.GONE
                rclMovie.visibility = View.VISIBLE
                noData.visibility = View.GONE
            }
            Status.LOADING ->{
                prcMovie.visibility = View.VISIBLE
                rclMovie.visibility = View.GONE
                noData.visibility = View.GONE
            }
        }
    }

    companion object {
        val TAG = "batman: ${MoviesFragment::class.java.simpleName}"
    }
}