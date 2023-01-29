# InstaClone-Android
A Clone Instagram app on Android using Jetpack Compose for UI and Ktor for server.

Linked project: [InstaClone-Ktor](https://github.com/hoangnguyen2021/InstaClone-Ktor)

## Goals
- Build a modern, native Android application using the latest and most recommended technology.
- Strictly follow best practices and recommended architecture according to Google Android team for a robust, high-quality application.
- Use Kotlin features wherever possible and appropriate to highly decouple the app from Android platform for a potential future use in multiplatform.

## Tech stack
- Language: 100% Kotlin, Gradle scripts: Kotlin DSL, Groovy.
- Frontend (Android):
  - UI: Jetpack Compose library
  - Supporting libraries: Compose libraries, Accompanist...
  - Dependency injection: Dagger Hilt (to be migrated to Koin if move to KMM)
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
  - Security: Password4J, JWT
  - Services: Twilio, SendGrid, SendinBlue
  - CI/CD: Bitrise
  - Deployment: AWS Amplify
   
## Project Status
In development

Finished:
- Signup Flow
- Send verification email/text messages
- Login
- Create Post

In progress:
- View Posts

## Screenshots
<img src="https://user-images.githubusercontent.com/90430178/212242951-f6932be3-6ecb-4799-9d03-311af855d1f4.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212243559-1cc3eed0-da94-4271-8920-1f917b4e5d1d.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212243834-69a30a97-fe58-4195-9d04-80c0e21b852b.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244065-10107a9b-53f0-4383-a293-2978073efe8a.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244423-8c14437f-5e8c-4095-934a-9765c93d9c09.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244475-e9208aba-acbe-4e60-ad29-690d358ef25b.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244564-5c130d8b-3a03-4b38-b5df-ea68ab386dad.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244659-5bdc8aa1-b26a-456b-a990-f4902241e757.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244744-e2d72fc2-6f94-4f56-8fc8-01bd59455e85.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/212244803-ea2618cd-e7cc-4ce8-87fc-97dfaeaff35e.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/215304834-20f2fa12-7aa9-47d3-94f7-fed24ddc069b.jpg" width="240"><span> </span><img src="https://user-images.githubusercontent.com/90430178/215304890-3bd9287b-f798-40c5-8e69-03532d11bc76.jpg" width="240">

## Acknowledgement
Special thanks to developers in Android/Java/Kotlin community for free, open-source libraries that this project utilizes and builds upon to speed up the development process.
