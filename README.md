# Movies Android App

## Overview

Movies is an Android app that allows users to discover popular movies, watch trailers, and read movie details. It leverages modern Android development technologies, including RxJava2, Retrofit, Room, Jetpack Compose, Dagger 2, and the YouTube player. The app follows the Clean Architecture principles and is thoroughly unit tested.

## Features

- Browse a curated list of popular movies fetched from [The Movie Database (TMDB)](https://www.themoviedb.org/).

- View movie details, including title, release date, overview, and poster image.
- Watch trailers of selected movies through the integrated YouTube player.
- Smooth and interactive UI powered by Jetpack Compose.

## Technologies Used

- RxJava2: For reactive programming, handling asynchronous operations, and simplifying data streams.
- Retrofit: For making HTTP requests to the TMDB API and fetching movie data.
- Room: For local data caching and offline access to favorite movies.
- Jetpack Compose: For building the user interface using a declarative approach.
- Dagger 2: For dependency injection, promoting modularity and testability.
- YouTube Player: For playing movie trailers in the app.

## Clean Architecture

The app follows Clean Architecture principles, which helps in achieving separation of concerns and maintainability. The architecture consists of the following layers:

1. **Presentation Layer**: Contains the Jetpack Compose UI components and ViewModels for displaying data and handling user interactions.
2. **Domain Layer**: Holds the core business logic, including use cases and domain models.
3. **Data Layer**: Includes data sources for fetching remote data from TMDB API and caching data in the local database using Room.
4. **Dependency Injection**: Dagger 2 is used for dependency injection, ensuring that the codebase remains modular and testable.

## Unit Testing

The app includes a comprehensive suite of unit tests, mainly focusing on the business logic implemented in the use cases. JUnit and Mockito are used for writing and executing the tests.

## Getting Started

1. Clone the repository from GitHub
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.

## Acknowledgments

- The app fetches movie data from [The Movie Database (TMDB)](https://www.themoviedb.org/). Special thanks to TMDB for providing the API.

---

Feel free to customize this README with additional information specific to your project. Include any special instructions, architecture diagrams, or further explanations of your codebase. The README should provide a clear understanding of your app and how developers can use and contribute to it.



