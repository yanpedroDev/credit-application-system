package me.dio.system.service.impl

import me.dio.system.exception.BusinessException
import me.dio.system.model.Customer
import me.dio.system.repository.CustomerRepository
import me.dio.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("Id $id not found")
        }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
        this.customerRepository.deleteById(id)
    }
}