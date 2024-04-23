plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.ticketsearchapp"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.ticketsearchapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    // Dagger - Dependency Injection
    implementation(libs.dagger.v2411)
    kapt(libs.dagger.compiler.v2411)
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation.fragment)
    kapt(libs.androidx.hilt.compiler)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx.v240)
    implementation(libs.androidx.navigation.ui.ktx.v240)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v240)
    implementation(libs.androidx.lifecycle.livedata.ktx.v240)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android.v152)

    // Retrofit for network calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Gson for JSON parsing
    implementation(libs.gson.v288)

    // Material Components
    implementation(libs.material)

    // AndroidX
    implementation(libs.androidx.core.ktx.v170)
    implementation(libs.androidx.appcompat.v141)
    implementation(libs.androidx.constraintlayout.v213)

    // Hilt
    implementation(libs.hilt.android.v2411)
    kapt(libs.hilt.android.compiler.v2411)

    // Firebase
    implementation(libs.firebase.analytics.v2001)

    // Play Services Auth
    implementation(libs.play.services.auth.v2020)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)
}
