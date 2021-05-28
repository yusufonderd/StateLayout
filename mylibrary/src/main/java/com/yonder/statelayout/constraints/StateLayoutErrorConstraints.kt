package com.yonder.statelayoutlib.constraints

import androidx.constraintlayout.widget.ConstraintSet

import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.CHAIN_PACKED
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.yonder.statelayoutlib.StateLayoutView

/**
 * @author: yusufonder
 * @date: 26/05/2021
 */

internal fun <T : ConstraintSet> StateLayoutView.setIvErrorLocation(constraint: T) {
  constraint.apply {
    with(ivError.id) {
      connect(this, START, PARENT_ID, START)
      connect(this, END, PARENT_ID, END)
      connect(this, TOP, PARENT_ID, TOP)
      connect(this, BOTTOM, tvErrorTitle.id, TOP,defaultMargin)
      setVerticalChainStyle(this, CHAIN_PACKED)
    }
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setTvErrorTitleLocation(constraint: T) {
  constraint.apply {
    val startId = tvErrorTitle.id
    with(ivError.id) {
      connect(startId, START, this, START)
      connect(startId, END, this, END)
      connect(startId, TOP, this, BOTTOM)
    }
    connect(startId, BOTTOM, tvErrorDetail.id, TOP, defaultMargin)
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setTvErrorDetailLocation(constraint: T) {
  constraint.apply {
    val startId = tvErrorDetail.id
    with(tvErrorTitle.id) {
      connect(startId, START, this, START)
      connect(startId, END, this, END)
      connect(startId, TOP, this, BOTTOM)
    }
    connect(startId, BOTTOM, btnErrorTryAgain.id, TOP, defaultMargin)
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setBtnErrorTryAgainLocation(constraint: T) {
  constraint.apply {
    val startId = btnErrorTryAgain.id
    with(tvErrorDetail.id) {
      connect(startId, START, this, START)
      connect(startId, END, this, END)
      connect(startId, TOP, this, BOTTOM)
    }
    connect(startId, BOTTOM, PARENT_ID, BOTTOM)
  }
}


