package com.example.structureskotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.structureskotlin.R
import com.example.structureskotlin.databinding.ActivityMainBinding
import com.example.structureskotlin.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    val mBinding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
        /*    addHomeTownViewModel.updateProfiledBirthDate.observe(this@AddBirthDateActivity) {
                handleLoader(it) { successResponse ->
                    (successResponse.data as Status).apply {
                        if (status.equals(APIConst.success, true)) {
                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
            }*/


     /*       tvSave.setOnClickListener {
                if (GeneralFunctions.validEmptyTextView(this@AddBirthDateActivity, listOf(Pair(tvFromDate, getString(R.string.select_birthdate_required))))) {
                    if (userByIdAndAuthcodeResponse != null) {
                        addHomeTownViewModel.updateProfiledBirthDate(
                            this@AddBirthDateActivity, hashMapOf(
                                Pair(APIConst.iUserId, prefUtil.getInt(PrefUtil.iUserId)),
                                Pair(APIConst.dBirthDate, tvFromDate.text.toString())))

                    } else {
                        addHomeTownViewModel.updateProfiledBirthDate(
                            this@AddBirthDateActivity, hashMapOf(
                                Pair(APIConst.iUserId, prefUtil.getInt(PrefUtil.iUserId)),
                                Pair(APIConst.dBirthDate, tvFromDate.text.toString())
                            )
                        )
                    }
                }
            }*/
        }
    }
}