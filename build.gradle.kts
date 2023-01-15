import java.net.URI

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.notmyfault.gradle-shadow-macos-demo"
version = "1.0.0-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.lingala.zip4j:zip4j:2.11.2")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set(null as String?)
    relocate("net.lingala.zip4j", "dev.notmyfault.zip4j")
    minimize()
    mergeServiceFiles()
}

tasks.named("build").configure {
    dependsOn("shadowJar")
}
