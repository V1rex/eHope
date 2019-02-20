package com.v1rex.ehope.Utilities

import android.graphics.Bitmap
import android.provider.MediaStore
import android.media.ExifInterface
import android.graphics.BitmapFactory
import android.content.res.AssetFileDescriptor
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileNotFoundException


object ImagePicker {

    private val DEFAULT_MIN_WIDTH_QUALITY = 400        // min pixels
    private val TAG = "ImagePicker"
    private val TEMP_IMAGE_NAME = "tempImage"

    var minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY

    fun getImageFromResult(context: Context, resultCode: Int,
                           imageReturnedIntent: Intent?): Bitmap? {
        Log.d(TAG, "getImageFromResult, resultCode: $resultCode")
        var bm: Bitmap? = null
        val imageFile = getTempFile(context)
        if (resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri?
            val isCamera = imageReturnedIntent == null ||
                    imageReturnedIntent.data == null ||
                    imageReturnedIntent.data == Uri.fromFile(imageFile)
            if (isCamera) {
                /** CAMERA  */
                selectedImage = Uri.fromFile(imageFile)
            } else {
                /** ALBUM  */
                selectedImage = imageReturnedIntent!!.data
            }
            Log.d(TAG, "selectedImage: " + selectedImage!!)

            bm = getImageResized(context, selectedImage)
            val rotation = getRotation(context, selectedImage, isCamera)
            bm = rotate(bm, rotation)
        }
        return bm
    }


    private fun decodeBitmap(context: Context, theUri: Uri, sampleSize: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inSampleSize = sampleSize

        var fileDescriptor: AssetFileDescriptor? = null
        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(theUri, "r")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
                fileDescriptor!!.fileDescriptor, null, options)

        Log.d(TAG, options.inSampleSize.toString() + " sample method bitmap ... " +
                actuallyUsableBitmap.width + " " + actuallyUsableBitmap.height)

        return actuallyUsableBitmap
    }

    /**
     * Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
     */
    private fun getImageResized(context: Context, selectedImage: Uri): Bitmap {
        var bm: Bitmap? = null
        val sampleSizes = intArrayOf(5, 3, 2, 1)
        var i = 0
        do {
            bm = decodeBitmap(context, selectedImage, sampleSizes[i])
            Log.d(TAG, "resizer: new bitmap width = " + bm.width)
            i++
        } while (bm!!.width < minWidthQuality && i < sampleSizes.size)
        return bm
    }


    private fun getRotation(context: Context, imageUri: Uri, isCamera: Boolean): Int {
        val rotation: Int
        if (isCamera) {
            rotation = getRotationFromCamera(context, imageUri)
        } else {
            rotation = getRotationFromGallery(context, imageUri)
        }
        Log.d(TAG, "Image rotation: $rotation")
        return rotation
    }

    private fun getRotationFromCamera(context: Context, imageFile: Uri): Int {
        var rotate = 0
        try {

            context.getContentResolver().notifyChange(imageFile, null)
            val exif = ExifInterface(imageFile.getPath())
            val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rotate
    }

    fun getRotationFromGallery(context: Context, imageUri: Uri): Int {
        val columns = arrayOf(MediaStore.Images.Media.ORIENTATION)
        val cursor = context.getContentResolver().query(imageUri, columns, null, null, null)
                ?: return 0

        cursor.moveToFirst()

        val orientationColumnIndex = cursor.getColumnIndex(columns[0])
        return cursor.getInt(orientationColumnIndex)
    }


    private fun rotate(bm: Bitmap?, rotation: Int): Bitmap? {
        if (rotation != 0) {
            val matrix = Matrix()
            matrix.postRotate(rotation.toFloat())
            return Bitmap.createBitmap(bm, 0, 0, bm!!.width, bm.height, matrix, true)
        }
        return bm
    }


    private fun getTempFile(context: Context): File {
        val imageFile = File(context.getExternalCacheDir(), TEMP_IMAGE_NAME)
        imageFile.getParentFile().mkdirs()
        return imageFile
    }
}