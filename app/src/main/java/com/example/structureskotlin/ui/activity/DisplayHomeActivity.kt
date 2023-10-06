package com.example.structureskotlin.ui.activity

import android.os.Bundle
import com.example.structureskotlin.databinding.ActivityDisplayHomeBinding
import com.example.structureskotlin.remote.GetAllPostResponse
import com.example.structureskotlin.util.BaseActivity
import com.example.structureskotlin.util.ConstantsVal
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayHomeActivity : BaseActivity() {
    private var getAllPostResponse: GetAllPostResponse.Data?=null

    private val mBinding by lazy { ActivityDisplayHomeBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            intent.extras?.let {
                getAllPostResponse = Gson().fromJson(it.getString(ConstantsVal.TEAM_HEAD_COACH_DETAILS), GetAllPostResponse.Data::class.java)
                GeneralFunctions.loadImage(this@DisplayHomeActivity,getAllPostResponse!!.user.vImage,ivPostDisplay)
            }

        }
    }
}

