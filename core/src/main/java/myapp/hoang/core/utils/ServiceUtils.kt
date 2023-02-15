package myapp.hoang.core.utils

import myapp.hoang.core.config.ServiceConfig

object ServiceUtils {
    fun buildAmazonS3ObjectUrl(objectKey: String): String {
        return ServiceConfig.amazonS3BaseUrl.plus(objectKey)
    }
}