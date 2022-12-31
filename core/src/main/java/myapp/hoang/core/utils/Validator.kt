package myapp.hoang.core.utils

import androidx.core.text.isDigitsOnly
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.sanctionco.jmail.JMail
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.LengthRule
import org.passay.PasswordData
import org.passay.PasswordValidator
import org.passay.RepeatCharacterRegexRule
import org.passay.WhitespaceRule

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

    fun validatePassword(password: String?): Boolean {
        if (password.isNullOrEmpty()) return false

        val passwordData = PasswordData(password)
        val passwordValidator = PasswordValidator(
            LengthRule(6, 20),
            WhitespaceRule(),
            CharacterRule(EnglishCharacterData.LowerCase),
            CharacterRule(EnglishCharacterData.Digit),
            RepeatCharacterRegexRule()
        )

        return passwordValidator.validate(passwordData).isValid
    }
}