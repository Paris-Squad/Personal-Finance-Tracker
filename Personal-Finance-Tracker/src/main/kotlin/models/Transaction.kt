package models

import kotlinx.datetime.LocalDate
import java.util.UUID

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val isDeposit: Boolean,
    val amount: Double,
    val category: Category,
    val creationDate: LocalDate,
    val editDate: List<LocalDate>
)
