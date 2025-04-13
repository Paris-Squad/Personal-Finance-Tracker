package org.example.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction

fun String.isValidName() : Boolean = this.isNotBlank()

fun Double.isValidAmount(): Boolean = this > 0

fun Boolean?.isValidTransactionType(): Boolean = this != null

fun Category?.isValidCategory(): Boolean = this != null && Category.entries.contains(this)

fun LocalDate.isValidCreationDate(): Boolean {
    val today = Clock.System.now().toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date
    return this <= today
}

fun Transaction.addModificationDate(): List<LocalDate> {
    val currentDates = this.modificationDates.toMutableList()
    currentDates.add(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
    return currentDates
}