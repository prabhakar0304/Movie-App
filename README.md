# Movie-App
**Kotlin-based Android Movie App**

This is a movie discovery app built using Kotlin for Android. It allows users to explore a variety of movies and TV shows through a clean and interactive user interface. The app retrieves movie data from an external API and presents it in an intuitive way, with movie titles, release dates, and additional information.

## Features
- **Movie and TV Show List:** View lists of movies and TV shows with their titles and release years.
- **Click to View Details:** Clicking on any movie or TV show will navigate to a detailed view of the item (if applicable).
- **Asynchronous Data Fetching:** Movies and TV shows are fetched asynchronously in parallel from the API using RxJava, ensuring smooth user experience.
- **Shimmer Effect:** Added shimmer effect for loading UI elements before data is available.

## Technologies Used
- **Kotlin**: Primary language used for the development of the app.
- **Jetpack Compose**: Modern UI toolkit used for building the app’s UI.
- **Retrofit & RxJava**: For making asynchronous network requests to fetch data from an external API.
- **Hilt**: For dependency injection to manage network and repository layers.
- **Glide/Coil**: Image loading libraries for fetching images asynchronously from URLs.

## Challenges Faced
1. **Parallel API Calls:** Initially faced difficulties with implementing parallel API calls and making sure both movie and TV show data loads simultaneously. This was resolved using RxJava for handling multiple network calls concurrently.
2. **UI Design:** Ensuring the UI is both functional and visually appealing was a challenge, but using Jetpack Compose made it easier to implement a modern and responsive design.
3. **Error Handling:** Handling errors effectively and providing meaningful error messages or fallback mechanisms was a key consideration to ensure the app remains stable even when the API fails or is unavailable.

## Assumptions Made
- API responses contain the data required to display movie and TV show information.
- The app is meant to display a list of movies and TV shows, with basic details available for each.

## Future Scope
- Implement search functionality to allow users to find specific movies or TV shows.
- Add authentication to enable users to save their favorite movies or TV shows.
- Expand the app to include more movie-related details, such as ratings, genres, etc.
- Improve error handling with more user-friendly messages and retries.
