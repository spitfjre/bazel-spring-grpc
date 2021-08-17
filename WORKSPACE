load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_spring",
    sha256 = "4afceddd222bfd596f09591fd41f0800e57dd2d49e3fa0bda67f1b43149e8f3e",
    url = "https://github.com/salesforce/rules_spring/releases/download/2.1.3/rules-spring-2.1.3.zip",
)

RULES_JVM_EXTERNAL_TAG = "4.0"

http_archive(
    name = "rules_jvm_external",
    sha256 = "31701ad93dbfe544d597dbe62c9a1fdd76d81d8a9150c2bf1ecf928ecdf97169",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

spring_boot_version = "2.4.2"

spring_version = "5.3.9"

grpc_version = "1.31.1"

grpc_spring_version = "2.10.1.RELEASE"

junit_jupiter_version = "5.7.2"

maven_install(
    name = "maven",
    artifacts = [
        "org.springframework.boot:spring-boot-starter-parent:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-autoconfigure:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-data-jpa:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-validation:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-test:%s" % spring_boot_version,
        "org.springframework.data:spring-data-jpa:2.5.4",
        "org.springframework:spring-test:%s" % spring_version,
        "org.springframework:spring-context:%s" % spring_version,
        "org.springframework:spring-beans:%s" % spring_version,
        "net.devh:grpc-spring-boot-starter:%s" % grpc_spring_version,
        "net.devh:grpc-server-spring-boot-autoconfigure:%s" % grpc_spring_version,
        "net.devh:grpc-client-spring-boot-autoconfigure:%s" % grpc_spring_version,
        "io.grpc:grpc-stub:%s" % grpc_version,
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "com.h2database:h2:1.4.200",
        "org.liquibase:liquibase-core:4.4.3",
        "org.projectlombok:lombok:1.18.20",
        "javax.persistence:javax.persistence-api:2.2",
        "org.hibernate.validator:hibernate-validator:6.1.7.Final",
        "jakarta.validation:jakarta.validation-api:2.0.2",
        "org.junit.jupiter:junit-jupiter-engine:%s" % junit_jupiter_version,
        "org.junit.jupiter:junit-jupiter-api:%s" % junit_jupiter_version,
        "org.junit.jupiter:junit-jupiter-api:%s" % junit_jupiter_version,
    ],
    fail_on_missing_checksum = False,
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = ["https://repo1.maven.org/maven2"],
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()

http_archive(
    name = "com_google_protobuf",
    sha256 = "c6003e1d2e7fefa78a3039f19f383b4f3a61e81be8c19356f85b6461998ad3db",
    strip_prefix = "protobuf-3.17.3",
    url = "https://github.com/protocolbuffers/protobuf/archive/v3.17.3.tar.gz",
)

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()

http_archive(
    name = "rules_proto_grpc",
    sha256 = "7954abbb6898830cd10ac9714fbcacf092299fda00ed2baf781172f545120419",
    strip_prefix = "rules_proto_grpc-3.1.1",
    urls = ["https://github.com/rules-proto-grpc/rules_proto_grpc/archive/3.1.1.tar.gz"],
)

load("@rules_proto_grpc//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")

rules_proto_grpc_toolchains()

rules_proto_grpc_repos()

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

load("@rules_proto_grpc//java:repositories.bzl", rules_proto_grpc_java_repos = "java_repos")

rules_proto_grpc_java_repos()

load("@rules_proto_grpc//java:repositories.bzl", rules_proto_grpc_java_repos = "java_repos")
load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

maven_install(
    name = "io_grpc_java_maven",
    artifacts = IO_GRPC_GRPC_JAVA_ARTIFACTS,
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = ["https://repo.maven.apache.org/maven2/"],
)

load("@io_grpc_java_maven//:compat.bzl", "compat_repositories")

compat_repositories()

grpc_java_repositories()
