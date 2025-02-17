#!/bin/sh
if [ -n "$REACT_APP_BACKEND_URL" ]; then
  echo "Updating BACKEND_URL in .env with REACT_APP_BACKEND_URL"
  if [ -f /usr/share/nginx/html/.env ]; then
    sed -i "s|^BACKEND_URL=.*|BACKEND_URL=$REACT_APP_BACKEND_URL|g" /usr/share/nginx/html/.env
  else
    echo "BACKEND_URL=$REACT_APP_BACKEND_URL" > /usr/share/nginx/html/.env
  fi
fi
exec "$@"
