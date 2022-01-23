# MovieFinder
Android application that shows trending movies from this week, allows searching for a certain movie and displays details about the movie.

## Features
- This is an android application that shows trending movies from this week.
- It has search functionality for searching a certain movie.
- When movie is clicked it shows details about the movie such as movie poster and brief description about the movie.
- It is using TMDB API for fetching data.

## Architecture and Tech features
- Fully written in Kotlin language.
- Usage of Android Jetpack.
- Built on MVVM architecture pattern.
- Uses Android Architecture Components: ViewModel, LiveData, Room, Lifecycle.
- Kotlin Coroutines for asynchronous code and multithreading.
- Offline caching with Room.
- Navigation Component.
- Data Binding.
- Retrofit for making API calls.
- Moshi to parse JSON into Kotlin objects.
- Glide for image loading.
- Material Design Components.
- Animations for transitions between screens.
- Basic Dependency Injection.
- RecyclerView.
- Encapsulation.

## Screenshots
- Trending Movies
<img src="https://user-images.githubusercontent.com/98162296/150681100-f4920cf5-1f0f-40b9-adee-df6fc898bc39.jpg" width="360" height="800">

- Searching for a movie
<img src="https://user-images.githubusercontent.com/98162296/150681645-91055a66-af7c-48b8-9435-2145141e85be.jpg" width="360" height="800">

- Details about the movie
<img src="https://user-images.githubusercontent.com/98162296/150681666-068679b6-acb0-43c0-8a8d-151101a0294a.jpg" width="360" height="800">

## Usage
For using this application you first need to register for an API key.
1. Go to TMDB site www.themoviedb.org and get your API key.
2. In the android application go to: app -> java -> util -> Constants and in the API_KEY enter you API key (API_KEY = "your_api_key_from_tmdb")
