package org.example

import datasource.TransactionDataSource
import kotlinx.datetime.LocalDate
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl
import org.example.datasource.FileTransactionDataSourceImpl
import org.example.interactor.*
import java.io.File
import kotlin.system.exitProcess

fun main() {
    print("Do u want to save your data in File? y/n:  ")
    var scanner = readln()
    var dataSource: TransactionDataSource = FakeTransactionDataSourceImpl()

    do {
        when (scanner) {
            "y" -> {
                val jsonFile = File("transactiontest.json")
                dataSource = FileTransactionDataSourceImpl(jsonFile)
            }

            "n" -> {
                dataSource = FakeTransactionDataSourceImpl()
            }

            else -> {
                print("please choose 'y' or 'n; only:  ")
                scanner = readln()
            }
        }
    } while (scanner != "y" && scanner != "n")

    val getTransactionsInteractor = GetTransactionsInteractor(dataSource)

    println(
        "WELCOME BACK! WHAT ACTION DO YOU NEED?\n" +
                "1. Add\n" +
                "2. Edit\n" +
                "3. Remove\n" +
                "4. View all transaction\n" +
                "5. Monthly Report\n" +
                "6. Exit"
    )
    var userSelection = readln().toIntOrNull()


    do {
        when (userSelection) {
            1 -> {
                //Nothing
                userSelection = 1
            }

            2 -> {
                userSelection = 2
            }

            3 -> {
                userSelection = 3
            }

            4 -> {
                val transactions = getTransactionsInteractor()
                transactions.forEach {
                    println(it)
                }

            }

            5 -> {
                userSelection = 5
            }

            6 -> {
                exitProcess(0)
            }

            else -> {
                print("please choose process number form the list:  ")
                userSelection = readln().toIntOrNull()
            }
        }
    } while (userSelection !in 1..6)

    when (userSelection) {
        1 -> {
            print("Enter the name: ")
            val name = readln()

            print("is this Deposit?: true/false ")
            val isDeposit = readBoolean()

            print("Enter the amount: ")
            val amount = readNumber()

            print("Enter the date: ")
            val creationDate = readDate()

            println("Select one of the following: ")
            Category.entries.forEachIndexed { index, value ->
                println("${index + 1} $value")
            }
            val category = readCategory()

            val transaction = Transaction(
                name = name,
                isDeposit = isDeposit,
                amount = amount,
                category = category,
                creationDate = creationDate,
                modificationDates = emptyList()
            )
            val createTransactionInteractor = CreateTransactionInteractor(dataSource)
            createTransactionInteractor(transaction = transaction)
        }

        2 -> {
            val transactions = getTransactionsInteractor()
            transactions.forEachIndexed { index, value ->
                println("${index + 1} $value")
                println("---------------------------------------------------------")
            }
            print("Choose the number of the transaction you want to edit: ")
            val transactionNumber = readIntegerNumber()

            val selectedTransaction = transactions[transactionNumber - 1]

            print("Enter the name: ")
            val name = readln()

            print("is this Deposit?: true/false ")
            val isDeposit = readBoolean()

            print("Enter the amount: ")
            val amount = readNumber()

            print("Enter the date: ")
            val creationDate = readDate()

            println("Select one of the following: ")
            Category.entries.forEachIndexed { index, value ->
                println("${index + 1} $value")
            }
            val category = readCategory()

            val transaction = Transaction(
                id = selectedTransaction.id,
                name = name,
                isDeposit = isDeposit,
                amount = amount,
                category = category,
                creationDate = creationDate,
                modificationDates = emptyList()
            )

            val updateTransactionInteractor = UpdateTransactionInteractor(dataSource)
            updateTransactionInteractor.execute(transaction = transaction)
        }

        3 -> {
            val transactions = getTransactionsInteractor()
            transactions.forEachIndexed { index, value ->
                println("${index + 1} $value")
                println("---------------------------------------------------------")
            }
            print("Choose the number of the transaction you want to delete: ")
            val transactionNumber = readIntegerNumber()

            val removeTransactionInteractor = RemoveTransactionInteractor(dataSource)
            removeTransactionInteractor(transaction = transactions[transactionNumber - 1])
        }

        5 -> {
            val getFinanceReportInteractor = GetFinanceReportInteractor(dataSource)

            print("Enter the start date: ")
            val startDate = readDate()

            print("Enter the end date: ")
            val endDate = readDate()


            getFinanceReportInteractor.invoke(
                startDate = startDate,
                endDate = endDate,
            )
        }
    }

}


fun readString(): String {
    var string = readln()
    do {
        when {
            string.isNotBlank() -> {
                return string
            }

            else -> {
                string = readln()
            }
        }
    } while (string.isBlank())

    return string
}

fun readNumber(): Double {
    var number = readln().toDoubleOrNull()

    do {
        when (number) {
            null -> {
                number = readln().toDoubleOrNull()
            }

            else -> {
                return number
            }
        }
    } while (number == null)

    return number
}

fun readIntegerNumber(): Int {
    var number = readln().toIntOrNull()

    do {
        when (number) {
            null -> {
                number = readln().toIntOrNull()
            }

            else -> {
                return number
            }
        }
    } while (number == null)

    return number
}

fun readBoolean(): Boolean {
    var input = readln()

    var output: Boolean? = null
    do {
        when (input) {
            "true", "t" -> {
                output = true
            }

            "false", "f" -> {
                output = false
            }

            else -> {
                input = readln()
            }
        }
    } while (output == null)

    return output
}

fun readCategory(): Category {
    val categorySize = Category.entries.size
    var number = readIntegerNumber()

    var category: Category? = null

    do {
        if (number in 1..categorySize) {
            category = Category.entries[number - 1]
        }else{
            number = readIntegerNumber()
        }
    } while (category == null)

    return category
}

fun readDate(): LocalDate {
    LocalDate.parse("1999-09-09")

    var date = readString()
    var dateParts = date.split("-")

    var localDate: LocalDate? = null

    do {
        if (dateParts.size == 3) {
            val validYear = (dateParts[0].toIntOrNull() ?: 0) > 999
            val validMonth = (dateParts[1].toIntOrNull() ?: 0) in 1..12
            val validDay = (dateParts[2].toIntOrNull() ?: 0) in 1..31
            if (validYear && validMonth && validDay) {
                localDate = LocalDate.parse(date)
            }
        }else{
            date = readString()
            dateParts = date.split("-")
        }
    } while (localDate == null)

    return localDate
}