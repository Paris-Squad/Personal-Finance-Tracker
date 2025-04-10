package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Transaction

class FakeTransactionDataSourceImpl : TransactionDataSource {
    private val transactionList = mutableListOf<Transaction>()

    override fun createTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun removeTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction) {

        val index = transactionList.indexOfFirst {
            it.id == transaction.id
        }

        // check name if empty
        val isBlankName = transaction.name.isBlank()
        // check amount if negative
        val isPositiveAmount = transaction.amount > 0
        // check date in future
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val isFutureDate = transaction.creationDate > today

        when {
            index < 0 -> println("Update Failed: Item not found")
            isBlankName -> println("Update Failed: Name can't be empty")
            isFutureDate -> println("Update Failed: Date can't be in future")
            !isPositiveAmount -> println("Update Failed: Amount must be positive")
            else -> {
                val currentTransaction = transactionList[index]
                val editedAtList = currentTransaction.editDate.toMutableList()
                editedAtList.add(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
                transactionList[index] = transaction.copy(editDate = editedAtList)
                println("Update Transaction Success ")
            }
        }

    }

    override fun getTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun generateReport(startingDate: LocalDate, endingDate: LocalDate): List<Transaction> {
        TODO("Not yet implemented")
    }
}