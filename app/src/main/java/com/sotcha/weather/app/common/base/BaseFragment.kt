package com.sotcha.weather.app.common.base

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sotcha.weather.R
import com.sotcha.weather.app.common.lce.Error
import com.sotcha.weather.app.common.lce.LceStateViewModel
import com.sotcha.weather.app.common.lce.Loading
import com.sotcha.weather.app.common.lce.Success
import com.sotcha.weather.app.utils.DebugLogHelper

/**
 * Base Fragment with LCE functionality
 *
 * Sub classes should provide the view for LCE and initialize observation using [observeUiState]
 *
 */
abstract class BaseFragment : Fragment() {


    // --------------------------------------------------------------------------------------------
    // LCE related
    // --------------------------------------------------------------------------------------------
    protected fun observeUiState(lceStateViewModel: LceStateViewModel) {
        lceStateViewModel.uiState.observe(viewLifecycleOwner) { lceState ->
            when (lceState) {
                is Loading -> showLoading()
                is Success -> showContent()
                is Error -> {
                    DebugLogHelper.e("Observe UI state error", lceState.error)
                    showError()
                }
            }
        }
    }

    protected fun showLoading() {
        handleLoadingViewVisibility(true)
        getContentView()?.visibility = View.GONE
        getErrorView()?.visibility = View.GONE
    }

    protected fun showContent() {
        handleLoadingViewVisibility(false)
        getContentView()?.visibility = View.VISIBLE
        getErrorView()?.visibility = View.GONE
    }

    protected fun showError(errorMsg: String = getString(R.string.error_occurred)) {
        handleLoadingViewVisibility(false)
        getContentView()?.visibility = View.GONE

        getErrorView()?.let { errorView ->
            errorView.visibility = View.VISIBLE
            if (errorView is TextView) {
                errorView.text = errorMsg

            }
        }
    }

    private fun handleLoadingViewVisibility(show: Boolean) {
        val loadingView = getLoadingView() ?: return

        if (loadingView is SwipeRefreshLayout) {
            // ILf loading view is a SwipeRefreshLayout then use isRefreshing functionality
            loadingView.isRefreshing = show
        } else {
            // this is a regular view just show or hide it
            loadingView.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

    /**
     * Provide the view that contains the content.
     *
     * @return The view or null if there is not such functionality.
     */
    abstract fun getContentView(): View?

    /**
     * Provide the view that contains the error.
     * Returning a [TextView] it will show the error message that the [LceStateViewModel.uiState] returns.
     *
     * @return The view or null if there is not such functionality.
     */
    abstract fun getErrorView(): View?

    /**
     * Provide the view that contains the loading view.
     * Returning a [SwipeRefreshLayout] it will use [SwipeRefreshLayout.isRefreshing] functionality.
     *
     * @return The view or null if there is not such functionality.
     */
    abstract fun getLoadingView(): View?
}