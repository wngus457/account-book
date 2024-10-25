plugins {
    id("android.library.convention")
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.juhyeon.calendar.shared.util.android"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.shared.coreMvi)
    implementation(projects.shared.ui.common)

    implementation(libs.java.inject)

    implementation(libs.serialization)
}