# Skoove Code Challenge

Skoove Coding Challenge application that is able to load data from a server and playback the associated audio content using compose and Hilt based on modern Android tech-stacks and MVVM architecture.

### Architecture

This app is based on the MVVM architecture and  Clean Architecture principles, which follows [the Google's official architecture guidance](https://developer.android.com/topic/architecture)
The overall architecture of this application is composed of three layers; the UI layer  domain layer and the data layer. Each layer has dedicated components and they have each different responsibilities.
Each layer follows unidirectional event/data flow; the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.
With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

- MVVM Architecture (Declarative View - ViewModel - Model)
- View: Renders UI and delegates user actions to ViewModel
- ViewModel: Can have simple UI logic but most of the time just gets the data from Repository
- Repository Pattern: Single source of data. Responsible to get data from one or more data sources

This application adopted modularization strategies below:

- Reusability: Modularizing reusable codes properly enable opportunities for code sharing and limits code accessibility in other modules at the same time.
- Parallel Building: Each module can be run in parallel and it reduces the build time.
- Strict visibility control: Modules restrict to expose dedicated components and access to other layers, so it prevents they're being used outside the module
- Decentralized focusing: Each developer team can assign their dedicated module and they can focus on their own modules.

### Tech Stack

- Minimum SDK level 23
- 100% Kotlin based 
- Coroutines + Flow for asynchronous.
- JetPack Compose : A modern toolkit for building native Android UI.
- JetPack Lifecycle : Dispose observing data when lifecycle state changes.
- JetPack ViewModel : UI related data holder, lifecycle aware.
- JetPack App Startup : Provides a straightforward, performant way to initialize components at application startup.
- JetPack Navigation Component: Consistent navigation between views
- Coil Landscapist: Image loading and caching
- JUNIT: Unit testing
- Timber: A logger with a small, extensible API.
- Mockito: Mock objects for unit testing
- Retrofit2 & OkHttp3 : construct the REST APIs and network data.
- Hilt: Dependency injector

### Task Board

- [X] Git Initialize
- [X] Readme Setup
- [X] Kotlin-DSL
- [X] Timber
- [X] Navigation Component
- [X] Dependency Injection Using Hilt
- [X] Coroutine and Flow
- [X] Retrofit Network Client
- [X] Domain Layer Setup
- [X] Data Layer Setup
- [X] Fetch the content of Manifest file
- [X] Show audio content on scrollable list
- [X] Reload list support
- [X] Non interactive rating element for each tile
- [X] Interactive element to set the song as a favorite for each tile
- [X] Open Detail Screen by clicking on tile
- [X] Displays the content of the selected element
- [X] Media Player functionalities Setup (Load/Play/Pause)
- [ ] Current audio play time and duration
- [ ] Audio Slider
- [ ] The interactive rating element displayed as stars
- [ ] The interactive favorite element to set the song as a favorite
- [ ] Unit Test
- [ ] Instrumentation Test
- [ ] UI Test
- [ ] Dokka Documentation Tool
- [ ] MAD Score
- [ ] Leak Canary Test
- [ ] Detekt Static Code Analyze
- [ ] Release APK 
