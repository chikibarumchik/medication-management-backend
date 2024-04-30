package ru.mityushkin.medicationmanagementbackend.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.mityushkin.medicationmanagementbackend.persistence.entity.Medication

@Repository
interface MedicationRepository: JpaRepository<Medication, Long> {
    fun findAllByUserId(userId: Long): List<Medication>
}