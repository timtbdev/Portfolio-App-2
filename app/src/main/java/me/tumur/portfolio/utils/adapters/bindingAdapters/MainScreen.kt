package me.tumur.portfolio.utils.adapters.bindingAdapters

import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.state.*

/** BINDING ADAPTERS FOR MAIN SCREEN */

/** Splash Screen */
@BindingAdapter("screenSplash")
fun setScreenSplash(view: View, screen: ScreenState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when(screen != null && screen is SplashScreen){
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

/** Loader Screen */
@BindingAdapter("screenLoader")
fun setScreenLoader(view: View, screen: ScreenState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when(screen != null && screen is LoaderScreen){
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

/** Host Screen */
@BindingAdapter("screenHost")
fun setScreenHost(view: View, screen: ScreenState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when(screen != null && screen is MainScreen || screen is WelcomeScreen){
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

/** Main Screen */
@BindingAdapter("screenMain")
fun setScreenMain(view: View, screen: ScreenState?) {
    val fadeIn = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
    val fadeOut = AnimationUtils.loadAnimation(view.context, R.anim.fade_out)
    when(screen != null && screen is MainScreen || screen is WelcomeScreen || screen is LoaderScreen){
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

/** Hide or Show Navigation */
@BindingAdapter("navigation")
fun hideOrShowNavigation(view: View, state :NavigationState) {
    view.visibility = if(state is HideNavigation) View.INVISIBLE else View.VISIBLE
}

/** BINDING ADAPTER FOR LOADER * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/** Animated loader icon */
@BindingAdapter("loaderIcon", "screenState")
fun setLoaderIcon(view: ImageView, @DrawableRes loaderIcon: Int, screenState: ScreenState?) {
    if (screenState != null && screenState is LoaderScreen) {
        val avdLoader = AnimatedVectorDrawableCompat.create(view.context, loaderIcon)!!
        avdLoader.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                when(screenState){
                    LoaderScreen -> avdLoader.start()
                    else -> avdLoader.stop()
                }
            }
        })
        view.setImageDrawable(avdLoader)
        avdLoader.start()
    }
}