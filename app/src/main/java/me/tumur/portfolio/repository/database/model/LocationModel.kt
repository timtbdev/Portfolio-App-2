package me.tumur.portfolio.repository.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.LOCATION, indices = [Index(value = [DbConstants.ID], unique = true)])
@Parcelize
data class LocationModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.LATITUDE) @ColumnInfo(name = DbConstants.LATITUDE) var latitude: Double? = null,
    @Json(name = DbConstants.LONGITUDE) @ColumnInfo(name = DbConstants.LONGITUDE) var longitude: Double? = null
) : Parcelable