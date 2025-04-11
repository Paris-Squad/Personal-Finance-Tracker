package org.example.test

import models.Transaction
import org.example.datasource.InMemoryTransactionDataSourceImpl

fun checkTransaction(name: String, actual: List<Transaction>, expected: List<Transaction>) {
    if (actual == expected)
        println("Success!")
    else
        println("Failed!")
}

fun checkGetTransactionTestCases() {
    checkTransaction(
        name = "When the list returned is empty, return true",
        actual = InMemoryTransactionDataSourceImpl().getAllTransaction(),
        expected = emptyList()
    )
}