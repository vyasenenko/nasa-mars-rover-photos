# NASA Mars Rover Photos

This project is an Android application that uses the NASA API to retrieve photos taken by the Curiosity rover. The application is implemented using Jetpack Compose and MVVM architecture.

## Description

The application displays photos from the Curiosity rover and allows the user to:
- Browse photos retrieved from the NASA API.
- Sort photos by ID.
- Go to the details screen to view information about each photo.

## Features

- **Main Screen**: Lists photos from the Curiosity rover.
- **Loading Indicator**: Displays a loading indicator while data is being downloaded.
- **Connection Status Handling**: Informs the user about problems with the internet connection.
- **Navigation**: Ability to go to the details screen, where information about the selected photo is displayed.

## Technologies

- Kotlin
- RecyclerView
- Jetpack Compose
- Retrofit for network requests
- Coroutines for asynchronous programming
- LiveData for UI state management
- ViewModel for lifecycle management

## Installation

1. Clone the repository: 
```bash
git clone https://github.com/vyasenenko/nasa-mars-rover-photos.git
```

2. Open the project in Android Studio.

3. Make sure you have the Android SDK installed and all required dependencies.

4. Get the API key from NASA and replace it in the code where necessary.

5. Run the app on an emulator or a real device.

## Usage
- When you run the app, you will see a list of photos from the Curiosity rover.
- You can tap on a photo to go to the details screen, where information about the photo will be displayed.
- Use the hamburger button to sort the photos.
- NASA API Documentation
- Jetpack Compose

## License
This project is licensed under the MIT License.