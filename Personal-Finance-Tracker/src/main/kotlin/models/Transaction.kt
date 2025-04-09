package models


import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.example.utils.UUIDSerializer
import java.util.*

@Serializable
data class Transaction(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val isDeposit: Boolean,
    val amount: Double,
    val category: Category,
    val creationTime: LocalDate,
    val editTime: List<LocalDate>
)
