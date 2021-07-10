package com.yonder.statelayout.constraints

import androidx.constraintlayout.widget.ConstraintSet
import com.yonder.statelayout.StateLayoutView
import androidx.constraintlayout.widget.ConstraintSet.BOTTOM
import androidx.constraintlayout.widget.ConstraintSet.CHAIN_PACKED
import androidx.constraintlayout.widget.ConstraintSet.TOP
import androidx.constraintlayout.widget.ConstraintSet.START
import androidx.constraintlayout.widget.ConstraintSet.END
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.yonder.statelayout.LoadingGravity

/**
 * @author: yusufonder
 * @date: 28/05/2021
 */

internal fun <T : ConstraintSet> StateLayoutView.setPbLoading(constraint: T) {
  constraint.apply {
    with(pbLoading.id) {
      connect(this, START, PARENT_ID, START)
      connect(this, END, PARENT_ID, END)
      connect(this, BOTTOM, tvLoading.id, TOP, defaultMargin)

      when (loadingGravity) {
        LoadingGravity.CENTER -> {
          connect(this, TOP, PARENT_ID, TOP)
          setVerticalChainStyle(this, CHAIN_PACKED)
        }
        LoadingGravity.TOP -> {
          connect(this, TOP, PARENT_ID, TOP, defaultMargin)
        }
        LoadingGravity.BOTTOM -> {
          connect(this, BOTTOM, tvLoading.id, TOP)
        }
      }
    }
  }
}

internal fun <T : ConstraintSet> StateLayoutView.setTvLoading(constraint: T) {
  constraint.apply {
    val startId = tvLoading.id
    with(pbLoading.id) {
      connect(startId, START, this, START)
      connect(startId, END, this, END)
    }
    when (loadingGravity) {
      LoadingGravity.CENTER -> {
        connect(startId, TOP, pbLoading.id, BOTTOM)
        connect(startId, BOTTOM, PARENT_ID, BOTTOM)
      }
      LoadingGravity.BOTTOM -> {
        connect(startId, BOTTOM, PARENT_ID, BOTTOM, defaultMargin)
      }
      LoadingGravity.TOP -> {
        connect(startId, TOP, pbLoading.id, BOTTOM, defaultMargin)
      }

    }

  }
}



