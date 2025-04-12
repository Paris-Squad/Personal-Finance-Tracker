package org.example.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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

    fun isValidCreationDate(creationDate: LocalDate): Boolean {
        val today = Clock.System.now().toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date
        return creationDate <= today
    }

}