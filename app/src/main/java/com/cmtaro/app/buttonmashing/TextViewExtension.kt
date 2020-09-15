package com.cmtaro.app.buttonmashing


import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("countText")
fun TextView.countText(count: Int) {
  text = "$count 回"
}
