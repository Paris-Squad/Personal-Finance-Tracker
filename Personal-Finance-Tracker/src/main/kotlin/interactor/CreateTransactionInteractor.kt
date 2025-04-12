package org.example.interactor

import datasource.TransactionDataSource
import models.Transaction
import org.example.exceptions.TransactionNotValidException
import org.example.utils.*

class CreateTransactionInteractor(
    private val dataSource: TransactionDataSource,
) {

    operator fun invoke(transaction: Transaction) {
        val isValidName = transaction.name.isValidName()
        val isValidAmount = transaction.amount.isValidAmount()
        val isValidTransactionType = transaction.isDeposit.isValidTransactionType()
        val isValidCategory = transaction.category.isValidCategory()
        val isValidCreationDate = transaction.creationDate.isValidCreationDate()

        if (isValidName &&
            isValidAmount &&
            isValidTransactionType &&
            isValidCategory &&
            isValidCreationDate
        ) {
           val isCreated = dataSource.createTransaction(transaction)
            if (isCreated.not()){
                throw Throwable("can't create Transaction")
            }
        }else{
            throw TransactionNotValidException()
        }
    }
}