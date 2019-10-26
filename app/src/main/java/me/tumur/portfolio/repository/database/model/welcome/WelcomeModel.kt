package me.tumur.portfolio.repository.database.model.welcome

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.tumur.portfolio.utils.constants.DbConstants

@Entity(tableName = DbConstants.WELCOME, indices = [Index(value = [DbConstants.ID], unique = true)])
data class WelcomeModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @SerializedName(DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @SerializedName(DbConstants.SUB_TITLE) @ColumnInfo(name = DbConstants.SUB_TITLE) var subTitle: String,
    @SerializedName(DbConstants.TEXT) @ColumnInfo(name = DbConstants.TEXT) var text: String,
    @SerializedName(DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) val imageDescription: String,
    @SerializedName(DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)