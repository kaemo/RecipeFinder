package pl.kaemo.recipefinder.data.utils

import pl.kaemo.recipefinder.domain.utils.Reply
import retrofit2.Response

fun <T : Any, R : Any> Response<T>.toReply(bodyMapper: (T) -> R): Reply<R> {
    val body = this.body()

    if (this.isSuccessful && body != null) {
        return Reply.Success(bodyMapper(body))
    } else {
        return Reply.Error(Exception("Error code: ${code()}"))
    }
}