package com.sotcha.weather.app.model

import android.net.Uri


interface HourRow

data class HourItemUiModel(
    val time: String,
    val temperature: String,
    val description: String,
    val image: Uri?,
    val conditions: List<ConditionUiModel>,

    /**
     * We use this attribtue to detect if the row is expanded or not
     */
    var expandedRow: Boolean = false

) : HourRow


data class HourHeaderUiModel(
    val dateName: String,
    val avgTemperature: String,
    val minTemperature: String,
    val maxTemperature: String,
) : HourRow