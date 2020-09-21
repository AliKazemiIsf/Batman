package com.test.batman.view.dashboard.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.batman.R
import com.test.batman.data.local.entity.MovieDetail
import com.test.batman.data.local.entity.Rating
import com.test.batman.enums.Status
import com.test.batman.extensions.isConnected
import com.test.batman.view.base.BaseFragment
import com.test.batman.view.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.layout_no_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment(
    private val imdbId: String,
    private val title: String,
    private val posterUrl: String,
    private val year: String
) : BaseFragment(), View.OnClickListener {

    private val viewModel by viewModel<DashboardViewModel>()

    override fun setup() {
        rclRating.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rclRating.setHasFixedSize(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        btnRefresh.setOnClickListener(this)
        btnBack.setOnClickListener(this)
    }

    private fun getData() {
        val activity = activity ?: return

        if (activity.isConnected) {
            viewModel.getMovieDetailOnline(imdbId)

            viewModel.listMovieDetail.observe(viewLifecycleOwner, {
                it?.let {
                    status(Status.SUCCESS)
                    showData(
                        MovieDetail().apply {
                            this.xImdbId = it.imdbId ?: ""
                            this.xWriter = it.writer
                            this.xWebsite = it.website
                            this.xRuntime = it.runtime
                            this.xReleased = it.released
                            this.xRated = it.rated
                            this.xProduction = it.production
                            this.xPlot = it.plot
                            this.xMetaScore = it.metaScore
                            this.xLanguage = it.language
                            this.xImdbVotes = it.imdbVotes
                            this.xImdbRating = it.imdbRating
                            this.xGenre = it.genre
                            this.xDvd = it.dvd
                            this.xDirector = it.director
                            this.xCountry = it.country
                            this.xBoxOffice = it.boxOffice
                            this.xAwards = it.awards
                            this.xActors = it.actors
                        }
                    )

                    val list = mutableListOf<Rating>()
                    it.rating?.forEach { rating ->
                        list.add(Rating().apply {
                            this.xImdbId = it.imdbId ?: ""
                            this.xSource = rating.source
                            this.xValue = rating.value
                        })
                    }
                    showRatings(list)

                } ?: kotlin.run {
                    status(Status.FAILURE)
                }
            })
        } else {
            viewModel.movieDetailOffline(imdbId).observe(viewLifecycleOwner, {
                it?.let {
                    status(Status.SUCCESS)
                    showData(it)

                } ?: kotlin.run {
                    status(Status.FAILURE)
                }
            })
            viewModel.movieRatings(imdbId).observe(viewLifecycleOwner, {
                showRatings(it)
            })
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnBack -> activity?.onBackPressed()
            R.id.btnRefresh -> {
                status(Status.LOADING)
                getData()
            }
        }
    }

    private fun showRatings(list: List<Rating>) {
        if (list.isNotEmpty())
            rclRating.adapter = Adapter(list)
        else
            rclRating.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun showData(model: MovieDetail) {
        val activity = activity ?: return
        Glide.with(activity)
            .load(posterUrl)
            .into(thumbnail)

        txtTitle.text = "$title (${year})"
        txtRated.text = model.xRated
        txtReleased.text = model.xReleased
        txtRuntime.text = model.xRuntime
        txtGenre.text = model.xGenre
        txtImdbRating.text = model.xImdbRating
        txtImdbVotes.text = model.xImdbVotes
        txtWebsite.text = model.xWebsite

        txtDirector.text = model.xDirector
        txtActor.text = model.xActors
        txtWriter.text = model.xWriter
        txtPlot.text = model.xPlot
        txtLanguage.text = model.xLanguage
        txtCountry.text = model.xCountry
        txtAwards.text = model.xAwards
        txtBoxOffice.text = model.xBoxOffice
        txtProduction.text = model.xProduction
    }

    override fun handleError() {
        viewModel.error.observe(viewLifecycleOwner, {
            status(Status.FAILURE)
        })
    }

    private fun status(status: Status) {
        when (status) {
            Status.LOADING -> {
                prcDetail.visibility = View.VISIBLE
                layoutDetail.visibility = View.GONE
                noData.visibility = View.GONE
            }
            Status.SUCCESS -> {
                prcDetail.visibility = View.GONE
                layoutDetail.visibility = View.VISIBLE
                noData.visibility = View.GONE
            }
            Status.FAILURE -> {
                prcDetail.visibility = View.GONE
                layoutDetail.visibility = View.GONE
                noData.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        val TAG = "batman: ${MovieDetailFragment::class.java.simpleName}"
    }
}