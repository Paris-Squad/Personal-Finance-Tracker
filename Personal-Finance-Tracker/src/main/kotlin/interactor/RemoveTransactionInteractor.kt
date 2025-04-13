package org.example.interactor

import datasource.TransactionDataSource
import models.Transaction

class RemoveTransactionInteractor(
    private val dataSource: TransactionDataSource,
) {

    operator fun invoke(transaction: Transaction) {
        val isRemoved = dataSource.removeTransaction(transaction)
        if (isRemoved.not()) {
            throw Throwable("Transaction didn't remove")
        }
    }
}