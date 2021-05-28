package com.yonder.statelayoutlib

import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

/**
 * @author: yusufonder
 * @date: 28/05/2021
 */
interface IStateLayoutView {

  //Error State View Components
  val ivError: ImageView
  val tvErrorTitle: TextView
  val tvErrorDetail: TextView
  val btnErrorTryAgain: Button

  //Loading State View Components
  val pbLoading: ProgressBar
  val tvLoading: TextView

  //Empty State View Components
  val ivEmpty: ImageView
  val tvEmptyTitle: TextView

}