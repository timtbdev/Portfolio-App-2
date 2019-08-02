package me.tumur.portfolio.repository.repo

import me.tumur.portfolio.repository.network.Result

interface Repository {

    /** MAIN SCREEN ------------------------------------------------------------------------------------------------- */

    /**
     * Fetch data from network and update the database
     */
    suspend fun fetchAll(): Result
}