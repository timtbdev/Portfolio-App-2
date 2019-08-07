package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.button.MaterialButton
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.constants.BsConstants
import me.tumur.portfolio.utils.constants.Constants


/** Load image from the network or cache with placeholder and error images */
@BindingAdapter("imageUrl", "placeholder", requireAll = false)
fun setImage(imageView: ImageView, url: String?, placeholderDrawable: Drawable?) {

    // Build requestOptions for Glide
    val requestOptions = RequestOptions()
    if (placeholderDrawable != null) requestOptions.placeholder(placeholderDrawable)

    // Load image into imageView
    if (url != null)
        imageView.doOnLayout {
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .placeholder(R.color.colorPreferenceItemBorder)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
}

/** SVG Icon */
@BindingAdapter("icon")
fun setSvgIcon(view: ImageView, @DrawableRes icon: Int) {
    view.setImageResource(icon)
}

/** Load web view url */
@BindingAdapter("webUrl")
fun setWebView(web: WebView, url: String?) {
    url?.let {
        web.loadUrl(url)
    }
}

/** Date from and date to */
@BindingAdapter("dateFrom", "dateTo", requireAll = true)
fun TextView.setDateFromTo(dateFrom: String?, dateTo: String?) {
    val date = "$dateFrom - $dateTo"
    text = date
}

/** Social icon */
@BindingAdapter("socialIcon")
fun ImageView.setSocialIcon(name: String?) {
    name?.let {
        setImageResource( when(name){
            BsConstants.GITHUB -> R.drawable.ic_github
            BsConstants.LINKEDIN -> R.drawable.ic_linkedin
            BsConstants.TWITTER -> R.drawable.ic_twitter
            BsConstants.PDF -> R.drawable.ic_pdf
            else -> R.drawable.ic_globe
        })
    }
}

/** Category icon */
@BindingAdapter("categoryIcon")
fun ImageView.setCategoryIcon(icon: String?) {
    icon?.let {
        setImageResource(
            when (icon) {
                Constants.CATEGORY_ANDROID -> R.drawable.ic_category_android
                Constants.CATEGORY_WEB -> R.drawable.ic_category_web
                Constants.CATEGORY_CODE -> R.drawable.ic_category_code
                else -> R.drawable.ic_category_structure
            }
        )
    }
}

/** App icon */
@BindingAdapter("appIcon")
fun ImageView.setAppIcon(order: Int?) {
    order?.let {
        setImageResource( when(order){
            1 -> R.drawable.ic_app_info_current
            2 -> R.drawable.ic_app_info_update
            else -> R.drawable.ic_app_info_date
        })
    }
}

@BindingAdapter("buttonIcon")
fun MaterialButton.setButtonIcon(type: String?) {
    type?.let {
        setIconResource(
            when (type) {
                Constants.BUTTON_GITHUB -> R.drawable.ic_github
                Constants.BUTTON_GOOGLE -> R.drawable.ic_play_store
                Constants.BUTTON_WEB -> R.drawable.ic_category_web
                Constants.BUTTON_YOUTUBE -> R.drawable.ic_youtube
                Constants.BUTTON_TWITTER -> R.drawable.ic_twitter
                else -> R.drawable.ic_pdf
            }
        )
    }
}

@BindingAdapter("buttonBackground")
fun MaterialButton.setButtonBackground(type: String?) {
    type?.let {
        backgroundTintList = when (type) {
            Constants.BUTTON_GITHUB -> ContextCompat.getColorStateList(context, R.color.button_github_color)
            Constants.BUTTON_GOOGLE -> ContextCompat.getColorStateList(context, R.color.button_google_color)
            Constants.BUTTON_WEB -> ContextCompat.getColorStateList(context, R.color.button_web_color)
            Constants.BUTTON_TWITTER -> ContextCompat.getColorStateList(context, R.color.button_twitter_color)
            else -> ContextCompat.getColorStateList(context, R.color.button_youtube_color)
        }
    }
}