plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mam_jetz"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.mam_jetz"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    // Tambahkan ini untuk Onboard DotsIndicator (Modul Pertemuan 11)
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    // Pastikan RecyclerView ada (kalau belum ada, tambahkan ini juga)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    // MPAndroidChart dari GeeksforGeeks Tutorial
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}