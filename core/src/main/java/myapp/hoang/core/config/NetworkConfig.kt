package myapp.hoang.core.config

object NetworkConfig {
    const val HOST = "192.168.86.174"
    private const val ROUTE_AUTH = "auth"
    private const val ROUTE_MEDIA = "media"
    private const val ROUTE_PROFILE_PIC = "profile-pics"
    private const val ROUTE_POSTS = "posts"
    private const val ROUTE_COMMENTS = "comments"
    private const val ROUTE_REPLY_COMMENTS = "reply-comments"

    const val ROUTE_SEND_VERIFICATION_CODE = "$ROUTE_AUTH/send-verification-code"
    const val ROUTE_CHECK_VERIFICATION_CODE = "$ROUTE_AUTH/check-verification-code"
    const val ROUTE_SIGN_UP = "$ROUTE_AUTH/sign-up"
    const val ROUTE_LOG_IN = "$ROUTE_AUTH/log-in"
    const val ROUTE_AUTHENTICATE = "$ROUTE_AUTH/authenticate"

    const val ROUTE_UPLOAD_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/upload-profile-pic"
    const val ROUTE_GET_PROFILE_PIC = "$ROUTE_MEDIA/$ROUTE_PROFILE_PIC/get-profile-pic"
    const val ROUTE_UPLOAD_IMAGES = "$ROUTE_MEDIA/$ROUTE_POSTS/upload-images"
    const val ROUTE_CREATE_POST = "$ROUTE_MEDIA/$ROUTE_POSTS/create"
    const val ROUTE_GET_POSTS_BY_USER_ID = "$ROUTE_MEDIA/$ROUTE_POSTS/posts-by-user-id"
    const val ROUTE_LIKE_POST = "$ROUTE_MEDIA/$ROUTE_POSTS/like"
    const val ROUTE_UNLIKE_POST = "$ROUTE_MEDIA/$ROUTE_POSTS/unlike"
    const val ROUTE_GET_POST_BY_ID = "$ROUTE_MEDIA/$ROUTE_POSTS/post-by-id"
    const val ROUTE_COMMENT_ON_POST = "$ROUTE_MEDIA/$ROUTE_POSTS/comment"

    const val ROUTE_LIKE_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENTS/like"
    const val ROUTE_UNLIKE_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENTS/unlike"
    const val ROUTE_REPLY_TO_COMMENT = "$ROUTE_MEDIA/$ROUTE_COMMENTS/reply"

    const val ROUTE_LIKE_REPLY_COMMENT = "$ROUTE_MEDIA/$ROUTE_REPLY_COMMENTS/like"
    const val ROUTE_UNLIKE_REPLY_COMMENT = "$ROUTE_MEDIA/$ROUTE_REPLY_COMMENTS/unlike"

    const val ROUTE_USERS = "users"
}