#!/usr/bin/env bash
# Start a jshell session loaded with all the classes declared as dependencies to this Gradle project.

set -eu

depsFile="build/runtime-dependencies.txt"
if [[ ! -f "$depsFile" ]]; then
  echo >&2 "Build the dependencies file first. See the README for instructions."
  exit 1
fi

jshell \
  --feedback verbose \
  --class-path "$(cat "$depsFile")"
