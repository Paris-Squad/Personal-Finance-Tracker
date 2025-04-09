package org.example

import models.Transaction
import org.example.datasource.InMemoryTransactionDataSourceImpl
import org.example.utils.getValidAmount
import org.example.utils.getValidCategory
import org.example.utils.getValidName
import org.example.utils.getValidTransactionType
import java.time.LocalDateTime

fun main() {

    println("  Welcome to Personal Finance Tracker  ")

    while (true) {
        println("Please choose an option:")
        println("1. Add Transaction")
        println("2. View Transactions")
        println("3. Edit Transaction")
        println("4. Delete Transaction")
        println("5. View Monthly Report")
        println("0. Exit")


        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> if(addTransactionFlow()=="Success") return
            "0" -> {
                break
            }
            else -> println("Invalid choice. Please try again.")
        }
    }

}



fun addTransactionFlow() : String  {

    val name = getValidName()
    val amount = getValidAmount()
    val type = getValidTransactionType()
    val category = getValidCategory()

    val transaction = Transaction(
        name = name,
        amount = amount,
        category = category,
        isDeposit = type,
        creationTime = LocalDateTime.now() ,
        editTime = emptyList()
    )

    val dataSource = InMemoryTransactionDataSourceImpl()
    dataSource.createTransaction(transaction)
    return "Success"
}