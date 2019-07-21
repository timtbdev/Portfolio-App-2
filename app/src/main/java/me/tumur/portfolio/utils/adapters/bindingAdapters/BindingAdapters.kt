package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.constants.BsConstants


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
                .placeholder(R.color.colorOnSurfaceSecondary)
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