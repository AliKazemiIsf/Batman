package com.test.batman.view.dashboard.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.test.batman.R
import com.test.batman.view.base.BaseFragment
import com.test.batman.view.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment(
    private val callback: (String) -> Unit
) : BaseFragment() {

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
        viewModel.getBatmanMovies()
    }

    override fun handleObserver() {
        viewModel.listMovie.observe(viewLifecycleOwner, {
            loading(false)
            it.search?.let { list ->
                if (list.isNotEmpty())
                    rclMovie.adapter = Adapter(list) { id ->
                        callback.invoke(id)
                    }
                else
                    showNoData(true)
            }
        })
    }

    override fun handleError() {
        viewModel.error.observe(viewLifecycleOwner, {
            loading(false)
            showNoData(true)
        })
    }

    private fun loading(visible: Boolean) {
        prcMovie.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showNoData(visible: Boolean) {
        if (visible) {
            rclMovie.visibility = View.GONE
            noData.visibility = View.VISIBLE
        } else {
            rclMovie.visibility = View.VISIBLE
            noData.visibility = View.GONE
        }
    }

    companion object {
        val TAG = "batman: ${MoviesFragment::class.java.simpleName}"
    }
}