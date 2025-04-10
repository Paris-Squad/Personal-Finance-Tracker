package org.example.interactor

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Transaction
import org.example.exceptions.TransactionNotValidException


class UpdateTransactionInteractor(
    private val dataSource: TransactionDataSource
) {
    @Throws(TransactionNotValidException::class)
    fun execute(transaction: Transaction): Transaction {
        if (isValidTransaction(transaction)) {
            return dataSource.updateTransaction(transaction)
        } else {
            throw TransactionNotValidException()
        }
    }

    private fun isValidTransaction(transaction: Transaction): Boolean {
        val isBlankName = transaction.name.isBlank()
        val isPositiveAmount = transaction.amount > 0
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val isFutureDate = transaction.creationDate > today

        return !isBlankName && isPositiveAmount && !isFutureDate
    }

}