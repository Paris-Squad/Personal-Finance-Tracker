package org.example.test
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.common.check
import org.example.datasource.FakeTransactionDataSourceImpl
import java.util.UUID

fun main(){
    val dataSource = FakeTransactionDataSourceImpl()

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
    val validDepositTransaction2 = Transaction(
        name = "Deposit Transaction",
        isDeposit = true,
        amount = 150.0,
        category = Category.RENT,
        creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        editDate = emptyList()
    )
    check(
        name = "When removing from empty list, then should return false",
        actual=dataSource.removeTransaction(validWithdrawalTransaction),
        expected =  false
    )

    dataSource.createTransaction(transaction = validDepositTransaction)
    check(
        name = "When removing existing transaction, then should return true",
        actual = dataSource.removeTransaction(validDepositTransaction),
        expected = true
    )
    val fakeTransaction = validDepositTransaction.copy(id = UUID.randomUUID())
    check(
        name = "When removing same data but different ID, then should return false",
        actual =  dataSource.removeTransaction(fakeTransaction) ,
        expected =  false
    )
    dataSource.createTransaction(transaction = validDepositTransaction)
    dataSource.createTransaction(transaction = validDepositTransaction2)
    check(
        name = "When removing one of multiple transactions, then should return true",
        actual= dataSource.removeTransaction(validDepositTransaction2),
        expected = true
    )
}

