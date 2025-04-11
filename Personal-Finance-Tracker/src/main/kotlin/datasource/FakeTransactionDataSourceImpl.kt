package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Transaction
import org.example.exceptions.TransactionNotFoundException

class FakeTransactionDataSourceImpl:TransactionDataSource {
    private val transactionList = mutableListOf<Transaction>()

    override fun createTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun removeTransaction(transaction: Transaction) =
        transactionList.removeIf { it.id == transaction.id }

    @Throws(TransactionNotFoundException::class)
    override fun updateTransaction(transaction: Transaction): Transaction {
        val currentTransaction = transactionList.find {
            it.id == transaction.id
        }

        currentTransaction?.let {
            val editedAtList = currentTransaction.editDate.toMutableList()
            editedAtList.add(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
            val index = transactionList.indexOfFirst {
                it.id == currentTransaction.id
            }
            val updatedTransaction = transaction.copy(editDate = editedAtList)
            transactionList[index] = updatedTransaction
            return updatedTransaction
        }

        throw TransactionNotFoundException()
    }

    override fun getTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }
}