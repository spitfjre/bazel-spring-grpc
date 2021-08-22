# RUN
deps = [
    "@maven//:org_springframework_boot_spring_boot_starter_data_jpa",
    "@maven//:org_springframework_boot_spring_boot_starter_validation",
    "@maven//:net_devh_grpc_spring_boot_starter",
    "@maven//:io_grpc_grpc_stub",
    "@maven//:com_h2database_h2",
    "@maven//:org_liquibase_liquibase_core",
]

java_library(
    name = "service_lib",
    srcs = glob([
        "src/main/java/com/spitfjre/store/**/*.java",
    ]),
    resources = glob([
        "src/main/resources/**",
    ]),
    deps = [
        "//api:pet_service__java",
        "//api:store_service__java",
        "//services:lombok",
    ] + deps,
)

java_binary(
    name = "service",
    main_class = "com.spitfjre.store.Application",
    runtime_deps = [
        ":service_lib",
    ],
)

# TEST
load("//:junit5.bzl", "java_junit5_test")

test_deps = [
    "@maven//:org_springframework_boot_spring_boot_test",
    "@maven//:org_springframework_spring_test",
    "@maven//:io_grpc_grpc_testing",
    "@maven//:org_mockito_mockito_core",
] + deps

java_junit5_test(
    name = "test",
    srcs = glob([
        "src/test/java/com/spitfjre/store/**/*.java",
    ]),
    classpath_resources = ["src/main/resources/db/changelog/db.changelog-master.xml"],
    resources = glob([
        "src/main/resources/**",
    ]),
    test_package = "com.spitfjre.store",
    deps = [
        ":service_lib",
    ] + test_deps,
)
