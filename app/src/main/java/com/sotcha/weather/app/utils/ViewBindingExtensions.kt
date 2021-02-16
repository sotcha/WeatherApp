package com.sotcha.weather.app.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Activity extension function used to create ViewBinding object for an activity
 *
 * class MainActivity : AppCompatActivity() {
 *        private val binding by viewBinding(MainActivityBinding::inflate)
 *
 *        override fun onCreate(savedInstanceState: Bundle?) {
 *            super.onCreate(savedInstanceState)
 *            setContentView(binding.root)
 *
 *            binding.button.onClick {
 *            showToast("hello world!")
 *        }
 *
 * @param T
 * @param bindingInflater The inflater of the view binding
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

/**
 * ViewGroup extension to create ViewBinding object
 *
 * Example:
 *
 * val binding = viewBinding(ComponentButtonBinding::inflate)
 * val binding = viewBinding(ComponentButtonBinding::inflate, false)
 *
 * @param bindingInflater The inflater of the view binding
 * @param attachToParent Attach or not to the parent
 */
inline fun <T : ViewBinding> ViewGroup.viewBinding(
    crossinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = true
) =
    bindingInflater.invoke(LayoutInflater.from(this.context), this, attachToParent)

