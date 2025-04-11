package org.example.interactor

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.models.FinanceReport

class GetFinanceReportInteractor(
    private val dataSource: TransactionDataSource,
) {
    operator fun invoke(
        startDate: LocalDate,
        endDate: LocalDate,
    ): FinanceReport {

        if (startDate > endDate) {
            return createEmptyReport(startDate, endDate)
        }

        val transactions = dataSource.getTransactions().filter { it.creationDate in startDate..endDate }


        val incomeTransactions = transactions.filter { it.isDeposit }
        val totalIncome = incomeTransactions.sumOf { it.amount }

        val expenseTransactions = transactions.filter { !it.isDeposit }
        val totalExpense = expenseTransactions.sumOf { it.amount }

        val netSavings = totalIncome - totalExpense

        val savingsRate = if (totalIncome > 0) netSavings / totalIncome else 0.0

        val incomeByCategory = groupByCategory(incomeTransactions)

        val expenseByCategory = groupByCategory(expenseTransactions)

        val expenseBreakdownPercentage = calculateExpenseBreakdown(totalExpense, expenseByCategory)

        return FinanceReport(
            startDate = startDate,
            endDate = endDate,
            generationTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
            totalIncome = totalIncome,
            totalExpense = totalExpense,
            netSavings = netSavings,
            savingsRate = savingsRate,
            incomeByCategory = incomeByCategory,
            expenseByCategory = expenseByCategory,
            expenseBreakdownPercentage = expenseBreakdownPercentage
        )
    }

    private fun createEmptyReport(startDate: LocalDate, endDate: LocalDate): FinanceReport {
        return FinanceReport(
            startDate = startDate,
            endDate = endDate,
            generationTime = getCurrentDate()
        )
    }

    private fun getCurrentDate() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    private fun groupByCategory(transactions: List<Transaction>): Map<Category, Double> {
        return transactions
            .groupBy { it.category }
            .mapValues { (_, transactions) -> transactions.sumOf { it.amount } }
    }

    private fun calculateExpenseBreakdown(
        totalExpense: Double,
        expenseByCategory: Map<Category, Double>
    ): Map<Category, Double> {
        return if (totalExpense > 0) {
            expenseByCategory.mapValues { (_, amount) -> amount / totalExpense }
        } else {
            emptyMap()
        }
    }

}