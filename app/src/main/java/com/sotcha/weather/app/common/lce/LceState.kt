package com.sotcha.weather.app.common.lce

/**
 * State for LCE -> Load Content Error
 */
sealed class LceState

object Loading : LceState()
object Success : LceState()
class Error(val error: Throwable) : LceState()