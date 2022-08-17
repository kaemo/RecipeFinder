package pl.kaemo.recipefinder.ui.mainActivity

import pl.kaemo.recipefinder.R

object IngredientNameValidation {
    fun validateUserInput(userInput: String): ValidationStatus{

        return if (userInput == "") {
            ValidationStatus.Error(R.string.validation_empty)
        } else if (userInput.contains(" ")) {
            ValidationStatus.Error(R.string.validation_whitespaces)
        } else if (userInput.contains("[0-9]".toRegex())) {
            ValidationStatus.Error(R.string.validation_digits)
        } else if (userInput.contains("[!\"#\$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            ValidationStatus.Error(R.string.validation_specChar)
        } else if (userInput.length == 1) {
            ValidationStatus.Error(R.string.validation_char)
        } else if (userInput.length > 70) {
            ValidationStatus.Error(R.string.validation_long)
        } else {
            ValidationStatus.Ok()
        }
    }
}

sealed class ValidationStatus(){
    class Ok: ValidationStatus()
    class Error(val message: Int): ValidationStatus()
}