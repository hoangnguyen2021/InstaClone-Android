package myapp.hoang.core.util

import androidx.core.text.isDigitsOnly
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber

object Validator {
    fun validateMobileNumber(mobileNumber: String?): Boolean {
        if (mobileNumber.isNullOrEmpty()) return false
        if (!mobileNumber.isDigitsOnly()) return false

        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        val number = PhoneNumber().apply {
            countryCode = 1
            nationalNumber = mobileNumber.toLong()
        }
        return phoneNumberUtil.isValidNumber(number)
    }
}