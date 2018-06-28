# Client-Server App

A client-server chat written in Java.

## How to use the app

### Prerequisites
* Install Gradle
* Clone the repo with `git clone git@github.com:Gabbendorf/Java-Client-Server-App.git`
* run `gradle jar` to build the program

### Run the EchoServer
* In your terminal session, start the server with `java -jar build/libs/EchoServer.jar server`
* The server will keep running for new connections

### Connect to the server
* Open a new terminal session and connect to the server with `java -jar build/libs/EchoServer.jar <your name>`
* Enter something on the terminal and hit `enter`: the server will echo your message!
* You can connect up to 10 clients simultaneously. The server will echo all their messages in order

### Commands for the user
`#quit` from the client: stops the connection between the client and the server
`Control-C` from the server: quits the server
