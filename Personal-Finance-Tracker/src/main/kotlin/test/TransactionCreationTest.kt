package org.example.test

import kotlinx.datetime.LocalDate
import models.Category
import models.Transaction
import org.example.datasource.FakeTransactionDataSourceImpl
import org.example.interactor.CreateTransactionInteractor
import org.example.utils.check

fun main() {
    val dataSource = FakeTransactionDataSourceImpl()
    val createTransactionInteractor = CreateTransactionInteractor(dataSource)

    val validTransaction = Transaction(
        name = "Apartment rent",
        amount = 12000.0,
        isDeposit = false,
        category = Category.RENT,
        creationDate = LocalDate(2025, 4, 9),
        modificationDates = emptyList()
    )

    val valEmptyNameTransaction = Transaction(
        name = "",
        isDeposit = true,
        amount = 30000.0,
        category = Category.SALARY,
        creationDate = LocalDate(2025, 4, 1),
        modificationDates = emptyList()
    )


    val invalidNameTransaction = Transaction(
        name = "$",
        isDeposit = true,
        amount = 150.0,
        category = Category.FOOD,
        creationDate = LocalDate(2025, 4, 9),
        modificationDates = emptyList()
    )

    val invalidAmountTransaction = Transaction(
        name = "Salary",
        isDeposit = true,
        amount = -0.0,
        category = Category.SALARY,
        creationDate = LocalDate(2025, 4, 9),
        modificationDates = emptyList()
    )


    check(
        actual = createTransactionInteractor(validTransaction),
        expected = true,
        name = "When user enter valid data return true",
    )

    check(
        actual = createTransactionInteractor(valEmptyNameTransaction),
        expected = false,
        name = "When user enter Empty name return false"
    )

    check(
        actual = createTransactionInteractor(invalidNameTransaction),
        expected = false,
        name = "When user enter special character/number into name return false",
    )

    check(
        actual = createTransactionInteractor(invalidAmountTransaction),
        expected = false,
        name = "When user enter amount less than or equal zero return false",
    )
}