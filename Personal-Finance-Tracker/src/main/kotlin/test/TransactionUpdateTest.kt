package org.example.test

import models.Category
import models.Transaction
import org.example.datasource.InMemoryTransactionDataSourceImpl
import java.time.LocalDateTime

fun main() {
    val dataSource = InMemoryTransactionDataSourceImpl()

    val unAddTransaction = Transaction(
        name = "Rent Payment",
        isDeposit = true,
        amount = 150.0,
        category = Category.FOOD,
        creationTime = LocalDateTime.now(),
        editTime = emptyList()
    )

    val validWithdrawalTransaction = Transaction(
        name = "Withdrawal Transaction",
        isDeposit = false,
        amount = 150.0,
        category = Category.RENT,
        creationTime = LocalDateTime.now(),
        editTime = emptyList()
    )

    val validDepositTransaction = Transaction(
        name = "Deposit Transaction",
        isDeposit = true,
        amount = 150.0,
        category = Category.RENT,
        creationTime = LocalDateTime.now(),
        editTime = emptyList()
    )
    val updateDepositTransaction = validDepositTransaction.copy(name = "Deposit Transaction Updated", amount = 200.0)

    val emptyNameTransaction = validDepositTransaction.copy(name = "   ")

    val negativeAmountTransaction = validDepositTransaction.copy(amount = -200.0)

    val futureDateTransaction = validDepositTransaction.copy(creationTime = LocalDateTime.MAX)


    dataSource.createTransaction(validWithdrawalTransaction)
    dataSource.createTransaction(validDepositTransaction)


    dataSource.updateTransaction(updateDepositTransaction)
    dataSource.updateTransaction(unAddTransaction)
    dataSource.updateTransaction(emptyNameTransaction)
    dataSource.updateTransaction(negativeAmountTransaction)
    dataSource.updateTransaction(futureDateTransaction)


}


