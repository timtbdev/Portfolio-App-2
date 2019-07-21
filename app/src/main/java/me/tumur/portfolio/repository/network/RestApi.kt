package me.tumur.portfolio.repository.network

import me.tumur.portfolio.repository.database.model.all.RequestAll
import me.tumur.portfolio.repository.database.model.experience.ExperienceApiResponse
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET("api/portfolio/")
    suspend fun getPortfolio(): Response<List<PortfolioApiResponse>>

    @GET("api/experience/")
    suspend fun getExperience(): Response<List<ExperienceApiResponse>>

    @GET("api/all/")
    suspend fun getAll(): Response<RequestAll>
}