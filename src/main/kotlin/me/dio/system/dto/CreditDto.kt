package me.dio.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import me.dio.system.model.Credit
import me.dio.system.model.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Credit Value invalid")
    val creditValue: BigDecimal,
    @field:Future val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid ID")
    val customerId: Long
) {

    fun toEntity(): Credit = Credit(
       creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
