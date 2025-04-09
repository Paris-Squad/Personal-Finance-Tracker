package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import java.time.LocalDateTime

class InMemoryTransactionDataSourceImpl:TransactionDataSource {
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
        TODO("Not yet implemented")
    }

    override fun getTransactionByDate(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getMonthlyReport(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }
}