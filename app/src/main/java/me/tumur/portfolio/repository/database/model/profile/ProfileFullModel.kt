package me.tumur.portfolio.repository.database.model.profile

import androidx.room.Embedded
import androidx.room.Relation
import me.tumur.portfolio.utils.constants.DbConstants

data class ProfileFullModel(

    @Embedded
    var profile: ProfileModel,

    @Relation(parentColumn = DbConstants.ID, entityColumn = DbConstants.OWNER_ID, entity = SocialModel::class)
    var social: List<SocialModel>,

    @Relation(parentColumn = DbConstants.ID, entityColumn = DbConstants.OWNER_ID, entity = AboutModel::class)
    var about: List<AboutModel>
)
