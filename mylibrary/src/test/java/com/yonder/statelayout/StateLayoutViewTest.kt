package com.yonder.statelayout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewTreeObserver
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author: yusufonder
 * @date: 29/05/2021
 */
class StateLayoutViewTest {

  @MockK
  private lateinit var context: Context
  @MockK
  private lateinit var viewTreeObserver: ViewTreeObserver

  private lateinit var subject: StateLayoutView

  @Before
  fun setup() {
    MockKAnnotations.init(this, relaxed = true)
    subject = spyk(StateLayoutView(context))
    every { subject.context } returns context
    every { subject.viewTreeObserver } returns viewTreeObserver
  }


  @Test
  fun `when called initViews, addViews only called once`() {
    //given

    //when
    subject.setState(State.CONTENT)



    //then
  //  verify(exactly = 0) { subject.removeStateLayoutViews() }
  }



  companion object {

    @JvmStatic
    @BeforeClass
    fun setUpClass() {
      mockkStatic(LayoutInflater::class)
    }

    @JvmStatic
    @AfterClass
    fun tearDownClass() {
      unmockkStatic(LayoutInflater::class)
    }

  }

}