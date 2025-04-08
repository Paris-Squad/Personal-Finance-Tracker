package models

import java.time.LocalDateTime
import java.util.UUID

data class Transaction (
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val isDeposit : Boolean ,
    val amount: Double,
    val category: Category,
    val creationTime: LocalDateTime,
    val editTime : List<LocalDateTime>
)
