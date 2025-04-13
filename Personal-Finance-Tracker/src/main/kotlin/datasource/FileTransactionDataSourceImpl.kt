package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Transaction
import org.example.exceptions.TransactionNotFoundException
import org.example.utils.addModificationDate
import java.io.File
import java.io.FileNotFoundException

class FileTransactionDataSourceImpl(
    private val file: File
) : TransactionDataSource {
    private var transactions = mutableListOf<Transaction>()
    private val json = Json { prettyPrint = true }

    init {
        if (file.exists().not()) {
            file.createNewFile()
        }

        if (file.isFile) {
            val outPutText = file.readText()
            if (outPutText.isNotBlank()) {
                transactions = json.decodeFromString(outPutText)
            }
        } else {
            throw FileNotFoundException("The path you provided it's a directory not file")
        }
    }

    override fun createTransaction(transaction: Transaction): Boolean {
        val isCreated = transactions.add(transaction)
        if (isCreated) {
            saveTransactions(transactions)
        }
        return isCreated
    }

    override fun updateTransaction(transaction: Transaction): Transaction {
        val currentTransaction = transactions.find {
            it.id == transaction.id
        }
        currentTransaction?.let {
            val index = transactions.indexOfFirst {
                it.id == currentTransaction.id
            }
            val updatedTransaction = transaction.copy(
                modificationDates = currentTransaction.addModificationDate()
            )
            transactions[index] = updatedTransaction
            saveTransactions(transactions)
            return updatedTransaction
        }
        throw TransactionNotFoundException()
    }

    override fun getTransactions(): List<Transaction> = transactions

    override fun removeTransaction(transaction: Transaction): Boolean {
        val removed = transactions.removeIf { it.id == transaction.id }
        if (removed) {
            saveTransactions(transactions)
        }
        return removed
    }


    private fun saveTransactions(transactions: List<Transaction>) {
        file.writeText(json.encodeToString(transactions))
    }
}