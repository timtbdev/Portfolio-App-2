package me.tumur.portfolio.repository.database.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(
    tableName = DbConstants.PROFILE,
    indices = [Index(value = [DbConstants.ID, DbConstants.NAME], unique = true)]
)
data class ProfileModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @SerializedName(DbConstants.GREETING) @ColumnInfo(name = DbConstants.GREETING) var greeting: String,
    @SerializedName(DbConstants.NAME) @ColumnInfo(name = DbConstants.NAME) var name: String,
    @SerializedName(DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @SerializedName(DbConstants.IMAGE) @ColumnInfo(name = DbConstants.IMAGE) var image: String,
    @SerializedName(DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @SerializedName(DbConstants.EMAIL) @ColumnInfo(name = DbConstants.EMAIL) var email: String,
    @SerializedName(DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)