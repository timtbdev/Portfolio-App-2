package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.content.res.Configuration
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButton
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.constants.BsConstants
import me.tumur.portfolio.utils.constants.Constants
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.text.SimpleDateFormat
import java.util.*


/** Load image from the network or cache with placeholder and error images */
@BindingAdapter("imageLoad")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(it) {
            crossfade(true)
            placeholder(R.color.colorBorder)
            when (imageView.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    transformations(RoundedCornersTransformation(0.0F))
                } // Night mode is not active, we're using the light theme
                Configuration.UI_MODE_NIGHT_YES -> {
                    transformations(GrayscaleTransformation())
                } // Night mode is active, we're using dark theme
            }
        }
    }

}

/** Load image from the network or cache with placeholder and error images */
@BindingAdapter("android:src")
fun setImageDrawable(imageView: ImageView, @IdRes drawable: Int?) {
    drawable?.let {
        imageView.load(it) {
            crossfade(true)
            placeholder(R.color.colorBorder)
            when (imageView.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    transformations(RoundedCornersTransformation(0.0F))
                } // Night mode is not active, we're using the light theme
                Configuration.UI_MODE_NIGHT_YES -> {
                    transformations(GrayscaleTransformation())
                } // Night mode is active, we're using dark theme
            }
        }
    }
}

/** Date from and date to */
@BindingAdapter("dateFrom", "dateTo", requireAll = true)
fun TextView.setDateFromTo(dateFrom: Date?, dateTo: Date?) {
    if (dateFrom != null && dateTo != null) {

        if (dateFrom.compareTo(dateTo) == 0) {
            val outputFormat = SimpleDateFormat("MMM yyyy", Locale.US)
            val a = outputFormat.format(dateFrom)
            text = a
        } else {

            val outputFormat = SimpleDateFormat("MMM yyyy", Locale.US)
            val a = outputFormat.format(dateFrom)
            val b = outputFormat.format(dateTo)

            val outputFormatC = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val ac = outputFormatC.format(dateFrom)
            val bc = outputFormatC.format(dateTo)

            val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val start = LocalDate.parse(ac, dateFormatter)
            val end = LocalDate.parse(bc, dateFormatter)

            if (start != null && end != null) {
                val diff: Long = ChronoUnit.MONTHS.between(start, end)
                val diffYear = diff / 12
                val diffMonth = diff % 12
                val d = if (diffYear > 0) "$diffYear.$diffMonth year(s)" else "$diffMonth month(s)"

                val result = "$a - $b | $d"
                text = result
            }
        }
    }
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