package cifor.icraf.flashcardsxml.commons.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class LocalResult<out T> {
    data class Success<T>(val data: T) : LocalResult<T>()

    data class Error(val errorMessage: String) : LocalResult<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<LocalResult<T>> {
    return this.map<T, LocalResult<T>> {
        LocalResult.Success(data = it)
    }.catch { emit(value = LocalResult.Error(errorMessage = it.message.toString())) }
}