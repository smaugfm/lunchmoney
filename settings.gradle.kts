import java.nio.file.Paths

rootProject.name = "lunchmoney"

plugins {
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "1.1.5"
}

gitHooks {
    preCommit {
        from(Paths.get("pre-commit.sh").toFile())
    }
    hook("pre-push") {
        tasks("clean", "build")
    }
    createHooks(true)
}
