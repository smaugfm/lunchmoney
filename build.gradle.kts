import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.2.0"
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    signing
    `maven-publish`
    application
}

group = "io.github.smaugfm"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val reactorCore = "3.5.2"
val reactorNetty = "1.1.2"
val mockserver = "5.15.0"
val logback = "1.4.5"
val javaVersion = "11"

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

detekt {
    buildUponDefaultConfig = true
    config = files("$projectDir/detekt.yml")
}

tasks {
    test {
        useJUnitPlatform()
    }
    withType<DetektCreateBaselineTask> {
        jvmTarget = javaVersion
    }
    withType<Detekt> {
        jvmTarget = javaVersion
        reports {
            html.required.set(true)
            md.required.set(true)
            xml.required.set(false)
            txt.required.set(false)
            sarif.required.set(false)
        }
    }
    withType<Jar> {
        from(rootDir.resolve("LICENSE")) {
            into("META-INF")
        }
    }
}
val dokkaHtml by tasks.existing(DokkaTask::class)

val javadocJar by tasks.registering(Jar::class) {
    group = "build"
    description = "Assembles a jar archive containing the Javadoc API documentation."
    archiveClassifier.set("javadoc")
    from(dokkaHtml)
}

kotlin {
    jvmToolchain(javaVersion.toInt())
}

application {
    mainClass.set("MainKt")
}

publishing {
    repositories {
        maven {
            if (project.version.toString().endsWith("SNAPSHOT")) {
                setUrl("https://oss.sonatype.org/content/repositories/snapshots")
            } else {
                setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            }

            val ossrhUsername: String? by project
            val ossrhPassword: String? by project

            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
            pom {
                name.set("Lunchmoney JVM")
                description.set("Non-blocking JVM client for Lunchmoney developer API")
                url.set("https://github.com/smaugfm/lunchmoney")
                inceptionYear.set("2023")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                    }
                }

                developers {
                    developer {
                        name.set("Dmytro Marchuk")
                        url.set("https://marchuk.io")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/smaugfm/lunchmoney")
                    developerConnection.set("scm:git:git@github.com:smaugfm/lunchmoney.git")
                    url.set("https://github.com/smaugfm/lunchmoney")
                }
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/smaugfm/lunchmoney/issues")
                }
            }
        }

        configure<SigningExtension> {
            val signingKeyId: String? by project // must be the last 8 digits of the key
            val signingKey: String? by project
            val signingPassword: String? by project

            useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
            sign(publications)
        }
    }
}
