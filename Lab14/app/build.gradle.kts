plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lab14"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab14"
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
}

dependencies {

    // AndroidX библиотека для RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.1")

    // AndroidX библиотека для поддержки ViewModel и LiveData (необязательно)
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.6.2")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}