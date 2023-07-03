# lunchmoney

[![Maven Central](https://img.shields.io/maven-central/v/io.github.smaugfm/lunchmoney)](https://central.sonatype.com/search?q=io.github.smaugfm.lunchmoney)
[![License](https://img.shields.io/github/license/smaugfm/lunchmoney.svg)](https://github.com/smaugfm/lunchmoney/blob/master/LICENSE)

Non-blocking client for [Lunchmoney](https://lunchmoney.dev) developer API for JVM-based languages.

## Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.smaugfm:lunchmoney:0.0.2")
}
```

## Info

Written in Kotlin but without using suspending functions and coroutines for better interoperability
with Java.
