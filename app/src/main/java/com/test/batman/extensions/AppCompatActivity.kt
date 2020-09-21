package com.test.batman.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.test.batman.R

fun AppCompatActivity.replaceFragmentInActivity(container: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.commit {
        replace(container, fragment, tag)
            .addToBackStack(tag)
    }
}

fun AppCompatActivity.addFragmentInActivity(container: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.commit {
             setCustomAnimations(
             R.anim.enter_anim,
             R.anim.exit_anim
         )
        add(container, fragment, tag)
            .addToBackStack(tag)
    }
}

