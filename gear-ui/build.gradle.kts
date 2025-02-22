import com.headwind.androidsandbox.AppBuildType

plugins {
    id("androidsandbox.android.library")
    id("androidsandbox.android.library.compose")
    id("androidsandbox.android.library.jacoco")
    id("androidsandbox.android.hilt")
}

android {
    namespace = "com.example.androidsandbox"
    resourcePrefix = "gear_ui_"
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.graphics.shapes)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
}
