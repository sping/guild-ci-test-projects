# Example Rails App
Dockerized Rails example application to verify various CI/CD tools.

## Initialize Database
```
docker-compose run web rake db:create
```

## Launch application
```
docker-compose up --build web
```
