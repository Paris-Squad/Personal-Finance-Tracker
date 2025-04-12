package org.example.test

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl
import java.util.*


val transaction = Transaction(
    name = "Test",
    isDeposit = false,
    amount = 200.4,
    category = Category.FOOD,
    creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    editDate = emptyList()
)

fun main() {
    val dataSource: TransactionDataSource = FakeTransactionDataSourceImpl()

    assertEquals(Unit, dataSource.createTransaction(transaction), "Adding new Transaction")
    assertEquals(Unit, dataSource.removeTransaction(transaction), "Removing current Transaction")
    assertEquals(Unit, dataSource.removeTransaction(transaction.copy(id = UUID.randomUUID())), "Removing un added Transaction")

}
fun assertEquals(expected: Any, actual: Any, message: String = "") {
    if (expected != actual) {
        throw AssertionError("FAIL: $message. Expected $expected but got $actual")
    }
    println("PASS: $message ✅")
}