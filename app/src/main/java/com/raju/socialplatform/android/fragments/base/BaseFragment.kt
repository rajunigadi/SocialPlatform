package com.raju.socialplatform.android.fragments.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.raju.socialplatform.R
import timber.log.Timber

abstract class BaseFragment constructor(private var layoutId: Int, private var title: String): Fragment() {

    @BindView(R.id.progress_bar)
    lateinit protected var progressBar: ProgressBar

    private var unbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder!!.unbind()
            unbinder = null
        }
    }

    protected fun showSnackBar(message: String) {
        showSnackBar(message, false, null)
    }

    protected fun showSnackBar(message: String, hasAction: Boolean) {
        showSnackBar(message, hasAction, null)
    }

    protected fun showSnackBar(message: String, isAction: Boolean, name: String?) {
        var button = name
        if (activity != null && activity!!.window != null) {
            val view = activity!!.window.decorView.findViewById<View>(android.R.id.content)
            //final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            if (view != null) {
                val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                if (isAction) {
                    snackbar.duration = Snackbar.LENGTH_INDEFINITE
                    if (TextUtils.isEmpty(button)) button = "Dismiss"
                    snackbar.setAction(button) { snackbar.dismiss() }
                }
                snackbar.setActionTextColor(Color.WHITE)
                val sbView = snackbar.view
                val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
                textView.setTextColor(Color.WHITE)
                snackbar.show()
            } else {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getContext(): Context {
        return activity!!
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    fun showError(message: String) {
        Timber.d(message)
    }
}