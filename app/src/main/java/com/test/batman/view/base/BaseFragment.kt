package com.test.batman.view.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        handleObserver()
        handleError()
    }

    abstract fun setup()
    abstract fun handleError()
    abstract fun handleObserver()
}