plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}


android {
    namespace = "com.kenneth.lab4_start"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.kenneth.lab4_start"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        //keep the same
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Core Android KTX libraries for writing concise, idiomatic Kotlin code.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose Bill of Materials (BOM) - Manages Compose library versions.
    implementation(platform(libs.androidx.compose.bom))

    // Core Jetpack Compose UI libraries.
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Android tooling for Compose previews and inspection.
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Local unit testing dependencies.
    testImplementation(libs.junit)

    // Instrumented UI testing dependencies.
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // -- Additional Libraries --

    // Koin for dependency injection in Compose.
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    // Google Maps Compose libraries.
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.maps.compose.utils) // Optional: For map utilities like clustering.
    implementation(libs.maps.compose.widgets) // Optional: For map widgets like ScaleBar.

    // Coil for asynchronous image loading in Compose.
    implementation(libs.coil.compose)

    // Material Icons Extended for a comprehensive set of icons.
    implementation(libs.androidx.compose.material.icons.extended.android)

}
