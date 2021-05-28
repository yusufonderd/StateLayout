package com.yonder.statelayoutlib.constraints

import androidx.constraintlayout.widget.ConstraintSet
import com.yonder.statelayoutlib.StateLayoutView
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.CHAIN_PACKED
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID

/**
 * @author: yusufonder
 * @date: 28/05/2021
 */

internal fun <T : ConstraintSet> StateLayoutView.setPbLoading(constraint: T) {
  constraint.apply {
    with(pbLoading.id) {
      connect(this, START, PARENT_ID, START)
      connect(this, END, PARENT_ID, END)
      connect(this, TOP, PARENT_ID, TOP)
      connect(this, BOTTOM, tvLoading.id, TOP, defaultMargin)
      setVerticalChainStyle(this, CHAIN_PACKED)
    }
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setTvLoading(constraint: T) {
  constraint.apply {
    val startId = tvLoading.id
    with(pbLoading.id) {
      connect(startId, START, this, START)
      connect(startId, END, this, END)
      connect(startId, TOP, this, BOTTOM)
    }
    connect(startId, BOTTOM, PARENT_ID, BOTTOM)
  }
}



