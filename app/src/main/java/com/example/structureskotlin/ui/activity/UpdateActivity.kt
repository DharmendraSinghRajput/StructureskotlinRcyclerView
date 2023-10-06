package com.example.structureskotlin.ui.activity

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.example.structureskotlin.databinding.ActivityUpdateBinding
import com.example.structureskotlin.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateActivity : BaseActivity() {
    private var isPickingProfileImage = true
    private lateinit var pickImageContract: ActivityResultLauncher<String>
    private var resultUri: Uri? = null
    private lateinit var captureImageContract: ActivityResultLauncher<Uri>
    private var profileImagePath = ""

    val mBinding by lazy { ActivityUpdateBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

       /*     ivProfile.setOnClickListener {
                isPickingProfileImage = true
                showImagePickerDialog()
            }*/

          /*  imageUri.observe(this@UpdateActivity) {
                try {
                    if (isPickingProfileImage) {
                        Glide.with(this@UpdateActivity).load(it).into(ivProfile)
                        profileImagePath = FileUtils.getFile(applicationContext, it).path
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }*/

            btnImageUpload.setOnClickListener {
                isPickingProfileImage = true
               showImagePickerDialog()

            }

            /*iUserId: 158
            vFirstName: Dharm
            vLastName: Rajput*/

            // {"status":"success","code":"200","message":"Nothing to Update","data":null}

            /*     btnUpdate.setOnClickListener {
                val params = hashMapOf(
                    Pair(APIConst.iUserId, prefUtil.getInt(APIConst.iUserId).toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                    Pair(APIConst.vFirstName, etFirstName.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                    Pair(APIConst.vLastName, etLastName.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                    Pair(APIConst.iRoleId, iRoleId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())),
                    Pair(APIConst.dBirthDate, profileResponse.data.dBirthDate.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())))

                updateProfileViewModel.callUpdateProfileAPI(
                    this@UpdateActivity, params,
                    MultipartUtil.getImagePart(APIConst.vImage, profileImagePath),
                    MultipartUtil.getImagePart(APIConst.vUserBanner, bannerImagePath)
                )
            }*/

        }
    }
}