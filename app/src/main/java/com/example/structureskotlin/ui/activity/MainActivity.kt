package com.example.structureskotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.structureskotlin.databinding.ActivityMainBinding
import com.example.structureskotlin.remote.APIConst
import com.example.structureskotlin.remote.GetAllPostResponse
import com.example.structureskotlin.ui.AddUniversityViewModel
import com.example.structureskotlin.util.BaseActivity
import com.example.structureskotlin.util.ConstantsVal
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){
    private val addUniversityViewModel by viewModels<AddUniversityViewModel>()
    private var getAllPostResponse:GetAllPostResponse?=null

    val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val addUniversityAdapter by lazy {
        TeamAboutCoachAdapter { position, viewId ->
            startActivity(Intent(this, UpdateActivity::class.java).apply {
                putExtra(
                    ConstantsVal.TEAM_HEAD_COACH_DETAILS,
                    Gson().toJson(getAllPostResponse!!.data[position])
                )

            })
            /*    if (viewId == R.id.imgEdit) {
                startActivityForResult(Intent(this, DisplayHomeActivity::class.java).apply {
                    putExtra(Constants.EMPLOYMENT_LIST_RESPONSE, Gson().toJson(createGetAllSecondarySchoolDataByiUserIdResponse.data[position]))
                    putExtra(APIConst.updates, "update")
                }, 113)


            }*/
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            addUniversityViewModel.callGetAllEducationDataByiUserAPI(this@MainActivity, hashMapOf(Pair(
                APIConst.iUserId,158 )))
            addUniversityViewModel.getAllEducationDataByiUserIdAPIResponse.observe(this@MainActivity) {
                handleLoader(it) { successResponse ->
                    getAllPostResponse = (successResponse.data as GetAllPostResponse)
                    addUniversityAdapter.setData(getAllPostResponse?.data ?: arrayListOf())
                }
            }
            recyclerAddItems.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = addUniversityAdapter
            }
     /*       tvSave.setOnClickListener {
                if (GeneralFunctions.validEmptyTextInputLayout(
                        listOf(
                            Triple(tilFirstName, etFirstName, getString(R.string.please_enter_firstname)),
                            Triple(tilLastName, etLastName, getString(R.string.please_enter_lastname))
                        )
                    ) && validate()) {
                    val params = hashMapOf(
                        Pair(APIConst.iUserId, prefUtil.getInt(APIConst.iUserId).toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                        Pair(APIConst.vFirstName, etFirstName.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                        Pair(APIConst.vLastName, etLastName.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                        Pair(APIConst.iRoleId, iRoleId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                        Pair(APIConst.dBirthDate, profileResponse.data.dBirthDate.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())))

                    updateProfileViewModel.callUpdateProfileAPI(
                        this@EditProfileActivity, params,
                        MultipartUtil.getImagePart(APIConst.vImage, profileImagePath),
                        MultipartUtil.getImagePart(APIConst.vUserBanner, bannerImagePath)
                    )

                }
            }*/
         /*   secondaryNumberViewModel.profileProfileUpdate(
                this@AddSecondaryEmailActivity, hashMapOf(
                    Pair(APIConst.iUserId, prefUtil.getInt(PrefUtil.iUserId)),
                    Pair(APIConst.vSecondaryEmail, etAddEmail.text.toString())
                )
            )*/
        }
    }
}