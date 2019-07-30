package me.tumur.portfolio.repository.network

sealed class Result

/** Network request successfull */
object Success : Result()

/** Network request failed */
object Failed : Result()