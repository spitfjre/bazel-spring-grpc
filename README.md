# multi-grpc

Playground for Monorepo for gRPC Spring Services managed by Bazel

## Project

```shell
# build:all
bazel build //...

# test:all
bazel test //...
```

## API

```shell
# build
bazel build //api:pet_service__java
bazel build //api:store_service__java
```

## Pet-Service

```shell
# build
bazel build //services/pet:service

# run
bazel run //services/pet:service

# test
bazel test //services/pet:test
```

## Store-Service

```shell
# build
bazel build //services/store:service

# run
bazel run //services/store:service

# test
bazel test //services/store:test
```
