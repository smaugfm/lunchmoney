# lunchmoney

[![Maven Central](https://img.shields.io/nexus/s/io.github.smaugfm/lunchmoney?server=https%3A%2F%2Fs01.oss.sonatype.org)](https://search.maven.org/search?q=g:io.github.smaugfm.lunchmoney)
[![License](https://img.shields.io/github/license/smaugfm/lunchmoney.svg)](https://github.com/michaelbull/kotlin-result/blob/master/LICENSE)

Non-blocking client for [Lunchmoney](https://lunchmoney.dev) developer API for JVM-based languages.

## Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.smaugfm:lunchmoney:0.0.1-SNAPSHOT")
}
```

## Info

Written in Kotlin but without using suspending functions and coroutines for better interoperability
with Java.
Depends only on SLF4J, [reactor-netty](https://github.com/reactor/reactor-netty) for
non-blocking HTTP calls and kotlin-stdlib.

