load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.0"

http_archive(
    name = "rules_jvm_external",
    sha256 = "31701ad93dbfe544d597dbe62c9a1fdd76d81d8a9150c2bf1ecf928ecdf97169",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

spring_boot_version = "2.4.5"

spring_version = "5.3.9"

grpc_version = "1.37.0"

grpc_spring_version = "2.12.0.RELEASE"

maven_install(
    name = "maven",
    artifacts = [
        "org.springframework.boot:spring-boot-starter-parent:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-data-jpa:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-validation:%s" % spring_boot_version,
        "org.springframework.boot:spring-boot-starter-test:%s" % spring_boot_version,
        maven.artifact(
            group = "net.devh",
            artifact = "grpc-spring-boot-starter",
            version = grpc_spring_version,
            exclusions = [
                "io.grpc:grpc-netty-shaded",
            ],
        ),
        "io.grpc:grpc-netty:%s" % grpc_version,
        "io.grpc:grpc-stub:%s" % grpc_version,
        "io.grpc:grpc-testing:%s" % grpc_version,
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "com.h2database:h2:1.4.199",
        "org.liquibase:liquibase-core:4.4.3",
        "org.projectlombok:lombok:1.18.20",
    ],
    fail_on_missing_checksum = False,
    fetch_sources = True,
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

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
    url = "https://github.com/rules-proto-grpc/rules_proto_grpc/archive/3.1.1.tar.gz",
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
    repositories = [
        "https://repo.maven.apache.org/maven2/",
    ],
)

load("@io_grpc_java_maven//:compat.bzl", "compat_repositories")

compat_repositories()

grpc_java_repositories()

load(":junit5.bzl", "junit_jupiter_java_repositories", "junit_platform_java_repositories")

JUNIT_JUPITER_VERSION = "5.7.2"

JUNIT_PLATFORM_VERSION = "1.7.2"

junit_jupiter_java_repositories(
    version = JUNIT_JUPITER_VERSION,
)

junit_platform_java_repositories(
    version = JUNIT_PLATFORM_VERSION,
)
