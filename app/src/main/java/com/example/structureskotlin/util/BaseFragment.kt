package com.example.structureskotlin.util

import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.structureskotlin.remote.Resource

open class BaseFragment(layoutId: Int) : Fragment(layoutId){
    fun handleLoader(resource: Resource<Any>, showLoader: Boolean = true, swipeRefreshLayout: SwipeRefreshLayout? = null, successResponse: (Resource<Any>) -> Unit) {
        (this.activity as BaseActivity).handleLoader(resource, showLoader, swipeRefreshLayout = swipeRefreshLayout) { response ->
            successResponse(response)
        }
    }
}