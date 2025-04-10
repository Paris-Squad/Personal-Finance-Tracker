package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import kotlinx.datetime.LocalDate
import org.example.common.Validation

class InMemoryTransactionDataSourceImpl:TransactionDataSource {
    private val transactions = mutableListOf<Transaction>()


    override fun createTransaction(transaction: Transaction) : Boolean {
        val isValidName = Validation.isValidName(transaction.name)
        val isValidAmount = Validation.isValidAmount(transaction.amount)
        val isValidTransactionType =  Validation.isValidTransactionType(transaction.isDeposit)
        val isValidCategory =  Validation.isValidCategory(transaction.category)
        val isValidCreationDate = Validation.isValidCreationDate(transaction.creationDate)

        return if (isValidName && isValidAmount && isValidTransactionType && isValidCategory && isValidCreationDate  ) {
            transactions.add(transaction)
            true
        } else {
            false
        }
    }

    override fun removeTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

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
