package me.tumur.portfolio.utils.extensions

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import me.tumur.portfolio.utils.delegates.ActivityBindingProperty

internal fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)