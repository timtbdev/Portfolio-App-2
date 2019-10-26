package me.tumur.portfolio.repository.database.model.experience

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.tumur.portfolio.utils.constants.DbConstants
import java.util.*

@Entity(tableName = DbConstants.EXPERIENCE, indices = [Index(value = [DbConstants.ID], unique = true)])
data class ExperienceModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName(DbConstants.ID) @ColumnInfo(name = DbConstants.ID) var id: String,
    @SerializedName(DbConstants.OWNER_ID) @ColumnInfo(name = DbConstants.OWNER_ID) var ownerId: String,
    @SerializedName(DbConstants.TITLE) @ColumnInfo(name = DbConstants.TITLE) var title: String,
    @SerializedName(DbConstants.COMPANY) @ColumnInfo(name = DbConstants.COMPANY) var company: String,
    @SerializedName(DbConstants.INFO) @ColumnInfo(name = DbConstants.INFO) var info: String,
    @SerializedName(DbConstants.DATE_FROM) @ColumnInfo(name = DbConstants.DATE_FROM) var dateFrom: Date,
    @SerializedName(DbConstants.DATE_TO) @ColumnInfo(name = DbConstants.DATE_TO) var dateTo: Date,
    @SerializedName(DbConstants.LOCATION) @ColumnInfo(name = DbConstants.LOCATION) var location: String,
    @SerializedName(DbConstants.LOGO) @ColumnInfo(name = DbConstants.LOGO) var logo: String,
    @SerializedName(DbConstants.LOGO_DESCRIPTION) @ColumnInfo(name = DbConstants.LOGO_DESCRIPTION) var logoDescription: String,
    @SerializedName(DbConstants.COVER_IMAGE) @ColumnInfo(name = DbConstants.COVER_IMAGE) var coverImage: String,
    @SerializedName(DbConstants.IMAGE_DESCRIPTION) @ColumnInfo(name = DbConstants.IMAGE_DESCRIPTION) var imageDescription: String,
    @SerializedName(DbConstants.ORDER) @ColumnInfo(name = DbConstants.ORDERS) var order: Int
)