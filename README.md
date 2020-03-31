# Documentation

This is a simple CRUD SpringBoot application used to shorten a user's URL and store it. When the user accesses the URL, he will be redirected to the original one.

## Technologies

- Java 
- SpringBoot
- Vue Js
- MongoDB (atlas & embedded)
- Maven
- Docker

SpringBoot because it's the best framework to create REST webservices in java which my most familiar language.
Vue Js because I have been making experiments with it :), it's faster than Angular and React, and it's easier to customize.

## Creating short URL

Possibilities:

- Md5 Hash
It is a fast encoding method, however if we talk about product scalability, we will probably end up with data corruption due to the possible data collision.

- Base62

Since it's an URL it doesn't make much since to use Base64 (+ and /, are not symbols we might want to add to the our URL), so the best way to shorten the URL is to get the next available index to store a document and convert it to Base 62 using a simple encoding algorithm.

## Storage

Options:

- In-Memory database

It's more than enough for really small and simple apps, however since the objective here it's to simulate a real URL shortener I will not use this option for production. I will however use an embedded database for testing.

- DataBase

Even though for this exercise it's over engineering I still want to use it because if this app was going for production it would be the best option.

There are 2 possible types, SQL and NoSQL databases.

I might want to change schemas faster and NoSQL databases offer more flexibility and higher scalability.

#### Security


## User Requirements Specification

- a user does not fill the input and clicks the button then an error message will appear;

- a user fills the input with an invalid URL and clicks button then an error message will appear;

- a user fills the input correctly and clicks the button and the URL is not registered in the database then the short URL will appear;

- a user fills the input correctly and clicks the button and the URL is already registered in the database then the short URL and a short message will appear;

- a user clicks the short URL link and the URL is registered in the database then the user will be redirected to long URL corresponding to the short version;

- a user clicks the short URL link and the URL is not registered in the database then the user will be redirected a not found page;


## API endpoints

### POST api/url/shorten

This endpoint receives a request from the frontend (with basic authentication) with the long URL and responds in case of success with the short URL.

#### Structure:

Request:

    {

        longUrl: "https://github.com/Cmdsa1"
    
    }

Response:

Success (200)

    {
        shortUrl: https://localhost:8080/b
    
        info:""
    }

Failure (400)

    {

        errorMessage: "The URL value cannot be empty, please enter a valid URL"
    
    }


### GET /{shortUrl}

Receives get request,in case of sucess finds the Long URL correponding to the short version and redirects the user to the long URL web page. In case o failure presents a "Link no found" web page.



