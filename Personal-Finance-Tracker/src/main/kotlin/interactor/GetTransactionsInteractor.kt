package org.example.interactor

import datasource.TransactionDataSource
import models.Transaction

class GetTransactionsInteractor(
    private val dataSource: TransactionDataSource,
) {
    operator fun invoke(): List<Transaction> = dataSource.getTransactions()
}