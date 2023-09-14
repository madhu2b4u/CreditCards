# Credit Card Data Display App

This Android app fetches credit card data from an external API and displays it in a list. It is built using Jetpack Compose and includes basic functionality for retrieving and rendering credit card information using MVVM clean architecture.

## Assumptions and Decisions

### Networking Library

- The example assumes the use of a networking library like Retrofit to make API calls. However, the actual implementation of network requests and parsing of responses is not provided in the code.

### Data Model

- The code assumes the existence of a data model class called `CreditCard` to represent credit card data, including fields like `creditCardType`, `creditCardNumber`, and `creditCardExpiryDate`. The structure of this data model is based on the assumed API response.

### Sample Data

- For testing purposes, the code uses a sample response (`sampleResponse`) to populate the credit card data. In a real-world scenario, this data would be fetched from the API.

### MVVM Architecture

- The code is structured following the MVVM (Model-View-ViewModel) architectural pattern, which separates concerns and promotes a clean and organized codebase.

- The `ViewModel` component is responsible for managing and providing data to the UI components (View) and contains business logic. In this case, the `CardViewModel` is used to fetch and handle credit card data.

- LiveData is utilized to observe changes in data and notify the UI when updates occur. The LiveData approach simplifies data synchronization between the ViewModel and UI components.

- The `CardRepository` acts as a data source, providing a clean API for fetching and managing credit card data. It abstracts the data source, making it easy to switch between local and remote data sources.

- Dependency injection is used to provide instances of repositories, use cases, and other components to the ViewModel, promoting a modular and testable codebase.

- This MVVM architecture facilitates code organization, testability, and separation of concerns, making it suitable for maintaining and extending the app in the long term.

### UI Components

- The UI components, including `CardList` and `CardItem`, are designed using Jetpack Compose. The assumption is that you have prior knowledge of Compose for UI development.

### Error Handling

- Error handling for network requests and other potential issues is not included in the provided code. In a production app, you would need to implement error handling and provide user feedback for failures.

### Permissions

- The code does not include permission requests for network access. Depending on your app's requirements, you may need to add permission requests to your AndroidManifest.xml.

### LiveData and ViewModel

- LiveData and ViewModel are used to manage and observe the credit card data. The ViewModel fetches data and updates LiveData, which is then observed by the Composable.

### UI Design

- The code follows a simple UI design with a top app bar and a list of credit card items. You can customize the UI according to your app's design requirements.

### Dependency Injection

- The code uses Hilt's `@AndroidEntryPoint` annotation for dependency injection. Ensure that you have Hilt set up in your project or use another dependency injection framework as needed.

### Testing

- The code includes basic unit testing for the ViewModel, RemoteDataSource, Repository, UseCase, Util classes..etc. More comprehensive testing, especially for UI components, is not included in the provided example but is essential for a production app.

### API Endpoint

- The assumption is that the API endpoint provided (`https://random-data-api.com/api/v2/credit_cards?size=100`) is accessible and returns the expected data structure.

### Data Pagination

- Data pagination is not implemented in the provided code. In a real-world scenario, you may need to implement pagination to fetch and display large datasets efficiently.

### UI Styling

- Styling of UI components, such as colors, fonts, and layouts, is not extensively defined in the example. UI styling should be tailored to match your app's design guidelines.

### UI Responsiveness

- The example does not cover UI responsiveness for different screen sizes and orientations. Ensure that your UI adapts to various device configurations as needed.

### Network Security

- The code assumes that network security measures (e.g., HTTPS) are in place for safe API communication. You should ensure secure network communication in a production app.

### Error Messages

- The code does not include specific error messages or handling for API errors. In practice, you would need to provide meaningful error messages to users in case of failures.

### API Rate Limiting

- The code does not address API rate limiting or throttling. In a real app, you should consider rate limiting and implement appropriate strategies.

### Android Version Compatibility

- The code does not specify Android version compatibility. Ensure that your app's minimum and target Android versions are defined as needed.

### Caching Credit Card Data Locally 

- To improve the user experience and reduce network requests, used caching credit card data locally on the device using [Room Database](https://developer.android.com/training/data-storage/room)


## Getting Started

Follow these steps to set up the project locally:

1. Clone the repository to your local machine.
2. Open the project in Android Studio or your preferred IDE.
3. Customize the code according to your project's requirements.
4. Implement network requests and error handling as needed.
5. Test the app thoroughly on different devices and Android versions.


