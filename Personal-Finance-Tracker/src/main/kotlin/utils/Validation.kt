package org.example.utils

import models.Category

fun getValidName(): String {
    while (true) {
        print("Enter transaction name: ")
        val name = readln().trim()
        if (name.isNotEmpty() && name.all { it.isLetter() || it.isWhitespace() }) {
            return name
        } else {
            println("Invalid name. Please enter only letters and spaces.")
        }
    }
}

fun getValidAmount(): Double {
    while (true) {
        print("Enter amount: ")
        val amount = readln().toDoubleOrNull()
        if (amount != null && amount > 0) {
            return amount
        } else {
            println("Invalid amount. Please enter a positive number.")
        }
    }
}

fun getValidTransactionType(): Boolean {
    while (true) {
        print("Enter type (1 for Income, 2 for Expense): ")
        when (readln().trim()) {
            "1" -> return true
            "2" -> return false
            else -> println("Invalid type. Please enter 1 or 2.")
        }
    }
}

fun getValidCategory(): Category {
    while (true) {
        println("Choose a category:")
        Category.entries.forEachIndexed { index, category ->
            println("${index + 1}. ${category.name}")
        }
        print("Enter category number: ")
        val input = readln().toIntOrNull()
        if (input in 1..Category.entries.size) {
            return Category.entries[input!! - 1]
        } else {
            println("Invalid category. Please choose a valid number.")
        }
    }
}





