package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import kotlinx.datetime.LocalDate

class FakeTransactionDataSourceImpl:TransactionDataSource {
    val transactionList = mutableListOf<Transaction>()

    override fun createTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun removeTransaction(transaction: Transaction) =
        transactionList.removeIf { it.id == transaction.id }

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun getTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun generateReport(startingDate: LocalDate, endingDate: LocalDate): List<Transaction> {
        TODO("Not yet implemented")
    }
}