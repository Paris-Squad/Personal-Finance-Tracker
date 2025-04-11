package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Transaction
import org.example.utils.Validator
import org.example.exceptions.TransactionNotFoundException

class FakeTransactionDataSourceImpl(private val validation: Validator = Validator) : TransactionDataSource {
    private val transactionList = mutableListOf<Transaction>()

    override fun createTransaction(transaction: Transaction): Boolean {
        val isValidName = validation.isValidName(transaction.name)
        val isValidAmount = validation.isValidAmount(transaction.amount)
        val isValidTransactionType = validation.isValidTransactionType(transaction.isDeposit)
        val isValidCategory = validation.isValidCategory(transaction.category)
        val isValidCreationDate = validation.isValidCreationDate(transaction.creationDate)

        return if (isValidName && isValidAmount && isValidTransactionType && isValidCategory && isValidCreationDate) {
            transactionList.add(transaction)
            true
        } else {
            false
        }
    }

    override fun removeTransaction(transaction: Transaction): Boolean =
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