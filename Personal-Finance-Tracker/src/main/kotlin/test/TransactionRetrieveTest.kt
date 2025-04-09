package org.example.test

import models.Transaction
import org.example.datasource.InMemoryTransactionDataSourceImpl

fun checkTransaction(name: String, actual: List<Transaction>, expected: List<Transaction>) = actual == expected

fun checkGetTransactionTestCases() {
    checkTransaction( //"print anything during logic time"
        name = "When the list returned is empty, return true",
        actual = InMemoryTransactionDataSourceImpl().getAllTransaction(),
        expected = emptyList()
    )
    checkTransaction(
        name = "When returned list of type \"Any\", return false",
        actual = InMemoryTransactionDataSourceImpl().getAllTransaction(),
        expected = emptyList() //Expected to be "null" not an empty list.
    )
    checkTransaction(
        name = "When the returned list is of type \"Transaction\", return true",
        actual = InMemoryTransactionDataSourceImpl().getAllTransaction(),
        expected = listOf<Transaction>()
    )
}