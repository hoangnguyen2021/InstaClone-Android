# InstaClone-Android
A Clone Instagram app on Android using Jetpack Compose for UI and Ktor for server.

## Goals
- Build a modern, native Android application using the latest and most recommended technology.
- Strictly follow best practices and the best architecture recommended by Google Android team for a robust, high-quality app.
- Use Kotlin features wherever possible and appropriate to highly decouple the app from Android platform for a potential future use in KMM.

## Tech stack
- Language: 100% Kotlin, Gradle script: mostly KTS with some Groovy.
- Frontend (Android):
  - UI: Jetpack Compose library
  - Supporting libraries: Compose libraries, Accompanist...
  - Dependency injection: Dagger Hilt (will migrate to Koin if move to KMM)
  - Reactive/asynchronous: Kotlin Coroutines and Flows
  - Network: Ktor Client
  - Local storage: ROOM, DataStore
  - Serialization: Kotlinx Serialization
- Backend:
   - Framework: Ktor Server
   - Engine: Netty
   - Database: MongoDB
   - Storage: Amazon S3
   - Dependency injection: Koin
   - Serialization: Kotlinx Serialization
- Services: Twilio, SendGrid, SendinBlue
- CI/CD: Bitrise
- Deployment: AWS Amplify
   
## Stage
In development. Registration flow is finished. Working on main features.
