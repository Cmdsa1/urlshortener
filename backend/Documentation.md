# Documentation

This is a simple CRUD SpringBoot application used to shorten a user's URL and store it. When the user acesses the URL he will be redirected to the original one.

##Techonologies

- Java 
- SpringBoot
- Vue Js
- MongoDB (atlas)

SpringBoot because it's the best framework to create REST webservices in java which my most familiar language.
Vue Js because I have been make experiments with it :), it's faster than Angular and React, and it's easier to customize.

##Creating short url

Possibilities:

- Md5 Hash
It uses the first 7 char to create the 

- Base62


##Storage

Options:

- In-Memory database
Does not require the creation of database outside of the app, however in-memory databases are not an atual representation of databases and so the most probable scenario is ending up with incompatibilities.

- DataBase
Most appropriate if this was an app going for production, however even though for this exercise it's over engineering recruiters might want to see how I deal with database management.

There are 2 possible types, SQL and NoSQL databases.

Because it's a small application, and I might want to change schemas faster NoSQL databases offer more flexibility and higher scalability.

##User Requirements Specification

- the user


##API endpoints

### POST api/url/shorten

#### Structure:

#### Security

### GET /{shortUrl}

#### Structure:


