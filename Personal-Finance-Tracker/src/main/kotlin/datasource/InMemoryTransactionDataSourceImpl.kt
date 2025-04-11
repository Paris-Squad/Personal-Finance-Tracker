package org.example.datasource

import datasource.TransactionDataSource
import models.Transaction
import org.example.common.Validation
import kotlinx.datetime.LocalDate

class FakeTransactionDataSourceImpl:TransactionDataSource {
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