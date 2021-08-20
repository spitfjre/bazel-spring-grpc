# RUN
deps = [
    "@maven//:org_springframework_boot_spring_boot_starter_parent",
    "@maven//:org_springframework_boot_spring_boot_starter_data_jpa",
    "@maven//:org_springframework_boot_spring_boot_starter_validation",
    "@maven//:net_devh_grpc_spring_boot_starter",
    "@maven//:io_grpc_grpc_stub",
    "@maven//:jakarta_annotation_jakarta_annotation_api",
    "@maven//:com_h2database_h2",
    "@maven//:org_liquibase_liquibase_core",
]

java_library(
    name = "service_lib",
    srcs = glob([
        "src/main/java/com/spitfjre/pet/**/*.java",
    ]),
    resources = glob([
        "src/main/resources/**",
    ]),
    deps = [
        "//api:pet_service__java",
        "//services:lombok",
    ] + deps,
)

java_binary(
    name = "service",
    main_class = "com.spitfjre.pet.Application",
    runtime_deps = [
        ":service_lib",
    ],
)

# TEST
load("//:junit5.bzl", "java_junit5_test")

test_deps = [
    "@maven//:org_springframework_boot_spring_boot_test",
    "@maven//:org_springframework_spring_test",
    "@maven//:org_junit_jupiter_junit_jupiter_api",
    "@maven//:org_junit_jupiter_junit_jupiter_engine",
    "@maven//:io_grpc_grpc_testing",
]

java_junit5_test(
    name = "spring_test",
    srcs = glob([
        "src/test/java/com/spitfjre/pet/**/*.java",
    ]),
    classpath_resources = ["src/main/resources/db/changelog/db.changelog-master.xml"],
    resources = glob([
        "src/main/resources/**",
    ]),
    test_package = "com.spitfjre.pet",
    deps = [
        ":service_lib",
    ] + deps + test_deps,
)
