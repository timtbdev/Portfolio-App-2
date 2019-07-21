package me.tumur.portfolio.repository.network

sealed class Result

/** Splash Screen */
object Success : Result()

/** Main Screen */
object Loading : Result()

/** Loader Screen */
object Failed : Result()