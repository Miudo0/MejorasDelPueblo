plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.ksp)
    id("com.google.dagger.hilt.android")


}

android {
    namespace = "com.empresa.aplicacion"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.empresa.aplicacion"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    //Obligatorio configurar un schemaDirectory para room desde 2.6.0
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
//    room{
//        schemaDirectory("$projectDir/schemas")
//    }

    buildFeatures {
        compose = true
    }
}




dependencies {
    implementation(libs.androidx.media3.database)
    //rooms, varias opcionales
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")


//    implementation("androidx.room:room-rxjava2:$room_version")
//    implementation("androidx.room:room-rxjava3:$room_version")
//    implementation("androidx.room:room-guava:$room_version")
//    testImplementation("androidx.room:room-testing:$room_version")
//    implementation("androidx.room:room-paging:$room_version")

    //hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

//para gif
    implementation (libs.coil.compose)
    implementation (libs.coil.gif)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.compose)


    implementation (libs.retrofit)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //mapas
    implementation (libs.play.services.maps)
    implementation (libs.play.services.location)

    implementation(libs.osmdroid.android.get())
}