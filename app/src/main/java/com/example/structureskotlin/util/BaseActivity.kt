package com.example.structureskotlin.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.structureskotlin.R
import com.example.structureskotlin.remote.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    private lateinit var pickImageContract: ActivityResultLauncher<String>
    private lateinit var loader: Dialog
    private lateinit var captureImageContract: ActivityResultLauncher<Uri>
    private var resultUri: Uri? = null

    private var _imageUri = MutableLiveData<Uri>()
    var imageUri: LiveData<Uri> = _imageUri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filesDir.listFiles()?.let {
            for (file in it) {
                file.delete()
            }
        }

        loader = Dialog(this).apply {
            setContentView(R.layout.item_loader)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setCancelable(false)
        }


        pickImageContract = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                this._imageUri.value = it
            }
        }

        captureImageContract = registerForActivityResult(ActivityResultContracts.TakePicture()) { status ->
            if (status) {
                resultUri?.let {
                    this._imageUri.value = resultUri
                }

        }

        }

    }

    fun handleLoader(
        resource: Resource<Any>,
        showLoader: Boolean = true,
        swipeRefreshLayout: SwipeRefreshLayout? = null,
        successResponse: (Resource<Any>) -> Unit
    ) {
        swipeRefreshLayout?.isRefreshing = false
        when (resource) {
            is Resource.Error -> {
                Timber.v("okhttp: State Error")
                hideLoader()
            }

            is Resource.Loading -> {
                Timber.v("okhttp: State Loading")
                if (showLoader)
                    showLoader()
            }

            is Resource.NoInternet -> {
                Timber.v("okhttp: State NoInternet")
                hideLoader()
            }


            is Resource.Success -> {
//                FileReadWriteUtil(this).writeFileOnInternalStorage("API_Response.txt", GeneralFunctions.prettifyJson(Gson().toJson(resource.data))!!)
                Timber.v("okhttp: State Success")
                hideLoader()
                successResponse(resource)
            }
        }

    }

    fun showImagePickerDialog() {
        if (PermissionUtil.verifyPermissions(this)) {
            try {
                val imageItems = arrayOf<CharSequence>(
                    getString(R.string.take_picture),
                    getString(R.string.choose_from_gallery),
                    getString(R.string.cancel)
                )
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.select_one))
                builder.setItems(imageItems) { dialog, item ->
                    when {
                        imageItems[item] == getString(R.string.take_picture) -> {
                            dialog.dismiss()
                            createImageURI()?.let { uri ->
                                captureImage(uri)
//                                captureImage(Uri.parse(filesDir.path))
                            }
                        }

                        imageItems[item] == getString(R.string.choose_from_gallery) -> {
                            dialog.dismiss()
                            pickImage()
                        }

                        imageItems[item] == getString(R.string.cancel) -> {
                            dialog.dismiss()
                        }
                    }
                }
                builder.show()
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }
        }
    }

    fun showNewsMediaPickerDialog() {
        if (PermissionUtil.verifyPermissions(this)) {
            try {
                val imageItems = arrayOf<CharSequence>(
                    getString(R.string.image),
                    getString(R.string.video),
                    getString(R.string.pdf),
                    getString(R.string.cancel)
                )
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.select_one))
                builder.setItems(imageItems) { dialog, item ->
                    when {
                        imageItems[item] == getString(R.string.image) -> {
                            dialog.dismiss()
                            showImagePickerDialog()
                        }

                        imageItems[item] == getString(R.string.video) -> {
                            dialog.dismiss()
                          //  pickVideo()
                        }

                        imageItems[item] == getString(R.string.pdf) -> {
                            dialog.dismiss()
                            //pickPdf()
                        }

                        imageItems[item] == getString(R.string.cancel) -> {
                            dialog.dismiss()
                        }
                    }
                }
                builder.show()
            } catch (e: Resources.NotFoundException) {
                e.printStackTrace()
            }
        }
    }

    private fun createImageURI(): Uri? {

        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val imageName = "${getString(R.string.app_name)}_${System.currentTimeMillis()}"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val finalURI = contentResolver.insert(imageCollection, contentValues)
        resultUri = finalURI
        return finalURI
    }

    private fun pickImage() = pickImageContract.launch("image/*")

    private fun captureImage(uri: Uri) = captureImageContract.launch(uri)


        fun showLoader() {
            if (!loader.isShowing)
                loader.show()
        }

        fun hideLoader() {
            if (loader.isShowing)
                loader.dismiss()
        }

}
