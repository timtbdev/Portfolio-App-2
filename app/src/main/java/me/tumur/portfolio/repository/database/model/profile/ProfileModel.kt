package me.tumur.portfolio.repository.database.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.PROFILE,
    indices = [Index(value = [DbConstants.ID, DbConstants.NAME], unique = true)])
@JsonClass(generateAdapter = true)
data class ProfileModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.GREETING) @ColumnInfo(name = DbConstants.GREETING) var greeting: String,
    @Json(name = DbConstants.NAME) @ColumnInfo(name = DbConstants.NAME) var name: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.IMAGE) @ColumnInfo(name = DbConstants.IMAGE) var image: String,
    @Json(name = DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @Json(name = DbConstants.EMAIL) @ColumnInfo(name = DbConstants.EMAIL) var email: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)