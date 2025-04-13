package org.example.test


import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl
import org.example.interactor.CreateTransactionInteractor
import org.example.interactor.GetTransactionsInteractor
import org.example.utils.check


fun main() {
    val dataSource = FakeTransactionDataSourceImpl()
    val getTransactionsInteractor = GetTransactionsInteractor(dataSource)
    val createTransactionInteractor = CreateTransactionInteractor(dataSource)

   check(
        name = "When the list returned is empty, return true",
        actual = getTransactionsInteractor(),
        expected = emptyList()
    )

    val transactions = (1..5).map { index ->
        val validDepositTransaction = Transaction(
            name = "Deposit Transaction $index",
            isDeposit = true,
            amount = 150.0 * index,
            category = Category.entries.random(),
            creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
            modificationDates = emptyList()
        )
        createTransactionInteractor(transaction = validDepositTransaction)
        validDepositTransaction
    }

    check(
        name = "When the transaction is not empty, return true",
        actual = getTransactionsInteractor(),
        expected = transactions.toList()
    )

}