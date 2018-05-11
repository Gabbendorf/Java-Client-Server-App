# Client-Server App

[![Coverage Status](https://coveralls.io/repos/github/Gabbendorf/Java-Client-Server-App/badge.svg?branch=master)](https://coveralls.io/github/Gabbendorf/Java-Client-Server-App?branch=master)

## How to use the app
* Install Gradle
* Clone the repo with `git clone git@github.com:Gabbendorf/Java-Client-Server-App.git`
* run `gradle jar` to build the program
* In your terminal session, start the server with `java -jar build/libs/EchoServer.jar server`
* In another terminal session, connect the client to the server with `java -jar build/libs/EchoServer.jar client`
* Enter something on the client terminal session and hit `enter`: the server will echo it!

## Commands for the user
`#quit` to the client: stops both the client and the server to run.
