package ru.mityushkin.medicationmanagementbackend.controllers.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class MedicationRequest(
    val userId: Long,
    val medicationName: String,
    val dosage: Double,
    val unit: String,
    val frequency: String,
    var startDate: LocalDate,
    var endDate: LocalDate? = null,
    val notes: String? = null
)

data class MedicationResponse(
    val userId: Long,
    val medicationName: String,
    val dosage: Double,
    val unit: String,
    val frequency: String,
    @JsonFormat(pattern = "dd.MM.yyyy")
    var startDate: LocalDate,
    @JsonFormat(pattern = "dd.MM.yyyy")
    var endDate: LocalDate?,
    val notes: String?
)