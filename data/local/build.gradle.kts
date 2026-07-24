plugins {
    id("android.data.convention")
}

android {
    namespace = "com.juhyeon.calendar.data.local"
}

dependencies {
    implementation(projects.data.repository)

    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
}