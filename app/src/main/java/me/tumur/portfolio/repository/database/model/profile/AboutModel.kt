package me.tumur.portfolio.repository.database.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.ABOUT, indices = [Index(value = [DbConstants.ID], unique = true)])
data class AboutModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @SerializedName(DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @SerializedName(DbConstants.HEADER) @ColumnInfo(name = DbConstants.HEADER) var header: String,
    @SerializedName(DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @SerializedName(DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)