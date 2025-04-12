package org.example.models

import kotlinx.datetime.LocalDate
import models.Category

data class FinanceReport(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val generationTime: LocalDate,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val netSavings: Double = 0.0,
    val savingsRate: Double = 0.0,
    val incomeByCategory: Map<Category, Double> = emptyMap(),
    val expenseByCategory: Map<Category, Double> = emptyMap(),
    val expenseBreakdownPercentage: Map<Category, Double> = emptyMap(),
) {

    override fun toString(): String {
        val dateRange = "$startDate to $endDate"
        val sb = StringBuilder()

        val dashedSeparator = "-------------------------------------------------------------------"
        val doubleLineSeparator = "==================================================================="


        sb.appendLine(doubleLineSeparator)
        sb.appendLine("        FINANCIAL REPORT: $dateRange        ")
        sb.appendLine(doubleLineSeparator)

        sb.appendLine("\n📊 SUMMARY")
        sb.appendLine(dashedSeparator)
        sb.appendLine("Total Income:".padEnd(40) + "$${String.format("%,.2f", totalIncome)}")
        sb.appendLine("Total Expenses:".padEnd(40) + "$${String.format("%,.2f", totalExpense)}")
        sb.appendLine("Net Savings:".padEnd(40) + "$${String.format("%,.2f", netSavings)}")
        sb.appendLine("Savings Rate:".padEnd(40) + "${String.format("%.1f", savingsRate * 100)}%")

        sb.appendLine("\n💰 INCOME BREAKDOWN")
        sb.appendLine(dashedSeparator)
        incomeByCategory.entries.sortedByDescending { it.value }.forEach { (category, amount) ->
            sb.appendLine("${category.name.padEnd(40)} $${String.format("%,.2f", amount)}")
        }

        sb.appendLine("\n💸 EXPENSE BREAKDOWN")
        sb.appendLine(dashedSeparator)
        expenseByCategory.entries.sortedByDescending { it.value }.forEach { (category, amount) ->
            val percentage = expenseBreakdownPercentage[category] ?: 0.0
            sb.appendLine(
                "${category.name.padEnd(40)} $${String.format("%,.2f", amount)} (${
                    String.format(
                        "%.1f",
                        percentage * 100
                    )
                }%)"
            )
        }

        sb.appendLine("\n$doubleLineSeparator")

        return sb.toString()
    }

}