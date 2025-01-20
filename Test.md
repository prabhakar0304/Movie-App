# Unit Test Cases for Movie-App

This document describes the unit test cases for the Movie-App project. The tests are designed to ensure the correctness of the app's features and functionality.

## 1. **Test Case: Movie List API Response**

### **Description**:
Verify that the movie list API returns a valid response when called.

### **Test Steps**:
1. Simulate a network call to the movie list API using mock data.
2. Check if the response contains the required data (e.g., movie titles, year, etc.).

### **Expected Outcome**:
- The API should return a list of movies with the correct data structure.
- Response status should be `200 OK`.

### **Test Method**:
- Use `Mockito` to mock the API response and `JUnit` for assertions.

```kotlin
@Test
fun testMovieListApiResponse() {
    // Mocking the API response
    val mockResponse = ApiResponse(listOf(Movie("Movie1", 2022), Movie("Movie2", 2023)))
    Mockito.`when`(movieListApiService.getMovies()).thenReturn(Single.just(mockResponse))

    // Test the API call
    movieListViewModel.fetchMovies()

    // Assert the response
    Assert.assertEquals(mockResponse.titles.size, 2)
    Assert.assertEquals(mockResponse.titles[0].title, "Movie1")
}
