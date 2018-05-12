# Events Organisation Web App with Google Maps, Spring Boot and Hibernate.

A small, web application allowing users to organize events with Google Maps.

## Used technologies:

* [MySQL Server 5.7](https://dev.mysql.com/downloads/mysql/)
* [IntelliJ IDEA 2018.1.3](https://www.jetbrains.com/idea/download/)

## Installation:

1) Download and install MySQL database.

2) Download and install InteliJ Idea.

3) Clone the repository into desired folder.

4) Opening project in InteliJ IDEA:

a) On welcome screen select ,,Import Project''.
b) Select pom.xml file in the directory where you cloned the repository and click OK.
c) Click Next.
d) Make sure maven project is checked and click Next.
e) JDK home path -> point to a folder where you JDK is installed.
f) Choose project name and file location or leave as default and click Finish.

5) Obtaining Google Maps Api Key:

Full proces description:
[https://developers.google.com/maps/documentation/javascript/get-api-key]

Paste the key to googleMapsApiKey file. Place it in /webapp/WEB-INF/publickeys folder.

Your local copy of application should work now.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Wojciech Szymczak**
