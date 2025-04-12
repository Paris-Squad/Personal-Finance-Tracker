package org.example.test

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl
import org.example.exceptions.TransactionNotFoundException
import org.example.exceptions.TransactionNotValidException
import org.example.interactor.UpdateTransactionInteractor

fun main() {
    val dataSource = FakeTransactionDataSourceImpl()
    val updateTransactionInteractor = UpdateTransactionInteractor(dataSource)

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

    val futureDateTransaction = validDepositTransaction.copy(creationDate = LocalDate(9925, 4, 1))
    // endregion

    dataSource.createTransaction(validWithdrawalTransaction)
    dataSource.createTransaction(validDepositTransaction)

    assertEquals(
        "updateDepositTransaction",
        updateDepositTransaction.id,
        updateTransactionInteractor.execute(updateDepositTransaction).id
    )

    val amountUpdateTransaction = updateDepositTransaction.copy(amount = 90000.0)
    assertEquals(
        "update Deposit Transaction with amount 90000.0",
        amountUpdateTransaction.id,
        updateTransactionInteractor.execute(amountUpdateTransaction).id
    )

    try {
        updateTransactionInteractor.execute(unAddTransaction)
        fail(
            name = "when update un added transaction will throw exception Transaction not found",
            message = "Expected TransactionNotFoundException exception"
        )
    } catch (exception: TransactionNotFoundException) {
        assertEquals(
            "when update un added transaction will throw exception Transaction not found",
            TransactionNotFoundException().message,
            exception.message
        )
    }

    try {
        updateTransactionInteractor.execute(emptyNameTransaction)
        fail(
            name = "when update un added transaction with empty name will throw exception Transaction not valid",
            message = "Expected TransactionNotValidException exception"
        )
    } catch (exception: TransactionNotValidException) {
        assertEquals(
            "when update un added transaction with empty name will throw exception Transaction not valid",
            TransactionNotValidException().message,
            exception.message
        )
    }

    try {
        updateTransactionInteractor.execute(negativeAmountTransaction)
        fail(
            name = "when update un added transaction with amount less than zero will throw exception Transaction not valid",
            message = "Expected TransactionNotValidException exception"
        )
    } catch (exception: TransactionNotValidException) {
        assertEquals(
            "when update un added transaction with amount less than zero will throw exception Transaction not valid",
            TransactionNotValidException().message,
            exception.message
        )
    }

    try {
        updateTransactionInteractor.execute(futureDateTransaction)
        fail(
            name = "when update un added transaction with date in future will throw exception Transaction not valid",
            message = "Expected TransactionNotValidException exception"
        )
    } catch (exception: TransactionNotValidException) {
        assertEquals(
            "when update un added transaction with date in future will throw exception Transaction not valid",
            TransactionNotValidException().message,
            exception.message
        )
    }

}


private fun fail(name: String, message: String) {
    throw Throwable("$name $message")
}


private fun assertEquals(name: String = "", expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("FAIL: $name. Expected $expected but got $actual")
    }
    println("PASS: $name ✅")
}

