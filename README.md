# WalletTestFramework


## Features

- **Behavior-Driven Development:** The framework uses Cucumber for behavior-driven development. This allows you to write tests in a natural language that is easy to read and understand.
- **Platform Independent:** The framework is designed to support testing on multiple platforms including Android and Web.
- **Modular and Scalable:** The framework is built with modularity in mind, allowing for easy scalability and maintenance.
- **Page Object Model:** The framework uses the Page Object Model design pattern, improving code reusability and readability.
- **Selenium and Appium Integration:** The framework integrates with Selenium for web application testing and Appium for mobile application testing.

## Getting Started

### Prerequisites

- Java
- Maven
- Selenium WebDriver
- Appium
- A real device plugged in or an emulator running for Appium tests
- Appium server must be up before the tests are executed. You can start it with the following command: `appium --base-path=/wd/hub`

## Project Structure

The project is structured as follows:

- `src/main/java`: This directory contains the main source code for the project.
    - `pages`: This package contains classes representing different pages of the application.
    - `utils`: This package contains utility classes that provide common functionality used across the project.
- `src/test/java`: This directory contains the test source code for the project.
    - `test`: This package contains the test classes.
- `src/main/resources`: This directory contains resources used by the project, such as configuration files.
- `pom.xml`: This is the Maven Project Object Model file. It contains information about the project and configuration details used by Maven to build the project.
##


### Configuration

You need to update the following values in your configuration file:

- `wallet.password`: Your wallet password
- `webdriver.chrome.driver`: Path to your ChromeDriver executable
- `extension.path`: Path to your Trust Wallet extension

Example:

```properties
wallet.password=YourWalletPassword
webdriver.chrome.driver=/path/to/chromedriver
extension.path=/path/to/Trust-Wallet.crx
```

For Appium tests, you need to update the following values in your test setup file:

- `appium:deviceName`: Your device name
- `platformName`: Your platform name (e.g., Android)
- `appium:app`: Path to your application file
- `appium:automationName`: Your automation name (e.g., UiAutomator2)
- `appium:platformVersion`: Your platform version

Example:

```java
DesiredCapabilities capabilities = new DesiredCapabilities();
capabilities.setCapability("appium:deviceName", "YourDeviceName");
capabilities.setCapability("platformName", "YourPlatformName");
capabilities.setCapability("appium:app", "/path/to/your/app");
capabilities.setCapability("appium:automationName", "YourAutomationName");
capabilities.setCapability("appium:platformVersion", "YourPlatformVersion");

```

### Installation

1. Clone the repository: `git clone https://github.com/andon-p/WalletTestFramework.git`
2. Navigate to the project directory:  `cd WalletTestFramework`
3. Install the dependencies: `mvn install`
###


### Running Tests

To run Android tests, use the following command:

```bash
mvn test -Pandroid-tests
```


To run Web tests, use the following command:

```bash
mvn test -Pweb-tests
```


### Contributing
Contributions are welcome. Please open an issue or submit a pull request for any contributions.