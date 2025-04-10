package org.example.test

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl

fun main() {
    val dataSource = FakeTransactionDataSourceImpl()


    // region ValidTransaction
    val validWithdrawalTransaction = Transaction(
        name = "Withdrawal Transaction",
        isDeposit = false,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        editDate = emptyList()
    )

    val validDepositTransaction = Transaction(
        name = "Deposit Transaction",
        isDeposit = true,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        editDate = emptyList()
    )
    val updateDepositTransaction = validDepositTransaction.copy(name = "Deposit Transaction Updated", amount = 200.0)
    // endregion

    // region InvalidTransaction
    val unAddTransaction = Transaction(
        name = "Rent Payment",
        isDeposit = true,
        amount = 150.0,
        category = Category.FOOD,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        editDate = emptyList()
    )

    val emptyNameTransaction = validDepositTransaction.copy(name = "   ")

    val negativeAmountTransaction = validDepositTransaction.copy(amount = -200.0)

    val futureDateTransaction = validDepositTransaction.copy(creationDate = LocalDate(2025, 4, 1))
    // endregion

    dataSource.createTransaction(validWithdrawalTransaction)
    dataSource.createTransaction(validDepositTransaction)


    dataSource.updateTransaction(updateDepositTransaction)
    dataSource.updateTransaction(updateDepositTransaction.copy(amount = 90000.0))

    dataSource.updateTransaction(unAddTransaction)
    dataSource.updateTransaction(emptyNameTransaction)
    dataSource.updateTransaction(negativeAmountTransaction)
    dataSource.updateTransaction(futureDateTransaction)


}


