import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("org.jlleitschuh.gradle.ktlint") version "11.2.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("org.jetbrains.dokka") version "1.8.10"
    id("com.github.breadmoirai.github-release") version "2.4.1"
    signing
    `maven-publish`
}

group = "io.github.smaugfm"
version = "1.0.2"
val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

repositories {
    mavenCentral()
}

val reactorCore = "3.5.2"
val reactorNetty = "1.1.2"
val mockserver = "5.15.0"
val logback = "1.4.5"
val javaVersion = "11"
val resilience4jVersion = "1.7.0"

dependencies {
    api("io.projectreactor:reactor-core:$reactorCore")
    api("io.projectreactor.netty:reactor-netty-http:$reactorNetty")
    api("io.projectreactor.netty:reactor-netty-core:$reactorNetty")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    testImplementation("org.mock-server:mockserver-netty:$mockserver")
    testImplementation("org.mock-server:mockserver-client-java:$mockserver")
    testImplementation("io.github.resilience4j:resilience4j-retry:$resilience4jVersion")
    testImplementation("io.github.resilience4j:resilience4j-reactor:$resilience4jVersion")
    testImplementation("io.github.resilience4j:resilience4j-kotlin:$resilience4jVersion")
    testImplementation("io.projectreactor:reactor-test:$reactorCore")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
    testImplementation("ch.qos.logback:logback-core:$logback")
    testImplementation("ch.qos.logback:logback-classic:$logback")
    testImplementation("io.projectreactor:reactor-tools:3.5.7")
    testImplementation(kotlin("test"))
}

java {
    withJavadocJar()
    withSourcesJar()
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
        testLogging {
            events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }
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
        baseline.set(file("$rootDir/detekt-baseline.xml"))
    }
    withType<Jar> {
        from(rootDir.resolve("LICENSE")) {
            into("META-INF")
        }
    }
    named<Jar>("javadocJar") {
        from(named("dokkaJavadoc"))
    }

    fun <T : KotlinCommonCompilerOptions> KotlinCompilationTask<T>.optIn() {
        compilerOptions.freeCompilerArgs.add(
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )
    }

    named<KotlinCompilationTask<*>>("compileKotlin") {
        optIn()
    }

    named<KotlinCompilationTask<*>>("compileTestKotlin") {
        optIn()
    }
}
kotlin {
    jvmToolchain(javaVersion.toInt())
}

githubRelease {
    val githubReleaseToken: String? by project
    token(githubReleaseToken)
    repo(project.name)
    releaseAssets(
        tasks.jar.get().destinationDirectory.asFile.get().listFiles()
    )
    targetCommitish("master")
    overwrite(true)
}

publishing {
    repositories {
        maven {
            if (isReleaseVersion) {
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            } else {
                setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots")
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
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

            from(components["java"])
            pom {
                name.set(project.name)
                description.set("Non-blocking client for Lunchmoney developer API")
                url.set("https://github.com/smaugfm/lunchmoney")
                inceptionYear.set("2023")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://www.opensource.org/licenses/mit-license.php")
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
            sign(publications)
        }
    }
}
