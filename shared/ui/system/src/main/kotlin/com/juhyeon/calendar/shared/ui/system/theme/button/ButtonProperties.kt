package com.juhyeon.calendar.shared.ui.system.theme.button

sealed interface ButtonProperties

sealed interface ButtonFlexible : ButtonProperties {
    data object False : ButtonFlexible
    data object True : ButtonFlexible
}

sealed interface ButtonForm : ButtonProperties {
    data object Filled : ButtonForm
    data object Line : ButtonForm
}

sealed interface ButtonState : ButtonProperties {
    data object Enabled : ButtonState
    data object Disabled : ButtonState
    data object Error : ButtonState
}