package ru.mityushkin.medicationmanagementbackend.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.mityushkin.medicationmanagementbackend.controllers.dto.MedicationResponse
import ru.mityushkin.medicationmanagementbackend.persistence.entity.base.BaseAuditSoftDeleteEntity
import java.time.LocalDate

@Entity
@Table(name = "medications")
class Medication(
    var userId: Long,
    var medicationName: String,
    var dosage: Double,
    var unit: String,
    var frequency: String,
    var startDate: LocalDate,
    var endDate: LocalDate?,
    var notes: String?
): BaseAuditSoftDeleteEntity() {

    fun toResponse(): MedicationResponse = MedicationResponse(
        this.userId,
        this.medicationName,
        this.dosage,
        this.unit,
        this.frequency,
        this.startDate,
        this.endDate,
        this.notes
    )

}