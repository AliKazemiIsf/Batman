package com.test.batman.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun AppCompatActivity.replaceFragmentInActivity(container: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.commit {
        replace(container, fragment, tag)
            .addToBackStack(tag)
    }
}

