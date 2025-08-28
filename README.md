# Death Time Middleware in Client 

![Mi genial imagen](img/Example.jpg)

This is an example of how the Death Time middleware works.

## Router configure (build a .env.local and paste this)


`` .env.local ``

```
# <<-APIs REST->>

# NEXT_PUBLIC_SERVICE_URL="http://localhost:8080/api"

```

## Requirements

[Docker](https://docs.docker.com/desktop) 
[Nodejs](https://nodejs.org/en/download/package-manager)

## Intallation

```bash 

# start server with db
$ docker-compose up db server

# Start client
$ npm install
$ npm run dev

``` 

## Port

[localhost:3000](http://localhost:3000)

## Author

Made by Dario Marzzucco (DMarzzucco)
