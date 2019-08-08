package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.state.Empty
import me.tumur.portfolio.utils.state.FavoriteState
import me.tumur.portfolio.utils.state.NotEmpty
import java.text.SimpleDateFormat
import java.util.*

/** Empty Screen */
@BindingAdapter("screenFavoriteEmpty")
fun setScreenFavoriteEmpty(view: View, screen: FavoriteState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when (screen != null && screen is Empty) {
        true -> {
            view.visibility = View.VISIBLE
            view.startAnimation(fadeIn)
        }
        false -> {
            view.visibility = View.GONE
            view.startAnimation(fadeOut)
        }
    }
}

/** Not Empty Screen */
@BindingAdapter("screenFavoriteNotEmpty")
fun setScreenFavoriteNotEmpty(view: View, screen: FavoriteState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when (screen != null && screen is NotEmpty) {
        true -> {
            view.visibility = View.VISIBLE
            view.startAnimation(fadeIn)
        }
        false -> {
            view.visibility = View.GONE
            view.startAnimation(fadeOut)
        }
    }
}

/** Date converter */
@BindingAdapter("dateConverter")
fun TextView.setDateConverter(date: Date?) {
    date?.let {
        val outputFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
        val formattedDate = outputFormat.format(it)
        formattedDate?.let { result ->
            text = result
        }
    }
}