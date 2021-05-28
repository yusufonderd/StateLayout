package com.yonder.statelayoutlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val stateLayoutView = findViewById<StateLayoutView>(R.id.errorStateLayoutView)

    stateLayoutView.setOnTryAgainClickListener(object : StateLayoutListener.TryAgainClickListener {
      override fun onClickTryAgain() {
        Toast.makeText(this@MainActivity, "Clicked try again button", Toast.LENGTH_LONG).show()
      }
    })


    findViewById<Button>(R.id.btnShowError).setOnClickListener {
      stateLayoutView.setState(State.ERROR)
    }

    findViewById<Button>(R.id.btnShowLoading).setOnClickListener {
      stateLayoutView.setState(State.LOADING)
    }

    findViewById<Button>(R.id.btnContent).setOnClickListener {
      stateLayoutView.setState(State.CONTENT)
    }

    findViewById<Button>(R.id.btnEmpty).setOnClickListener {
      stateLayoutView.setState(State.EMPTY)
    }


    stateLayoutView.setState(State.LOADING)

  }

}