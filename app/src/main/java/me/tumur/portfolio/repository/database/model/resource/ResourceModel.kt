package me.tumur.portfolio.repository.database.model.resource

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants
import java.util.*

@Entity(tableName = DbConstants.RESOURCE, indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
@Parcelize
data class ResourceModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.IMAGE) @ColumnInfo(name = DbConstants.IMAGE) var image: String,
    @Json(name = DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @Json(name = DbConstants.DATE_FROM) @ColumnInfo(name = DbConstants.DATE_FROM) var dateFrom: Date,
    @Json(name = DbConstants.DATE_TO) @ColumnInfo(name = DbConstants.DATE_TO) var dateTo: Date,
    @Json(name = DbConstants.URL) @ColumnInfo(name = DbConstants.URL) var url: String?,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
) : Parcelable