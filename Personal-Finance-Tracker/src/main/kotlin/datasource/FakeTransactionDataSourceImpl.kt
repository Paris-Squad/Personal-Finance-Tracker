package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import org.example.exceptions.TransactionNotFoundException
import org.example.utils.addModificationDate

class FakeTransactionDataSourceImpl : TransactionDataSource {
    private val transactions = mutableListOf<Transaction>()

    override fun createTransaction(transaction: Transaction): Boolean =
        transactions.add(transaction)

    override fun removeTransaction(transaction: Transaction): Boolean =
        transactions.removeIf { it.id == transaction.id }

    @Throws(TransactionNotFoundException::class)
    override fun updateTransaction(transaction: Transaction): Transaction {
        val currentTransaction = transactions.find {
            it.id == transaction.id
        }

        currentTransaction?.let {
            val index = transactions.indexOfFirst {
                it.id == currentTransaction.id
            }
            val updatedTransaction = transaction.copy(
                modificationDates = currentTransaction.addModificationDate()
            )
            transactions[index] = updatedTransaction
            return updatedTransaction
        }

        throw TransactionNotFoundException()
    }

    override fun getTransactions(): List<Transaction> = transactions
}