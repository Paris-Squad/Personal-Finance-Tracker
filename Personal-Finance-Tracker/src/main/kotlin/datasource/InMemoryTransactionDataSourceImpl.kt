package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import java.time.LocalDateTime

class InMemoryTransactionDataSourceImpl : TransactionDataSource {
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
        val isFutureDate = transaction.creationTime.isAfter(LocalDateTime.now())

        when {
            index < 0 -> println("Update Failed: Item not found")
            isBlankName -> println("Update Failed: Name can't be empty")
            isFutureDate -> println("Update Failed: Date can't be in future")
            !isPositiveAmount -> println("Update Failed: Amount must be positive")
            else -> {
                val currentTransaction = transactionList[index]
                val editedAtList = currentTransaction.editTime.toMutableList()
                editedAtList.add(LocalDateTime.now())
                transactionList[index] = transaction.copy(editTime = editedAtList)
                println("Update Transaction Success ")
            }
        }

    }

    override fun getAllTransaction(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionByDate(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getMonthlyReport(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }
}