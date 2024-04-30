package ru.mityushkin.medicationmanagementbackend.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mityushkin.medicationmanagementbackend.config.MedicationNotFoundById
import ru.mityushkin.medicationmanagementbackend.controllers.dto.MedicationRequest
import ru.mityushkin.medicationmanagementbackend.persistence.entity.Medication
import ru.mityushkin.medicationmanagementbackend.persistence.repositories.MedicationRepository

@Service
class MedicationService(
    private val medicationRepository: MedicationRepository
) {

    @Transactional
    fun addMedication(rq: MedicationRequest): Medication {
        return Medication(
            userId = rq.userId,
            medicationName = rq.medicationName,
            dosage = rq.dosage,
            unit = rq.unit,
            frequency = rq.frequency,
            startDate = rq.startDate,
            endDate = rq.endDate,
            notes = rq.notes
        )
            .also { medicationRepository.save(it) }
    }

    @Transactional
    fun getMedicationsByUserId(userId: Long): List<Medication> =
        medicationRepository.findAllByUserId(userId)

    @Transactional
    fun updateMedication(id: Long, rq: MedicationRequest): Medication {
        return getById(id).apply {
            this.userId = rq.userId
            this.medicationName = rq.medicationName
            this.dosage = rq.dosage
            this.unit = rq.unit
            this.frequency = rq.frequency
            this.startDate = rq.startDate
            this.endDate = rq.endDate
            this.notes = rq.notes
        }.also { medicationRepository.save(it) }
    }

    @Transactional
    fun getById(id: Long): Medication = medicationRepository.findById(id).orElseThrow { throw MedicationNotFoundById(id) }

    @Transactional
    fun deleteMedication(id: Long) = getById(id).let { medicationRepository.delete(it) }
}