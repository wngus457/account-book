plugins {
    id("android.library.convention")
}

android {
    namespace = "com.juhyeon.calendar.shared.ui.common"
}

dependencies {
    implementation(libs.compose.navigation)
}