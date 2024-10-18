package com.juhyeon.calendar.shared.ui.system.theme.modal

sealed interface ModalProperties

sealed interface ModalTitle : ModalProperties {
    data class On(val text: String) : ModalTitle
    data object Off : ModalTitle
}

sealed interface ModalButtons : ModalProperties {
    data class One(val text: String) : ModalButtons
    data class Two(val leftText: String, val rightText: String) : ModalButtons
}