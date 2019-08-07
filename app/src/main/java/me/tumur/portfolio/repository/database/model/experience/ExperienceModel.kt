package me.tumur.portfolio.repository.database.model.experience

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.utils.constants.DbConstants
import java.util.*

@Entity(tableName = DbConstants.EXPERIENCE, indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
data class ExperienceModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.COMPANY) @ColumnInfo(name = DbConstants.COMPANY) var company: String,
    @Json(name = DbConstants.INFO) @ColumnInfo(name = DbConstants.INFO) var info: String,
    @Json(name = DbConstants.DATE_FROM) @ColumnInfo(name = DbConstants.DATE_FROM) var dateFrom: Date,
    @Json(name = DbConstants.DATE_TO) @ColumnInfo(name = DbConstants.DATE_TO) var dateTo: Date,
    @Json(name = DbConstants.LOCATION) @ColumnInfo(name = DbConstants.LOCATION) var location: String,
    @Json(name = DbConstants.LOGO) @ColumnInfo(name = DbConstants.LOGO) var logo: String,
    @Json(name = DbConstants.LOGO_DESCRIPTION) @ColumnInfo(name = DbConstants.LOGO_DESCRIPTION) var logoDescription: String,
    @Json(name = DbConstants.COVER_IMAGE) @ColumnInfo(name = DbConstants.COVER_IMAGE) var coverImage: String,
    @Json(name = DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)