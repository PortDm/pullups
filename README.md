# Multi platform distribution for Java application

The example demonstrates how to create multi platform distributions for applications in *Java 21*.

## Requirements

* [JDK 21+](https://www.oracle.com/java/technologies/downloads/)
* [Apache Maven 3.9.0+](https://maven.apache.org/download.cgi)
* [Inno Setup 6.2.2+](https://jrsoftware.org/isinfo.php)

## Compilation and package creation

With native packages:

    mvn clean package -P native-deploy
    
Without native packages (only *ZIP* file):

    mvn clean package
    
## Location of package files

For all platforms:

    pullups-distribution-distrib/target/pullups-distribution-<version>-no-jre.zip

For *Windows*:

    pullups-client/target/pullups-<version>.exe

For *macOS*:

    pullups-client/target/pullups-<version>.dmg

For *Linux*:

    pullups-client/target/pullups-<version>.tar.gz
    pullups-client/target/pullups_<version>.deb
    pullups-client/target/pullups_<version>.rpm

## Articles

* [Creating distributions for different operating systems in Java 21 and 22](https://habr.com/ru/companies/jugru/articles/816981/) (Russian)
* [Creating distributions for different operating systems in Java 9 and 10](https://habr.com/ru/companies/jugru/articles/340248/) (Russian)
