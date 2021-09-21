package com.programmer2704.unsplash.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<viewbinding : ViewBinding> : Fragment() {

    private var _b: viewbinding? = null
    protected val b: viewbinding get() = _b!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> viewbinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _b = bindingInflater(inflater, container, false)
        return _b!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _b = null
    }
}
