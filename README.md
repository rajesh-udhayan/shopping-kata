# Shopping app

A simple beverage shopping app developed using kotlin by following TDD approach. 

# Screens

This app consists of three screens

* Product List Screen - Fetch the products from API and store them in Room DB then show them in a list. Each item should behave as follows:
    * The prodcut's image, name, price and button to add to the cart.
    * Favourite icon button to mark the product as a favourite.
    * On clicking the item, head to the product detail screen.
    
* Product Details Screen - Display image, name, price and rating on a single screen.
    * Favourite icon button to mark the product as favourite.
    
* Favourites Screen - Display products as a list marked as favourites on the Product List& Details screen.
    * Favourite icon button to delete the product from the favourite list. The item should be removed from the list once the icon gets clicked.
    * On clicking the item, head to the product details screen.
    
# Navigation
    * Product list screen and Favourites screen should be in the bottom navigation bar.
    * Clicking on an item in Product list screen and Favourites screen should navigate to Product Details screen.

# Setup

Clone the project using below command

```bash
https://github.com/rajesh-udhayan/shopping-kata.git
```

Here are some useful Gradle/adb commands for executing this project:

 * `./gradlew runApp` - Builds and install the debug apk on the current connected device.
 * `./gradlew compileApp` - Builds the debug apk.
 * `./gradlew runUnitTests` - Execute unit tests (both unit and integration).
 * `./gradlew connectedAndroidTest` - Execute UI tests.
 
 # Dependencies used
 
 - Jetpack Compose
 
 - Android Hilt
 
 - Retrofit
 
 - Room
 
 - Coil-compose
 
 - Google Truth 
 
 - Mockk
 
 # Approaches followed 
 
 - Test Driven Development (TDD)

- UI Tests & Unit Tests

- MVVM architecture

- Dependency Injection
