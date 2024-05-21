#!/usr/bin/env bash
#   Use this script to test if a given TCP host/port are available

# Original script from https://github.com/vishnubob/wait-for-it
# with some modifications to exit on the first successful connection.

set -e

TIMEOUT=15
QUIET=0
HOST="$1"
PORT="$2"
shift 2
CMD="$@"

echo "Waiting for $HOST:$PORT..."

for i in `seq $TIMEOUT` ; do
    nc -z "$HOST" "$PORT" >/dev/null 2>&1 && break
    echo "Attempt $i..."
    sleep 1
done

nc -z "$HOST" "$PORT" >/dev/null 2>&1

if [[ $? -ne 0 ]] ; then
    echo "Operation timed out after $TIMEOUT seconds"
    exit 1
fi

>&2 echo "$HOST:$PORT is available - executing command"
exec $CMD
