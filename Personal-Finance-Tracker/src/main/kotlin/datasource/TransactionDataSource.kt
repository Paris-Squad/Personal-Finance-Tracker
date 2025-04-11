package datasource

import kotlinx.datetime.LocalDate
import models.Transaction


interface TransactionDataSource {
    fun createTransaction(transaction: Transaction):Boolean
    fun removeTransaction(transaction: Transaction):Boolean
    fun updateTransaction(transaction: Transaction)
    fun getTransactions(): List<Transaction>
    fun generateReport(startingDate: LocalDate, endingDate: LocalDate) : List<Transaction>
}