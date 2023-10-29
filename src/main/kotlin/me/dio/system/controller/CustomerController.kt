package me.dio.system.controller

import jakarta.validation.Valid
import me.dio.system.dto.CustomerDto
import me.dio.system.dto.CustomerUpdateDto
import me.dio.system.dto.CustomerView
import me.dio.system.model.Customer
import me.dio.system.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val customerTopUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerTopUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }

}