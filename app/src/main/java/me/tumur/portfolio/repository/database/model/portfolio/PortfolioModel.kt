package me.tumur.portfolio.repository.database.model.portfolio

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.PORTFOLIO, indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
@Parcelize
data class PortfolioModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.SUB_TITLE) @ColumnInfo(name = DbConstants.SUB_TITLE) var subTitle: String,
    @Json(name = DbConstants.LOGO) @ColumnInfo(name = DbConstants.LOGO) var logo: String,
    @Json(name = DbConstants.LOGO_DESCRIPTION) @ColumnInfo(name = DbConstants.LOGO_DESCRIPTION) var logoDescription: String,
    @Json(name = DbConstants.COVER_IMAGE) @ColumnInfo(name = DbConstants.COVER_IMAGE) var coverImage: String,
    @Json(name = DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @Json(name = DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @Json(name = DbConstants.INFO) @ColumnInfo(name = DbConstants.INFO) var info: String,
    @Json(name = DbConstants.DATE_FROM) @ColumnInfo(name = DbConstants.DATE_FROM) var dateFrom: String,
    @Json(name = DbConstants.DATE_TO) @ColumnInfo(name = DbConstants.DATE_TO) var dateTo: String,
    @Json(name = DbConstants.HEADER) @ColumnInfo(name = DbConstants.HEADER) var header: String,
    @Json(name = DbConstants.TYPE) @ColumnInfo(name = DbConstants.TYPE) var categoryType: Int,
    @Json(name = DbConstants.VIDEO_URL) @ColumnInfo(name = DbConstants.VIDEO_URL) var videoUrl: String?,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
) : Parcelable