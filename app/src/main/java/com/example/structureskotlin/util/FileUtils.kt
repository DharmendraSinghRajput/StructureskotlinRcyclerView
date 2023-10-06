package com.example.structureskotlin.util

import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUtils {

    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File {
        val destinationFilename = File(context.filesDir.path + File.separatorChar.toString() + queryName(context, uri))
        try {
            context.contentResolver.openInputStream(uri).use { ins -> createFileFromStream(ins!!, destinationFilename) }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
        return destinationFilename
    }

    private fun createFileFromStream(inputStream: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while (inputStream.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor: Cursor = context.contentResolver.query(uri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }

    fun getVideoWidthAndHeight(videoPath: String): Pair<Int, Int> {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(videoPath)
        val videoWidth = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)!!.toInt()
        val videoHeight = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)!!.toInt()
        mediaMetadataRetriever.release()
        return Pair(videoWidth, videoHeight)
    }

    fun getOrientation(width: Int, height: Int): String {
        if (width >= height)
            return "Landscape"
        return "Portrait"
    }

}