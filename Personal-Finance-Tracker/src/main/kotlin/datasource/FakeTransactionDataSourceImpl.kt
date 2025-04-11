package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import kotlinx.datetime.LocalDate
import org.example.utils.Validator

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

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun getTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

}