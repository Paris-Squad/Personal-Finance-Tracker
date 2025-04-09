package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import java.time.LocalDateTime

class InMemoryTransactionDataSourceImpl : TransactionDataSource {
    val transactions = mutableListOf<Transaction>()
    override fun createTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun removeTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun getAllTransaction(): List<Transaction> {
        if (transactions.isEmpty())
            return emptyList<Transaction>()
        else
            return transactions
        /*else if (transactions is MutableList<Transaction>)
            return transactions
        else if (transactions is MutableList<Any>)
            return emptyList<Transaction>()*/
    }
    override fun getTransactionByDate(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getMonthlyReport(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }
}