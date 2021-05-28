package com.yonder.statelayoutlib.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author: yusufonder
 * @date: 28/05/2021
 */

val Number.toPx get() = TypedValue.applyDimension(
  TypedValue.COMPLEX_UNIT_DIP,
  this.toFloat(),
  Resources.getSystem().displayMetrics)