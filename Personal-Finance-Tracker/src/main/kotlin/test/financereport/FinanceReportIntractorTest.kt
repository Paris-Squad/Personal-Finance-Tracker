package org.example.test.financereport

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import org.example.common.check
import org.example.interactor.GetFinanceReportInteractor
import org.example.models.FinanceReport

fun main() {
    testInvalidDateRange()
    testEmptyReportPeriod()
    testValidCalculation()
}

private object FinanceReportTestHelper {
    private val dataSource = FinanceReportMock()
    val interactor = GetFinanceReportInteractor(dataSource)
}

private fun testInvalidDateRange() {
    val startDate = LocalDate(2023, 5, 15)
    val endDate = LocalDate(2023, 5, 10)

    val expectedEmptyReport = FinanceReport(
        startDate = startDate,
        endDate = endDate,
        generationTime = FinanceReportTestHelper.interactor.invoke(startDate, endDate).generationTime,
    )

    check(
        name = "when generate report With start date later than the end date then it should return empty report",
        actual = FinanceReportTestHelper.interactor.invoke(startDate, endDate)
            .copy(generationTime = expectedEmptyReport.generationTime),
        expected = expectedEmptyReport
    )
}

private fun testEmptyReportPeriod() {

    val startDate = LocalDate(2000, 1, 1)
    val endDate = LocalDate(2000, 1, 31)

    val report = FinanceReportTestHelper.interactor.invoke(startDate, endDate)
    val expectedEmptyReport = FinanceReport(
        startDate = startDate,
        endDate = endDate,
        generationTime = report.generationTime,
    )

    check(
        name = "when generate report for period with no transactions then it should return zeroed report",
        actual = report,
        expected = expectedEmptyReport
    )
}

private fun testValidCalculation() {

    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val startOfMonth = LocalDate(currentDate.year, currentDate.monthNumber, 1)

    val report = FinanceReportTestHelper.interactor.invoke(startOfMonth, currentDate)

    check(
        name = "when generating report with income transactions then total income should match sum of income transactions",
        actual = report.totalIncome,
        expected = 3500.00
    )

    check(
        name = "when generating report with expense transactions then total expense should match sum of expense transactions",
        actual = report.totalExpense,
        expected = 1465.95
    )

    check(
        name = "when calculating net savings then it should match income minus expenses",
        actual = report.netSavings,
        expected = 2034.05
    )

    check(
        name = "when calculating savings rate then it should match net savings divided by income",
        actual = Math.round(report.savingsRate * 1000) / 1000.0,
        expected = 0.581
    )

    check(
        name = "when checking income by category then SALARY category should match sum of related transactions",
        actual = report.incomeByCategory[Category.SALARY],
        expected = 3500.00
    )

    check(
        name = "when checking expense by category then RENT category should match sum of related transactions",
        actual = report.expenseByCategory[Category.RENT],
        expected = 1200.00
    )

    // uncomment the print to see the full report
    // println(report)

}
