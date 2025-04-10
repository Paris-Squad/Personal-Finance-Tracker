package org.example.utils

import models.Category


object Validator {

    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.all { it.isLetter() || it.isWhitespace() }
    }

    fun isValidAmount(amount: Double): Boolean {
        return amount > 0
    }

    fun isValidTransactionType(isIncome: Boolean?): Boolean {
        return isIncome != null
    }

    fun isValidCategory(category: Category?): Boolean {
        return category != null && Category.entries.contains(category)
    }

}