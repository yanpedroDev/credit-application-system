package me.dio.system.exception

data class BusinessException(override val message: String?) : RuntimeException(message)
