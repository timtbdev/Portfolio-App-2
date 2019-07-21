package me.tumur.portfolio.repository.database.model.welcome

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.WELCOME,
    indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
data class WelcomeModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID)var id: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.SUB_TITLE) @ColumnInfo(name = DbConstants.SUB_TITLE) var subTitle: String,
    @Json(name = DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @Json(name = DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) val imageDescription: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)