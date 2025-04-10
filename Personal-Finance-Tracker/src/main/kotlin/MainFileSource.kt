package org.example

import kotlinx.datetime.LocalDate
import models.Category
import models.Transaction
import org.example.datasource.FileTransactionDataSourceImpl
import java.io.File

fun main() {

    val jsonFile = File("transaction.json")
    if (!jsonFile.exists()) {
        jsonFile.parentFile?.mkdirs()
        jsonFile.createNewFile()
        jsonFile.writeText("[]")
    }

    val fileDataSource = FileTransactionDataSourceImpl(jsonFile)

    fileDataSource.createTransaction(
        Transaction(
            name = "Grocery Shopping",
            isDeposit = false,
            amount = 45.67,
            category = Category.FOOD,
            creationDate = LocalDate(2025, 4, 9),
            editDate = emptyList()
        )
    )

    fileDataSource.createTransaction(
        Transaction(
            name = "Salary Deposit",
            isDeposit = true,
            amount = 3000.0,
            category = Category.SALARY,
            creationDate = LocalDate(2025, 4, 9),
            editDate = emptyList()
        )
    )

    println("\n\n All Transactions after create new two transactions\n")
    fileDataSource.getAllTransaction().forEach { println(it) }

    val firstTransaction = fileDataSource.getAllTransaction().first()
    fileDataSource.updateTransaction(
        firstTransaction.copy(amount = 50.0)
    )

    println("\n\n All Transactions after last update ")
    fileDataSource.getAllTransaction().forEach { println(it) }

    fileDataSource.removeTransaction(firstTransaction)
    println("\n\n All Transactions after removing one Transaction ")
    fileDataSource.getAllTransaction().forEach { println(it) }
}
