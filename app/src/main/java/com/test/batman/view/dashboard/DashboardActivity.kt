package com.test.batman.view.dashboard

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.batman.R
import com.test.batman.extensions.addFragmentInActivity
import com.test.batman.extensions.replaceFragmentInActivity
import com.test.batman.view.dashboard.detail.MovieDetailFragment
import com.test.batman.view.dashboard.movies.MoviesFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dashboard)
        super.onCreate(savedInstanceState)

        gotoDashboard()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun gotoDashboard() {
        replaceFragmentInActivity(
            R.id.container,
            MoviesFragment { id, title, posterUrl, year ->
                gotoDetail(id, title, posterUrl, year)
            },
            MoviesFragment.TAG
        )
    }

    private fun gotoDetail(id: String, title:String, posterUrl: String, year: String) {
        addFragmentInActivity(
            R.id.container,
            MovieDetailFragment(id, title, posterUrl, year),
            MovieDetailFragment.TAG
        )
    }

    private var pressTwice = 1

    override fun onBackPressed() {
        when {
            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
            pressTwice < 2 -> {
                pressTwice += 1
                Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_LONG).show()

            }
            else -> this.finishAffinity()
        }
    }
}