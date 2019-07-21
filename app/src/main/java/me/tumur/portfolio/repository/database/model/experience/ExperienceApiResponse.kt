package me.tumur.portfolio.repository.database.model.experience

import com.squareup.moshi.Json
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.utils.constants.DbConstants

data class ExperienceApiResponse (

    @Json(name = DbConstants.EXPERIENCE)
    val experience: List<ExperienceModel>,

    @Json(name = DbConstants.TASK)
    val task: List<TaskModel>,

    @Json(name = DbConstants.BUTTON)
    val button: List<ButtonModel>
)