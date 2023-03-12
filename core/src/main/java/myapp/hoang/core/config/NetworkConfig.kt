package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "192.168.1.65"
    private const val ROUTE_AUTH = "auth"
    private const val ROUTE_MEDIA = "media"
    private const val ROUTE_PROFILE_PIC = "profile-pic"
    private const val ROUTE_POST = "post"
    private const val ROUTE_COMMENT = "comment"

    const val ROUTE_SEND_VERIFICATION_CODE = "$ROUTE_AUTH/send-verification-code"
    const val ROUTE_CHECK_VERIFICATION_CODE = "$ROUTE_AUTH/check-verification-code"
    const val ROUTE_SIGN_UP = "$ROUTE_AUTH/sign-up"
    const val ROUTE_LOG_IN = "$ROUTE_AUTH/log-in"
    const val ROUTE_AUTHENTICATE = "$ROUTE_AUTH/authenticate"

    const val ROUTE_UPLOAD_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/upload-profile-pic"
    const val ROUTE_GET_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/get-profile-pic"
    const val ROUTE_UPLOAD_IMAGES = "$ROUTE_MEDIA/$ROUTE_POST/upload-images"
    const val ROUTE_CREATE_POST = "$ROUTE_MEDIA/$ROUTE_POST/create"
    const val ROUTE_GET_POSTS_BY_USER_ID = "$ROUTE_MEDIA/$ROUTE_POST/posts-by-user-id"
    const val ROUTE_LIKE_POST = "$ROUTE_MEDIA/$ROUTE_POST/like"
    const val ROUTE_UNLIKE_POST = "$ROUTE_MEDIA/$ROUTE_POST/unlike"
    const val ROUTE_GET_POST_BY_ID = "$ROUTE_MEDIA/$ROUTE_POST/post-by-id"
    const val ROUTE_COMMENT_ON_POST = "$ROUTE_MEDIA/$ROUTE_POST/comment"

    const val ROUTE_LIKE_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENT/like"
    const val ROUTE_UNLIKE_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENT/unlike"
    const val ROUTE_REPLY_TO_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENT/reply"

    const val ROUTE_USERS = "users"
}