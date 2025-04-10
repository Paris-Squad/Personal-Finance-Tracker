package org.example.test

import datasource.TransactionDataSource
import models.Category
import models.Transaction
import org.example.datasource.InMemoryTransactionDataSourceImpl
import java.time.LocalDateTime
import java.util.*


val transaction = Transaction(
    name = "Test",
    isDeposit = false,
    amount = 200.4,
    category = Category.FOOD,
    creationTime = LocalDateTime.now(),
    editTime = emptyList()
)

fun main() {
    val dataSource: TransactionDataSource = InMemoryTransactionDataSourceImpl()

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