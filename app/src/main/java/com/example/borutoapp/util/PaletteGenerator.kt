package com.example.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator {
    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest
            .Builder(context = context)
            .data(imageUrl)
            .allowHardware(enable = false)
            .build()
        val imageResult = loader.execute(request)
        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            Constants.VIBRANT_COLOR to parseColorSwatchToColorString(Palette.from(bitmap).generate().vibrantSwatch),
            Constants.DARK_VIBRANT_COLOR to parseColorSwatchToColorString(Palette.from(bitmap).generate().darkVibrantSwatch),
            Constants.ON_DARK_VIBRANT_COLOR to parseColorIntToColorString(Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor)
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun parseColorSwatchToColorString(color: Palette.Swatch?): String {
        return if (color != null) {
            val parsedColorString = color.rgb.toHexString()
            return "#$parsedColorString"
        } else {
            "#000000"
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun parseColorIntToColorString(color: Int?): String {
        return if (color != null) {
            val parsedColorString = color.toHexString()
            return "#$parsedColorString"
        }  else {
            "#FFFFFF"
        }
    }
}