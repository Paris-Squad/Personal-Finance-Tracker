package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction

class FakeTransactionDataSourceImpl:TransactionDataSource {
    private val transactionList = mutableListOf<Transaction>()

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

}