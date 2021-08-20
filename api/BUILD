load("@rules_proto//proto:defs.bzl", "proto_library")
load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

java_grpc_library(
    name = "pet_service__java",
    protos = [
        ":category_proto",
        ":pet_proto",
        ":pet_service__proto",
        ":tag_proto",
    ],
    visibility = [
        "//services:__subpackages__",
    ],
)

java_grpc_library(
    name = "store_service__java",
    protos = [
        ":order_proto",
        ":store_service__proto",
    ],
    visibility = [
        "//services:__subpackages__",
    ],
)

java_grpc_library(
    name = "user_service__java",
    protos = [
        ":user_proto",
        ":user_service__proto",
    ],
    visibility = [
        "//services:__subpackages__",
    ],
)

proto_library(
    name = "category_proto",
    srcs = [
        "category.proto",
    ],
)

proto_library(
    name = "order_proto",
    srcs = [
        "order.proto",
    ],
    deps = [
        "@com_google_protobuf//:timestamp_proto",
    ],
)

proto_library(
    name = "pet_proto",
    srcs = [
        "pet.proto",
    ],
    deps = [
        ":category_proto",
        ":tag_proto",
    ],
)

proto_library(
    name = "pet_service__proto",
    srcs = [
        "pet_service.proto",
    ],
    deps = [
        ":pet_proto",
        "@com_google_protobuf//:empty_proto",
    ],
)

proto_library(
    name = "store_service__proto",
    srcs = [
        "store_service.proto",
    ],
    deps = [
        ":order_proto",
        "@com_google_protobuf//:empty_proto",
    ],
)

proto_library(
    name = "tag_proto",
    srcs = [
        "tag.proto",
    ],
)

proto_library(
    name = "user_proto",
    srcs = [
        "user.proto",
    ],
)

proto_library(
    name = "user_service__proto",
    srcs = [
        "user_service.proto",
    ],
    deps = [
        ":user_proto",
        "@com_google_protobuf//:empty_proto",
    ],
)
