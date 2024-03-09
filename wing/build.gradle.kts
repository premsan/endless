plugins {
    id("java-library")
    id("maven-publish")
    id("com.diffplug.spotless")
}

group = "com.premsan.endless"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":base"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("io.netty:netty-all:4.1.107.Final")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat("1.19.2").aosp().reflowLongStrings().skipJavadocFormatting()
        formatAnnotations()

        licenseHeaderFile("license-header")
    }
}