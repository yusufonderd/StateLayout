package com.yonder.statelayout

/**
 * @author: yusufonder
 * @date: 10/07/2021
 */
enum class LoadingGravity(val value: Int) {
  CENTER(0),
  TOP(1),
  BOTTOM(2);

  companion object {
    fun find(value: Int): LoadingGravity = values().find { it.value == value } ?: CENTER
  }
}