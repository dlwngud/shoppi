package com.dlwngud.app.ui.common

import android.opengl.Visibility
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun updateVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if(isVisibility) View.VISIBLE else View.GONE
}