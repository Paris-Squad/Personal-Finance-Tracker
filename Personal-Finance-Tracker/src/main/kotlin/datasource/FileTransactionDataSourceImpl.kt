package org.example.datasource

import datasource.TransactionDataSource
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Transaction
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDateTime


class FileTransactionDataSourceImpl(private val file: File) : TransactionDataSource {
    private var transactions = mutableListOf<Transaction>()

    init {
        if (file.isFile) {
            if (file.exists()) {
                val outPutText = file.readText()
                if (outPutText.isNotBlank()) {
                    transactions = Json.decodeFromString(outPutText)
                }
            }
        }else{
            throw FileNotFoundException("The path you provided it's a directory not file")
        }
    }

    override fun createTransaction(transaction: Transaction) {
        // TODO Add ur logic here
        commitToFile()
    }

    override fun removeTransaction(transaction: Transaction) {
        // TODO Add ur logic here
        commitToFile()
    }

    override fun updateTransaction(transaction: Transaction) {
        // TODO Add ur logic here
        commitToFile()
    }

    override fun getAllTransaction(): List<Transaction> {
        return transactions
    }

    override fun getTransactionByDate(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getMonthlyReport(date: LocalDateTime): List<Transaction> {
        TODO("Not yet implemented")
    }

    private fun commitToFile() {
        file.writeText(Json.encodeToString(transactions))
    }
}