plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.bzios.mecha.weathertoday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bzios.mecha.weathertoday"
        minSdk = 29
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
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${System.getProperty("kotlinVersion")}")
    implementation("androidx.appcompat:appcompat:${System.getProperty("appCompatVersion")}")
    implementation("com.google.android.material:material:${System.getProperty("materialVersion")}")
    implementation("androidx.constraintlayout:constraintlayout:${System.getProperty("constraintLayoutVersion")}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${System.getProperty("lifecycleVersion")}")
    implementation("androidx.work:work-runtime-ktx:${System.getProperty("workRuntimeKtxVersion")}")
    implementation("androidx.recyclerview:recyclerview:${System.getProperty("recyclerviewVersion")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${System.getProperty("coroutineVersion")}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${System.getProperty("coroutineVersion")}")
    implementation("com.squareup.okhttp3:okhttp:${System.getProperty("okhttp3Version")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${System.getProperty("okhttp3Version")}")
    implementation("com.squareup.retrofit2:retrofit:${System.getProperty("retrofit2Version")}")
    implementation("com.squareup.retrofit2:converter-gson:${System.getProperty("retrofit2Version")}")
    implementation("com.squareup.okio:okio:${System.getProperty("okioVersion")}")
    implementation("com.jakewharton.timber:timber:${System.getProperty("timberVersion")}")
    implementation("com.jakewharton.threetenabp:threetenabp:${System.getProperty("threetenabpVersion")}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:${System.getProperty("swipeRefreshLayoutVersion")}")
    implementation("androidx.preference:preference-ktx:${System.getProperty("preferenceKtx")}")
    implementation("androidx.security:security-crypto:${System.getProperty("securityCryptoVersion")}")
    testImplementation("junit:junit:${System.getProperty("junitVersion")}")
    androidTestImplementation("androidx.test.ext:junit:${System.getProperty("junitExtVersion")}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${System.getProperty("espressoCoreVersion")}")
}