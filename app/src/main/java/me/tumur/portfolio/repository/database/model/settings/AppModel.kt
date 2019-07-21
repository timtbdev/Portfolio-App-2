package me.tumur.portfolio.repository.database.model.settings

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.APP)
@JsonClass(generateAdapter = true)
@Parcelize
data class AppModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
): Parcelable