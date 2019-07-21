package me.tumur.portfolio.repository.database.model.profile

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.SOCIAL, indices = [Index(value = [DbConstants.ID, DbConstants.OWNER_ID], unique = true)])
@JsonClass(generateAdapter = true)
@Parcelize
data class SocialModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.NAME) @ColumnInfo(name = DbConstants.NAME) var name: String,
    @Json(name = DbConstants.URL) @ColumnInfo(name = DbConstants.URL) var url: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
): Parcelable