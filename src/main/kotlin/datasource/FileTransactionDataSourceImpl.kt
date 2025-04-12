package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Transaction
import org.example.exceptions.TransactionNotFoundException
import org.example.utils.Validator
import java.io.File
import java.io.FileNotFoundException


class FileTransactionDataSourceImpl(private val file: File, private val validation: Validator = Validator) :
    TransactionDataSource {
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
        } else {
            throw FileNotFoundException("The path you provided it's a directory not file")
        }
    }

    override fun createTransaction(transaction: Transaction): Boolean {
        val isValid = validation.isValidName(transaction.name) &&
                validation.isValidAmount(transaction.amount) &&
                validation.isValidTransactionType(transaction.isDeposit) &&
                validation.isValidCategory(transaction.category)

        return if (isValid) {
            transactions = getTransactions().toMutableList()
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


    override fun updateTransaction(transaction: Transaction): Transaction {
        val currentTransaction = transactions.find {
            it.id == transaction.id
        }

        currentTransaction?.let {
            val editedAtList = currentTransaction.editDate.toMutableList()
            editedAtList.add(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date)
            val index = transactions.indexOfFirst {
                it.id == currentTransaction.id
            }
            val updatedTransaction = transaction.copy(editDate = editedAtList)
            transactions[index] = updatedTransaction
            return updatedTransaction
        }

        throw TransactionNotFoundException()
    }

    override fun getTransactions(): List<Transaction> {
        return if (file.exists()) {
            json.decodeFromString(file.readText())
        } else {
            emptyList()
        }
    }


    override fun removeTransaction(transaction: Transaction): Boolean {
        transactions = getTransactions().toMutableList()
        val removed = transactions.removeIf { it.id == transaction.id }
        if (removed) saveTransactions(transactions)
        return removed
    }

}