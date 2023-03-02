import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.2.0"
    application
}

group = "com.github.smaugfm"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val reactorCore = "3.5.2"
val reactorNetty = "1.1.2"
val mockserver = "5.15.0"
var logback = "1.4.5"

dependencies {
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("io.projectreactor:reactor-core:$reactorCore")
    implementation("io.projectreactor.netty:reactor-netty-http:$reactorNetty")
    implementation("io.projectreactor.netty:reactor-netty-core:$reactorNetty")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    testImplementation("org.mock-server:mockserver-netty:$mockserver")
    testImplementation("org.mock-server:mockserver-client-java:$mockserver")
    testImplementation("io.projectreactor:reactor-test:$reactorCore")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
    testImplementation("ch.qos.logback:logback-core:$logback")
    testImplementation("ch.qos.logback:logback-classic:$logback")
    testImplementation(kotlin("test"))
}

configure<KtlintExtension> {
    enableExperimentalRules.set(true)
    reporters {
        reporter(ReporterType.HTML)
    }
}

tasks {
    test {
        useJUnitPlatform()
        systemProperty("java.util.logging.config.fil", "logging.properties")
    }
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}
