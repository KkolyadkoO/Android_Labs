plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lab10"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lab10"
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
    val nav_version = "2.8.2"

    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation (libs.recyclerview)
//    implementation ("androidx.recyclerview:recyclerview:1.3.2")
//    implementation("com.squareup.retrofit2:retrofit:2.4.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.3.0")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}