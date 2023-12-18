# Car Game Server

## Overview
This project implements a simple HTTP server to manage a car game. The server provides endpoints to start the game,
perform a drift, repair a car, and destroy a car. It's built using Java and leverages the `com.sun.net.httpserver.HttpServer` 
for handling HTTP requests. There is no database and framework i am using only this com.sun.net.httpserver library.

## Features
- **Start Game**: Initialize the game with default car settings.
- **Drift It**: Perform a drift with the car based on the season.
- **Repair**: Repair and update the car's properties.
- **Destroy**: Remove the car from the game.

## Installation
To set up the project locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/cargameserver.git
