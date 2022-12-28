package myapp.hoang.core.util

import androidx.core.text.isDigitsOnly
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.sanctionco.jmail.JMail

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

    fun validateEmailAddress(emailAddress: String?): Boolean {
        return JMail.isValid(emailAddress)
    }
}