package ru.mityushkin.medicationmanagementbackend.controllers

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.mityushkin.medicationmanagementbackend.controllers.dto.MedicationRequest
import ru.mityushkin.medicationmanagementbackend.controllers.dto.MedicationResponse
import ru.mityushkin.medicationmanagementbackend.services.MedicationService

@RestController
@RequestMapping("/api/v1/medication-management")
class MedicationController(
    private val medicationService: MedicationService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавление лечения")
    fun addMedication(@RequestBody rq: MedicationRequest): MedicationResponse {
        return medicationService.addMedication(rq).toResponse()
    }

    @GetMapping("/medications/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получение всех лечений по userId")
    fun getMedications(@PathVariable userId: Long): List<MedicationResponse> {
        return medicationService.getMedicationsByUserId(userId)
            .map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Получение лечения по id")
    fun getMedication(@PathVariable id: Long): MedicationResponse {
        return medicationService.getById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Изменениe лечения")
    fun updateMedication(
        @PathVariable id: Long,
        @RequestBody record: MedicationRequest
    ): MedicationResponse {
        return medicationService.updateMedication(id, record).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Удаление лечения")
    fun deleteMedication(@PathVariable id: Long): Unit = medicationService.deleteMedication(id)
}