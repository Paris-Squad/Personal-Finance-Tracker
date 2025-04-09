package datasource

import models.Transaction
import java.time.LocalDateTime

interface TransactionDataSource {
    fun createTransaction(transaction: Transaction)
    fun removeTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction)
    fun getAllTransaction(): List<Transaction>
    fun getTransactionByDate(date: LocalDateTime): List<Transaction>
    fun getMonthlyReport(date: LocalDateTime) : List<Transaction>
}