package com.example.m2_layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.m2_layout.databinding.MyCustomViewBinding

class MyCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = MyCustomViewBinding.inflate(LayoutInflater.from(context))


    init {
        inflate(context, R.layout.my_custom_view, this)
    }

    fun setTopText(text: String) {
     binding.text1.text = text;
    }

    fun setBottomText(text: String) {
        binding.text2.text = text;

    }



}

