package datasource

import models.Transaction

interface TransactionDataSource {
    fun createTransaction(transaction: Transaction)
    fun removeTransaction(transaction: Transaction): Boolean
    fun updateTransaction(transaction: Transaction): Transaction
    fun getTransactions(): List<Transaction>
}