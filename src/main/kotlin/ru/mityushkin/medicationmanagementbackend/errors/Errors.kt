package ru.mityushkin.medicationmanagementbackend.config

open class BadRequestExceptions(override val message: String?) : RuntimeException(message)

class MedicationNotFoundById(val id: Long): BadRequestExceptions("Medication with userId=$id is not found")

