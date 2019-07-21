package me.tumur.portfolio.repository.database.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.ABOUT, indices = [Index(value = [DbConstants.ID], unique = true)])
@JsonClass(generateAdapter = true)
data class AboutModel(
    @PrimaryKey(autoGenerate = false)
    @Json(name = DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @Json(name = DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @Json(name = DbConstants.HEADER) @ColumnInfo(name = DbConstants.HEADER) var header: String,
    @Json(name = DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @Json(name = DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int)