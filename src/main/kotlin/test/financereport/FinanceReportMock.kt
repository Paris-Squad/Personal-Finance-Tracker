package org.example.test.financereport

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import java.util.UUID

class FinanceReportMock : TransactionDataSource {
    override fun createTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

    override fun getTransactions(): List<Transaction> {
        return createMockTransactions()
    }

}


private fun createMockTransactions(): MutableList<Transaction> {

    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    return mutableListOf(
        Transaction(
            id = UUID.randomUUID(),
            name = "Monthly Salary",
            isDeposit = true,
            amount = 3500.00,
            category = Category.SALARY,
            creationDate = currentDate,
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Freelance Project",
            isDeposit = true,
            amount = 850.00,
            category = Category.SALARY,
            creationDate = LocalDate(currentDate.year, currentDate.month, 15),
            editDate = emptyList()
        ),

        Transaction(
            id = UUID.randomUUID(),
            name = "Apartment Rent",
            isDeposit = false,
            amount = 1200.00,
            category = Category.RENT,
            creationDate = LocalDate(currentDate.year, currentDate.month, 1),
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Grocery Shopping",
            isDeposit = false,
            amount = 187.45,
            category = Category.FOOD,
            creationDate = currentDate,
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Restaurant Dinner",
            isDeposit = false,
            amount = 78.50,
            category = Category.FOOD,
            creationDate = LocalDate(currentDate.year, currentDate.month, currentDate.dayOfMonth - 2),
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Movie Tickets",
            isDeposit = false,
            amount = 32.00,
            category = Category.ENTERTAINMENT,
            creationDate = LocalDate(currentDate.year, currentDate.month, 18),
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Uber Rides",
            isDeposit = false,
            amount = 45.75,
            category = Category.TRANSPORT,
            creationDate = LocalDate(currentDate.year, currentDate.month, 20),
            editDate = emptyList()
        ),

        Transaction(
            id = UUID.randomUUID(),
            name = "Previous Month Salary",
            isDeposit = true,
            amount = 3500.00,
            category = Category.SALARY,
            creationDate = LocalDate(currentDate.year - 1, currentDate.month, 20),
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Previous Month Rent",
            isDeposit = false,
            amount = 1200.00,
            category = Category.RENT,
            creationDate = LocalDate(currentDate.year - 1, currentDate.month, 20),
            editDate = emptyList()
        ),
        Transaction(
            id = UUID.randomUUID(),
            name = "Gas Station",
            isDeposit = false,
            amount = 65.30,
            category = Category.TRANSPORT,
            creationDate = LocalDate(currentDate.year - 1, currentDate.month, 20),
            editDate = emptyList()
        )
    )
}