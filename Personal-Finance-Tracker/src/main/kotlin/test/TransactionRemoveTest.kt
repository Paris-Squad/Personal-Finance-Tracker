package org.example.test

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.utils.check
import org.example.datasource.FakeTransactionDataSourceImpl
import org.example.interactor.CreateTransactionInteractor
import org.example.interactor.RemoveTransactionInteractor
import java.util.UUID

fun main() {
    val dataSource = FakeTransactionDataSourceImpl()
    val removeTransactionInteractor = RemoveTransactionInteractor(dataSource)
    val createTransactionInteractor = CreateTransactionInteractor(dataSource)

    val validWithdrawalTransaction = Transaction(
        name = "Withdrawal Transaction",
        isDeposit = false,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        modificationDates = emptyList()
    )

    val validDepositTransaction = Transaction(
        name = "Deposit Transaction",
        isDeposit = true,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        modificationDates = emptyList()
    )
    val validDepositTransaction2 = Transaction(
        name = "Deposit Transaction",
        isDeposit = true,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        modificationDates = emptyList()
    )
    check(
        name = "When removing from empty list, then should return false",
        actual = removeTransactionInteractor(validWithdrawalTransaction),
        expected = false
    )

    createTransactionInteractor(transaction = validDepositTransaction)
    check(
        name = "When removing existing transaction, then should return true",
        actual = removeTransactionInteractor(validDepositTransaction),
        expected = true
    )
    val fakeTransaction = validDepositTransaction.copy(id = UUID.randomUUID())
    check(
        name = "When removing same data but different ID, then should return false",
        actual = removeTransactionInteractor(fakeTransaction),
        expected = false
    )

    createTransactionInteractor(transaction = validDepositTransaction)
    createTransactionInteractor(transaction = validDepositTransaction2)
    check(
        name = "When removing one of multiple transactions, then should return true",
        actual = removeTransactionInteractor(validDepositTransaction2),
        expected = true
    )
}

