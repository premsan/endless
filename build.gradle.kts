plugins {
    id("java")
    id("com.diffplug.spotless") version "6.25.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

spotless {
    ratchetFrom("origin/main")

    format("misc") {
        target("*.gradle", ".gitattributes", ".gitignore")

        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
}