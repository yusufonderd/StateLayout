package com.yonder.statelayout.constraints

import androidx.constraintlayout.widget.ConstraintSet
import com.yonder.statelayout.StateLayoutView

/**
 * @author: yusufonder
 * @date: 28/05/2021
 */


internal fun <T : ConstraintSet> StateLayoutView.setIvEmpty(constraint: T) {
  constraint.apply {
    with(ivEmpty.id) {
      connect(this, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
      connect(this, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
      connect(this, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
      connect(this, ConstraintSet.BOTTOM, tvEmptyTitle.id, ConstraintSet.TOP, defaultMargin)
      setVerticalChainStyle(this, ConstraintSet.CHAIN_PACKED)
    }
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setTvEmpty(constraint: T) {
  constraint.apply {
    val startId = tvEmptyTitle.id
    with(ivEmpty.id) {
      connect(startId, ConstraintSet.START, this, ConstraintSet.START)
      connect(startId, ConstraintSet.END, this, ConstraintSet.END)
      connect(startId, ConstraintSet.TOP, this, ConstraintSet.BOTTOM)
    }
    connect(startId, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
  }
}