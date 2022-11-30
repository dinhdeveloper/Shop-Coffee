package dinhtc.android.xml.ultils

import java.io.Serializable

class ErrorResponse : Serializable {
    var error: String? = null
    var errorHint: String? = null
    var timestamp: String? = null
    var status: String? = null
    var message: String? = null
    var path: String? = null

    constructor(
        error: String?,
        timestamp: String?,
        status: String?,
        message: String?,
        path: String?
    ) {
        this.error = error
        this.timestamp = timestamp
        this.status = status
        this.message = message
        this.path = path
    }

    constructor(message: String?) {
        this.message = message
    }

    constructor() {}

    fun getErrorHint(): ErrorHint {
        return ErrorHint.getErrorHint(errorHint)
    }

    val isDuplicatedInterview: Boolean
        get() = ErrorHint.DUPLICATE_INTERVIEW.name == errorHint
    val isAuthFailureError: Boolean
        get() = "401" == status

    enum class ErrorHint {
        NO_ERROR, WRONG_USERNAME, REQUIRE_LOGIN, REQUIRE_CREATE_ACCOUNT, DUPLICATE_INTERVIEW, DENY_APPLICATION, APP_EXITS, FILE_NOT_FOUND;

        companion object {
            fun getErrorHint(hint: String?): ErrorHint {
                for (item in values()) {
                    if (item.name == hint) return item
                }
                return NO_ERROR
            }
        }
    }
}