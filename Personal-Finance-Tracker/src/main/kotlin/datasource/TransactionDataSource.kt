package datasource

import kotlinx.datetime.LocalDate
import models.Transaction


interface TransactionDataSource {
    fun createTransaction(transaction: Transaction):Boolean
    fun removeTransaction(transaction: Transaction):Boolean
    fun updateTransaction(transaction: Transaction)
    fun getAllTransaction(): List<Transaction>
    fun getMonthlyReport(date: LocalDate) : List<Transaction>
}