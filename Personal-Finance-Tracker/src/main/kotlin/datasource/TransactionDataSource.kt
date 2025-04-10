package datasource

import models.Transaction
import kotlinx.datetime.LocalDate

interface TransactionDataSource {
    fun createTransaction(transaction: Transaction)
    fun removeTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction): Transaction
    fun getTransactions(): List<Transaction>
    fun generateReport(startingDate: LocalDate, endingDate: LocalDate) : List<Transaction>
}