rootProject.name = "lunchmoney"

plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.5"
}

gitHooks {
    preCommit {
        tasks("ktlintFormat", "ktlintCheck")
    }
    hook("prePush") {
        tasks("clean", "build")
    }
    createHooks(true)
}
