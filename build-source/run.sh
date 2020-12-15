#!/usr/bin/env bash
# Start a jshell session loaded with all the classes declared as dependencies to this Gradle project.

set -eu

classPathFile="build/runtime-classpath.txt"
if [[ ! -f "$classPathFile" ]]; then
  echo >&2 "Build the classpath file first. See the README for instructions."
  exit 1
fi

jshell \
  --feedback verbose \
  --class-path "$(cat "$classPathFile")"
