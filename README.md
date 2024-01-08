# Bookstore-backend

This repository contains a half of the whole project, so in order to run the project fully, please follow the instructions below, and then proceed to https://github.com/adz011/Bookstore-frontend.

## About Project
This project imitate a real-world bookstore application, where each user can create an account, buy and sell books.
Each books' data is retrieved from Google Books Api, so that it guarantees its validity, and provides useful information automatically, such as images, descriptions and categories. 
Hence user only needs to provide ISBN code in order to find a book they want to sell.
The payment methods use Stripe Api to help detect and prevent fraudulent transactions.
It provides a dashboard for businesses to monitor and manage transactions, view financial reports, and gain insights into customer behavior.
Stripe Api is set to test mode, so no real transactions are being processed!

This project serves educational purpose only.

## How to run
To build, compile and run this application you need to:

### Clone this repository.

```sh
git clone https://github.com/adz011/Bookstore-backend
```

### Install Java 19 or above
You might try with other versions if you have JDK 8 or above, but it is not guaranteed to compile. 

Remember to set your environment variable, for more info check https://www.java.com/en/download/help/path.html

### Install Maven 
Go to https://maven.apache.org/download.cgi and install the newest version of Maven.

Unzip it anywhere you want on your machine.

Add the bin directory of the created directory apache-maven-3.9.5 to the PATH environment variable

Confirm with mvn -v in a new shell. The result should look similar to

```sh
Apache Maven 3.9.5 (57804ffe001d7215b5e7bcb531cf83df38f93546)
Maven home: /opt/apache-maven-3.9.5
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
```

### Run Maven
To check unit tests before running application use:

```sh
mvn test
```

If tests return something similar to 

```sh
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.033 s - in com.bookstore.repository.ItemRepositoryTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 18, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.706 s
[INFO] Finished at: 2023-11-25T18:04:05+01:00
[INFO] ------------------------------------------------------------------------
```
You can run the application using command:

```sh
mvn spring-boot:run
```

To check that everything works on the backend, go to localhost:8080 in your browser, and if you get HTTP ERROR 403 you are all set!

### Alternatively use Intellij

Open this project in Intellij and go to File -> Project Structure.

If you have already installed SDK 19 or above you can use it, otherwise click on Add SDK -> Download, and choose anything above 19. Click Apply and Ok.

After downloading SDK you should be ready to go, unless Intellij doesn't recognize modules, i.e. icons of java files appear to be orange (default theme), then you have to tell Intellij explicitly.

Go to Project Structure -> Modules, you should be able to see path of the project. Go to src -> main folder and select java and mark it as Sources (blue icon), and do the same for tests under src -> test as Tests (green icon).

Click Apply and Ok.

#### To run application:
Find BookstoreApplication class under src -> main -> java -> com -> bookstore

#### To run tests: 
You can find any tests under src -> test -> java -> com -> bookstore

## APIs
This project uses Google Api and Stripe Api, both for the convenience have working keys, so you don't have to create your own.
But because they are free to use, the traffic is very limited, so it is not advised to make substantial amount of requests.


