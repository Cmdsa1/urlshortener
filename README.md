# Documentation

This is a simple CRUD SpringBoot application used to shorten a user's URL and store it. When the user acesses the URL he will be redirected to the original one.

## Techonologies

- Java 
- SpringBoot
- Vue Js
- MongoDB (atlas & embedded)
- Maven
- Docker

SpringBoot because it's the best framework to create REST webservices in java which my most familiar language.
Vue Js because I have been make experiments with it :), it's faster than Angular and React, and it's easier to customize.

## Creating short url

Possibilities:

- Md5 Hash
It is a fast enconding method, however if we talk about product scalability we will probably end up with data corruption due to the possible data collision.

- Base62

Since it's an URl it doesn't make much sence to use Base64 (+ and /, are not symbols we migth want to add to the our Url), so the best way to shorten the Url is to get the next available index to store a document and convert it to Base 62 using a simple enconding algorithm.

## Storage

Options:

- In-Memory database

It's more than enough for really small and simple apps, however since the objective here it's to simulate a real Url shortener I will not use this option for production. I will however use an embedded database for testing.

- DataBase

Even though for this exercise it's over engineering I still want to use it because if this app was going for production it would be the best option.

There are 2 possible types, SQL and NoSQL databases.

I might want to change schemas faster and NoSQL databases offer more flexibility and higher scalability.

## User Requirements Specification


## API endpoints

### POST api/url/shorten

#### Structure:

#### Security

### GET /{shortUrl}

#### Structure:


