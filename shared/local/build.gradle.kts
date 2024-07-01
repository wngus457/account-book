plugins {
    id("android.data.convention")
}

android {
    namespace = "com.juhyeon.calendar.shared.local"
}

dependencies {
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    implementation(libs.datastore.preferences)
}