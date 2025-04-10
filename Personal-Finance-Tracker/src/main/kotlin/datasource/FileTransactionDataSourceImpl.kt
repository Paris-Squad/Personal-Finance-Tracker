package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Transaction
import org.example.utils.Validator
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDate



class FileTransactionDataSourceImpl(private val file: File) : TransactionDataSource {
    private var transactions = mutableListOf<Transaction>()
    private val json = Json { prettyPrint = true }

    init {
        if (file.isFile) {
            if (file.exists()) {
                val outPutText = file.readText()
                if (outPutText.isNotBlank()) {
                    transactions = json.decodeFromString(outPutText)
                }
            }
        }else{
            throw FileNotFoundException("The path you provided it's a directory not file")
        }
    }

    override fun createTransaction(transaction: Transaction) :Boolean{
        val isValid = Validator.isValidName(transaction.name) &&
                Validator.isValidAmount(transaction.amount) &&
                Validator.isValidTransactionType(transaction.isDeposit) &&
                Validator.isValidCategory(transaction.category)

        return if(isValid) {
            transactions = getAllTransaction().toMutableList()
            transactions.add(transaction)
            saveTransactions(transactions)
            true
        } else {
            false
        }
    }

    private fun saveTransactions(transactions: List<Transaction>) {
        file.writeText(json.encodeToString(transactions))
    }

    override fun getAllTransaction(): List<Transaction> {
        return if (file.exists()) {
            json.decodeFromString(file.readText())
        } else {
            emptyList()
        }
    }


    override fun updateTransaction(transaction: Transaction) {
        transactions = getAllTransaction().toMutableList()
        val index = transactions.indexOfFirst { it.id == transaction.id }

        val isBlankName = transaction.name.isBlank()
        val isPositiveAmount = transaction.amount > 0

        when {
            index < 0 -> println("Update Failed: Item not found")
            isBlankName -> println("Update Failed: Name can't be empty")
            !isPositiveAmount -> println("Update Failed: Amount must be positive")
            else -> {
                val currentTransaction = transactions[index]
                val editedAtList = currentTransaction.editDate.toMutableList()
                editedAtList.add(LocalDate.now().toKotlinLocalDate())
                transactions[index] = transaction.copy(editDate = editedAtList)
                saveTransactions(transactions)
                println("Update Transaction Success ")
            }
        }
    }

    override fun removeTransaction(transaction: Transaction): Boolean {
        transactions = getAllTransaction().toMutableList()
        val removed = transactions.removeIf { it.id == transaction.id }
        if (removed) saveTransactions(transactions)
        return removed
    }

    override fun getMonthlyReport(date: kotlinx.datetime.LocalDate): List<Transaction> {

        TODO("Not yet implemented")
    }

}