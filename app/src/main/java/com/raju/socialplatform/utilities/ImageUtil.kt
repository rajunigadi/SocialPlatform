package com.raju.socialplatform.utilities

import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.Target
import com.raju.socialplatform.R
import com.raju.socialplatform.di.modules.base.GlideApp

object ImageUtil {

    fun loadProfileImage(path: String, imageView: ImageView) {
        if (!TextUtils.isEmpty(path)) {
            GlideApp.with(imageView.context)
                    .load(path)
                    .override(60)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(CircleCrop())
                    .into(imageView)
        }
    }

    fun loadImage(path: String, imageView: ImageView) {
        if (!TextUtils.isEmpty(path)) {
            GlideApp.with(imageView.context)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView)
        }
    }
}