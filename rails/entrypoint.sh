#!/bin/bash
set -e

# Ensure all gems are installed (if we are not issuing a bundle command)
if [ "$1" != bundle ] || [ "$1 $2" == "bundle exec" ]; then
  bundle check || bundle install
else
  echo "(skipping bundle check/install since we are running a bundle command)"
fi

# Then exec the container's main process (what's set as CMD in the Dockerfile).
exec "$@"
