# load our Spring Boot rule
load("@rules_spring//springboot:springboot.bzl", "springboot")

# create our deps list for Spring Boot
springboot_deps = [
    "@maven//:org_springframework_boot_spring_boot_starter_parent",
    "@maven//:org_springframework_boot_spring_boot_autoconfigure",
    "@maven//:org_springframework_boot_spring_boot",
    "@maven//:org_springframework_boot_spring_boot_starter_data_jpa",
    "@maven//:org_springframework_boot_spring_boot_starter_validation",
    "@maven//:org_springframework_data_spring_data_jpa",
    "@maven//:org_springframework_spring_context",
    "@maven//:org_springframework_spring_beans",
    "@maven//:net_devh_grpc_spring_boot_starter",
    "@maven//:net_devh_grpc_server_spring_boot_autoconfigure",
    "@maven//:net_devh_grpc_client_spring_boot_autoconfigure",
    "@maven//:io_grpc_grpc_stub",
    "@maven//:jakarta_annotation_jakarta_annotation_api",
    "@maven//:com_h2database_h2",
    "@maven//:org_liquibase_liquibase_core",
    "@maven//:javax_persistence_javax_persistence_api",
    "@maven//:org_hibernate_validator_hibernate_validator",
    "@maven//:jakarta_validation_jakarta_validation_api",
]

# This Java library contains the app code
java_library(
    name = "pet_service__lib",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**"]),
    deps = [
        "//api:pet_service__java",
        ":lombok",
    ] + springboot_deps,
)

# Build the app as a Spring Boot executable jar
springboot(
    name = "pet_service",
    boot_app_class = "com.spitfjre.pet.Application",
    java_library = ":pet_service__lib",
)

test_deps = [
    "@maven//:junit_junit",
    "@maven//:org_hamcrest_hamcrest_core",
]

springboot_test__deps = [
    "@maven//:org_junit_jupiter_junit_jupiter_engine",
    "@maven//:org_junit_jupiter_junit_jupiter_api",
    "@maven//:org_springframework_boot_spring_boot_test",
    "@maven//:org_springframework_spring_test",
]

java_test(
    name = "pet_service__spring_test",
    srcs = glob(["src/test/java/**/*Test.java"]),
    resources = glob(["src/test/resources/**"]),
    test_class = "SpringTests",
    deps = [
        "@proto//:pet_service__java",
        ":lombok",
        ":pet_service__lib",
    ] + springboot_test__deps,
)

java_test(
    name = "pet_service__unit_test",
    srcs = glob(["src/test/java/**/*UT.java"]),
    test_class = "UnitTests",
    deps = [":pet_service__lib"] + test_deps,
)

java_test(
    name = "pet_service__integration_test",
    srcs = glob(["src/test/java/**/*IT.java"]),
    resources = glob(["src/test/resources/**"]),
    test_class = "IntegrationTests",
    deps = [
        "@proto//:pet_service__java",
        ":lombok",
        ":pet_service__lib",
    ] + springboot_test__deps,
)

java_library(
    name = "lombok",
    exported_plugins = [":lombok_plugin"],
    exports = ["@maven//:org_projectlombok_lombok"],
)

java_plugin(
    name = "lombok_plugin",
    processor_class = "lombok.launch.AnnotationProcessorHider$AnnotationProcessor",
    deps = ["@maven//:org_projectlombok_lombok"],
)
