package com.yonder.statelayout

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.yonder.errorstatelayout.R
import com.yonder.statelayout.constraints.setBtnErrorTryAgainLocation
import com.yonder.statelayout.constraints.setIvEmpty
import com.yonder.statelayout.constraints.setIvErrorLocation
import com.yonder.statelayout.constraints.setPbLoading
import com.yonder.statelayout.constraints.setTvEmpty
import com.yonder.statelayout.constraints.setTvErrorDetailLocation
import com.yonder.statelayout.constraints.setTvErrorTitleLocation
import com.yonder.statelayout.constraints.setTvLoading
import com.yonder.statelayout.utils.toPx

/**
 * @author: yusufonder
 * @date: 25/05/2021
 */

class StateLayoutView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), IStateLayoutView {

  private val stateLayoutViewIds = arrayListOf<Int>()

  private var currentState: State = State.CONTENT

  // State Error case
  private var textErrorTitle: String
  private var textErrorDetail: String
  private var textErrorBtnTryAgain: String
  private var errorDrawable: Drawable?

  // State Loading case
  private var textLoading: String
  internal var loadingGravity: LoadingGravity = LoadingGravity.TOP

  private var isAddedViews: Boolean = false

  // State Empty case
  private var textEmpty: String
  private var emptyIconDrawable: Drawable?

  //Spacing between views
  internal var defaultMargin: Int

  //View Click Listeners
  private var tryAgainClickListener: StateLayoutListener.TryAgainClickListener? = null

  init {
    context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorStateLayoutView, 0, 0).apply {
      try {

        //State.Error case
        textErrorTitle = getString(R.styleable.ErrorStateLayoutView_textErrorTitle)
          ?: context.getString(R.string.title_error_title)
        textErrorDetail = getString(R.styleable.ErrorStateLayoutView_textErrorDetail)
          ?: context.getString(R.string.title_error_detail)
        textErrorBtnTryAgain = getString(R.styleable.ErrorStateLayoutView_textErrorButtonTryAgain)
          ?: context.getString(R.string.title_error_btn_try_again)
        errorDrawable =
          getDrawable(R.styleable.ErrorStateLayoutView_errorDrawable) ?: ContextCompat.getDrawable(
            context,
            R.drawable.ic_round_error_72
          )

        //State.Loading case
        textLoading = getString(R.styleable.ErrorStateLayoutView_textLoading)
          ?: context.getString(R.string.title_loading)
        loadingGravity = LoadingGravity.find(getInt(R.styleable.ErrorStateLayoutView_loadingGravity, LoadingGravity.CENTER.value))

        //State.Empty case
        textEmpty = getString(R.styleable.ErrorStateLayoutView_textEmpty)
          ?: context.getString(R.string.title_empty)
        emptyIconDrawable = getDrawable(R.styleable.ErrorStateLayoutView_emptyIconDrawable)
          ?: ContextCompat.getDrawable(context, R.drawable.ic_baseline_inbox_72)

        defaultMargin =
          getInteger(R.styleable.ErrorStateLayoutView_defaultMargin, DEFAULT_MARGIN).toPx.toInt()

      } finally {
        recycle()
      }
    }
  }

  //State.ERROR View Components
  override val ivError: ImageView by lazy {
    ImageView(context).apply {
      id = generateViewId()
      setImageDrawable(errorDrawable)
      stateLayoutViewIds.add(id)
    }
  }

  override val tvErrorTitle: TextView by lazy {
    TextView(context).apply {
      id = generateViewId()
      text = textErrorTitle
      TextViewCompat.setTextAppearance(this, R.style.ErrorTitleTextAppearance)
      stateLayoutViewIds.add(id)
    }
  }

  override val tvErrorDetail: TextView by lazy {
    TextView(context).apply {
      id = generateViewId()
      text = textErrorDetail
      stateLayoutViewIds.add(id)
    }
  }

  override val btnErrorTryAgain: Button by lazy {
    Button(context).apply {
      id = generateViewId()
      text = textErrorBtnTryAgain
      stateLayoutViewIds.add(id)
      setOnClickListener { tryAgainClickListener?.onClickTryAgain() }
    }
  }

  //State Loading View Components
  override val pbLoading: ProgressBar by lazy {
    ProgressBar(context).apply {
      id = generateViewId()
      stateLayoutViewIds.add(id)
    }
  }

  override val tvLoading: TextView by lazy {
    TextView(context).apply {
      id = generateViewId()
      text = textLoading
      stateLayoutViewIds.add(id)
    }
  }

  //State Empty View Components
  override val tvEmptyTitle: TextView by lazy {
    TextView(context).apply {
      id = generateViewId()
      text = textEmpty
      stateLayoutViewIds.add(id)
    }
  }

  override val ivEmpty: ImageView by lazy {
    ImageView(context).apply {
      id = generateViewId()
      setImageDrawable(emptyIconDrawable)
      stateLayoutViewIds.add(id)
    }
  }

  fun setOnTryAgainClickListener(listener: StateLayoutListener.TryAgainClickListener) {
    tryAgainClickListener = listener
  }

  @VisibleForTesting
  fun generateUndefinedViewIds(children: Sequence<View>) {
    children.forEach { subView ->
      if (subView.id == UNDEFINED_VIEW_ID) {
        subView.id = generateViewId()
      }
    }
  }

  private fun initStateLayoutViews() {
    generateUndefinedViewIds(children)
    addViews(isAddedViews)
    connectViews(currentState)
    setVisibilityOfContents()
    isAddedViews = true
  }

  private fun setVisibilityOfContents() {
    val isContentState = currentState == State.CONTENT
    children.forEach { subView ->
      if (subView.id !in stateLayoutViewIds) {
        subView.isVisible = isContentState
      }
    }
  }

  private fun addViews(isAddedViews: Boolean) {
    if (!isAddedViews) {
      when (currentState) {

        State.ERROR -> {
          addView(ivError)
          addView(tvErrorTitle)
          addView(tvErrorDetail)
          addView(btnErrorTryAgain)
        }

        State.LOADING -> {
          addView(pbLoading)
          addView(tvLoading)
        }

        State.EMPTY -> {
          addView(ivEmpty)
          addView(tvEmptyTitle)
        }
        else -> Unit
      }
    }
  }

  fun setState(state: State) {
    if (currentState != state) {
      currentState = state
      isAddedViews = false
      removeStateLayoutViews()
      initStateLayoutViews()
    }

  }

  @VisibleForTesting
  fun removeStateLayoutViews() {
    stateLayoutViewIds.forEach { viewId ->
      removeView(findViewById(viewId))
    }
  }


  private fun connectViews(state: State) {

    if (!isAddedViews) {
      ConstraintSet().apply {
        clone(this@StateLayoutView)
        when (state) {

          State.ERROR -> {
            this@StateLayoutView.setIvErrorLocation(this)
            this@StateLayoutView.setTvErrorTitleLocation(this)
            this@StateLayoutView.setTvErrorDetailLocation(this)
            this@StateLayoutView.setBtnErrorTryAgainLocation(this)
          }

          State.LOADING -> {
            this@StateLayoutView.setPbLoading(this)
            this@StateLayoutView.setTvLoading(this)
          }

          State.EMPTY -> {
            this@StateLayoutView.setIvEmpty(this)
            this@StateLayoutView.setTvEmpty(this)
          }

          else -> Unit
        }
        applyTo(this@StateLayoutView)
      }
    }
  }

  companion object {
    private const val DEFAULT_MARGIN = 16
    private const val UNDEFINED_VIEW_ID = -1
  }

}

