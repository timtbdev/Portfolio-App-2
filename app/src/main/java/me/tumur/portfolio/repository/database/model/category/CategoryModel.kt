package me.tumur.portfolio.repository.database.model.category

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.CATEGORY, indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
@Parcelize
data class CategoryModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @Json(name = DbConstants.TYPE) @ColumnInfo(name = DbConstants.TYPE) var type: Int,
    @Json(name = DbConstants.ICON) @ColumnInfo(name = DbConstants.ICON) var icon: String,
    @Json(name = DbConstants.ICON_DESCRIPTION) @ColumnInfo(name = DbConstants.ICON_DESCRIPTION) var iconDescription: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
) : Parcelable