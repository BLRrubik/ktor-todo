package ru.rubik.app.response

import io.ktor.http.*

sealed class BaseResponse<T> (
    val status: HttpStatusCode
) {
    data class SuccessResponse<T> (
        val data: T
    ): BaseResponse<T>(HttpStatusCode.OK)

    data class NotFoundResponse<T> (
        val data: T,
    ): BaseResponse<T>(HttpStatusCode.NotFound)
    data class AuthErrorResponse<T> (
        val data: T,
    ): BaseResponse<T>(HttpStatusCode.Forbidden)

    data class ConflictResponse<T> (
        val data: T,
    ): BaseResponse<T>(HttpStatusCode.Conflict)
}