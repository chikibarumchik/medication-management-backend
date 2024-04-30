package ru.mityushkin.medicationmanagementbackend.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.*
import ru.mityushkin.medicationmanagementbackend.controllers.dto.MedicationRequest
import ru.mityushkin.medicationmanagementbackend.persistence.entity.Medication
import ru.mityushkin.medicationmanagementbackend.persistence.repositories.MedicationRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test", "security_disabled")
class MedicationControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var medicationRepository: MedicationRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @AfterEach
    fun clear() {
        medicationRepository.deleteAll()
    }

    @Test
    fun `should add medication`() {
        val rq = MedicationRequest(
            1L,
            "test",
            10.1,
            "test",
            "test",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            "test note"
        )

        mockMvc.post("/api/v1/medication-management") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(rq)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.userId", equalTo(rq.userId.toInt()))
            jsonPath("$.medicationName", equalTo(rq.medicationName))
            jsonPath("$.dosage", equalTo(rq.dosage))
        }
    }

    @Test
    fun `should get medications by user id`() {
        val userId = 1L
        val medication = Medication(
            1L,
            "test",
            10.1,
            "test",
            "test",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            "test note"
        )
            .also { medicationRepository.save(it) }

        mockMvc.get("/api/v1/medication-management/medications/$userId")
            .andDo {
                print()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].userId", equalTo(userId.toInt()))
                jsonPath("$[0].medicationName", equalTo(medication.medicationName))
                jsonPath("$[0].dosage", equalTo(medication.dosage))
            }
    }

    @Test
    fun `should get medication by id`() {
        val medication = Medication(
            1L,
            "test",
            10.1,
            "test",
            "test",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            "test note"
        )
            .also { medicationRepository.save(it) }

        mockMvc.get("/api/v1/medication-management/${medication.id}")
            .andDo {
                print()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.userId", equalTo(medication.userId.toInt()))
                jsonPath("$.medicationName", equalTo(medication.medicationName))
                jsonPath("$.dosage", equalTo(medication.dosage))
            }
    }

    @Test
    fun `should update medication`() {
        val medication = Medication(
            1L,
            "test",
            10.1,
            "test",
            "test",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            "test note"
        )
            .also { medicationRepository.save(it) }

        val updateRq = MedicationRequest(
            1L,
            "testNew",
            11.1,
            "testNew",
            "testNew",
            LocalDate.now(),
            LocalDate.now().plusDays(20),
            "test note new"
        )

        mockMvc.put("/api/v1/medication-management/${medication.id}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updateRq)
        }.andDo {
            print()
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.userId", equalTo(medication.userId.toInt()))
            jsonPath("$.medicationName", equalTo(updateRq.medicationName))
            jsonPath("$.dosage", equalTo(updateRq.dosage))
            jsonPath("$.unit", equalTo(updateRq.unit))
            jsonPath("$.frequency", equalTo(updateRq.frequency))
            jsonPath("$.startDate", equalTo(updateRq.startDate.format(FORMATTER)))
            jsonPath("$.endDate", equalTo(updateRq.endDate!!.format(FORMATTER)))
            jsonPath("$.notes", equalTo(updateRq.notes))
        }
    }

    @Test
    fun `should delete medication`() {
        val medication = Medication(
            1L,
            "test",
            10.1,
            "test",
            "test",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            "test note"
        )
            .also { medicationRepository.save(it) }

        mockMvc.delete("/api/v1/medication-management/${medication.id}")
            .andDo {
                print()
            }.andExpect {
                status { isNoContent() }
            }
        assertThat(medicationRepository.findById(medication.id!!).isEmpty).isTrue
    }

    companion object{
        val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}