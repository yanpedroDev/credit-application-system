package me.dio.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.system.model.Address
import me.dio.system.model.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "First Name invalid")
    val firstName: String,
    @field:NotEmpty(message = "Last Name invalid")
    val lastName: String,
    @field:CPF(message = "This invalid CPF")
    val cpf: String,
    @field:NotNull(message = "First Name invalid")
    val income: BigDecimal,
    @field:Email(message = "Invalid Email")
    val email: String,
    @field:NotEmpty(message = "Password incorrect")
    val password: String,
    @field:NotEmpty(message = "Zip Code invalid")
    val zipCode: String,
    @field:NotEmpty(message = "Street invalid")
    val street: String
) {

    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        ),
    )
}
