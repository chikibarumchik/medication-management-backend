package ru.mityushkin.medicationmanagementbackend.persistence.entity.base

import jakarta.persistence.MappedSuperclass
import ru.mityushkin.medicationmanagementbackend.persistence.entity.base.BaseAuditEntity

@MappedSuperclass
abstract class BaseAuditSoftDeleteEntity : BaseAuditEntity() {
    var isDeleted: Boolean = false
}