plugins {
    kotlin("jvm") version "2.2.10"
    application
}

group = "ch.cpnv.kotlin"
version = "1.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("exercices.Exercice1Kt")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
