package dinhtc.android.xml.ultils

sealed class ResultState<T> {
    class Idle<T>() : ResultState<T>()

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val isLoading: Boolean) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [error] is return.
     */
    data class Error<T>(val error: ErrorResponse?) : ResultState<T>()

    data class ErrorMessage<T>(val errorMessage: String) : DataResponse<T>(message = errorMessage)
//
//    /**
//     * A state to show a [throwable] is thrown.
//     */
//    data class OnThrowable<T>(val throwable: Throwable) : ResultState<T>()
}