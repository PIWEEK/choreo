This is a Micronaut project that builds the Choreo's API that front-app consumes.

## Configuration

Define these environment variables to connect with your Postgresql database:

```shell
$ export JDBC_URL="jdbc:postgresql://127.0.0.1:5432/<yourdb>"
$ export JDBC_USER=<dbuser>
$ export JDBC_USER=<dbpassword>
```

## Running the app

In order to recreate the model on the database for the very first time, let's simply start the application: 

```shell
$ ./gradlew run
```

Then define an environment variable enabling the migrations for future runs:
```shell
$ export ENABLE_FLYWAY=true
````

And finally, start the application again `$ ./gradlew run` to load the initial data.

## API

### API documentation

https://documenter.getpostman.com/view/3299216/TzsWtpnX

### API Requests

The application is initially configured to return the first 100 entries each time, although it's configurable editing `ApplicationConfigurationProperties.java`:

```java
Integer DEFAULT_MAX = 10
```

There are several general parameters that any request accepts to, for example, change the sorting, ordering, or accessing other elements providing an offset:

```curl
curl --location --request GET 'http://localhost:8080/main-tasks?offset=10&sort=name&order=asc'
```




## Micronaut 2.5.9 Documentation

- [User Guide](https://docs.micronaut.io/2.5.9/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.5.9/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.5.9/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

