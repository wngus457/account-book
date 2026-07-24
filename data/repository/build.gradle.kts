plugins {
    id("android.data.convention")
}

android {
    namespace = "com.juhyeon.calendar.data.repository"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.shared.util.kotlin)
}