package ru.mityushkin.medicationmanagementbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MedicationManagementBackendApplication

fun main(args: Array<String>) {
    runApplication<MedicationManagementBackendApplication>(*args)
}
