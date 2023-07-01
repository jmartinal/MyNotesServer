val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val sqldelight_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.serialization") version "1.8.22"
    id("io.ktor.plugin") version "2.3.2"
    id("com.squareup.sqldelight") version "1.5.5"
}

group = "com.jmartinal"
version = "0.0.1"

sqldelight {
    database("MyNotesDatabase") {
        packageName = "com.jmartinal.mynotes.database"
    }
}

application {
    mainClass.set("com.jmartinal.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("com.squareup.sqldelight:sqlite-driver:$sqldelight_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}