plugins {
    alias(libs.plugins.android.application)

    id("com.google.devtools.ksp")
}

android {

    buildFeatures {
        viewBinding = true
    }

    namespace = "com.example.lab12"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab12"
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

    var room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.3")

    implementation ("androidx.recyclerview:recyclerview:1.3.1")

    implementation ("com.google.android.material:material:1.9.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}