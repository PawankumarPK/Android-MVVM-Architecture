package com.handypawan.mvvmexampleproject.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by pawan on 03,June,2020
 */

fun Context.toast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}