plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.lab5"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab5"
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
}

dependencies {
    val nav_version = "2.8.0"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation ("androidx.navigation:navigation-ui:$nav_version")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}