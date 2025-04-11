package datasource

import models.Transaction

interface TransactionDataSource {
    fun createTransaction(transaction: Transaction)
    fun removeTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction)
    fun getTransactions(): List<Transaction>
}