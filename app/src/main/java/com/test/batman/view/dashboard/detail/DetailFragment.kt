package com.test.batman.view.dashboard.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.batman.R
import com.test.batman.data.network.model.MovieDetailResponse
import com.test.batman.view.base.BaseFragment
import com.test.batman.view.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment(
    private val id: String
) : BaseFragment() {

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(id)
        btnBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun handleObserver() {
        viewModel.listMovieDetail.observe(viewLifecycleOwner, Observer {
            loading(false)
            it?.let {
                showData(it)

            } ?: kotlin.run {
                showNoData(true)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun showData(model: MovieDetailResponse) {
        val activity = activity ?: return
        Glide.with(activity)
            .load(model.posterUrl)
            .into(thumbnail)

        txtTitle.text = "${model.title} (${model.year})"
        txtRated.text = model.rated
        txtReleased.text = model.released
        txtRuntime.text = model.runtime
        txtGenre.text = model.genre
        txtImdbRating.text = model.imdbRating
        txtImdbVotes.text = model.imdbVotes
        txtWebsite.text = model.website

        txtDirector.text = model.director
        txtActor.text = model.actors
        txtWriter.text = model.writer
        txtPlot.text = model.plot
        txtLanguage.text = model.language
        txtCountry.text = model.country
        txtAwards.text = model.awards
        txtBoxOffice.text = model.boxOffice
        txtProduction.text = model.production

        model.rating?.let { list ->
            if (list.isNotEmpty())
                rclRating.adapter = Adapter(list)
            else
                rclRating.visibility = View.GONE
        } ?: kotlin.run {
            rclRating.visibility = View.GONE
        }
    }

    override fun handleError() {
        viewModel.error.observe(viewLifecycleOwner, {
            loading(false)
            showNoData(true)
        })
    }

    private fun loading(visible: Boolean) {
        if (visible) {
            prcDetail.visibility = View.VISIBLE
            layoutDetail.visibility = View.GONE
        } else {
            prcDetail.visibility = View.GONE
            layoutDetail.visibility = View.VISIBLE
        }
    }

    private fun showNoData(visible: Boolean) {
        if (visible) {
            noData.visibility = View.VISIBLE
            layoutDetail.visibility = View.GONE
        } else {
            noData.visibility = View.GONE
            layoutDetail.visibility = View.VISIBLE
        }
    }

    companion object {
        val TAG = "batman: ${DetailFragment::class.java.simpleName}"
    }
}