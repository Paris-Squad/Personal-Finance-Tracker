package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import kotlinx.datetime.LocalDate
import org.example.common.Validation

class InMemoryTransactionDataSourceImpl:TransactionDataSource {
    private val transactions = mutableListOf<Transaction>()
    private val validator = Validation()


    override fun createTransaction(transaction: Transaction) : Boolean {
        val isValid = validator.isValidName(transaction.name) &&
                validator.isValidAmount(transaction.amount) &&
                validator.isValidTransactionType(transaction.isDeposit) &&
                validator.isValidCategory(transaction.category) &&
                validator.isValidCreationDate(transaction.creationDate)

        return if (isValid) {
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
