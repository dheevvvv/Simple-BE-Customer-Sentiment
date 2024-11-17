# Customer Sentiment API

## Overview
API sederhana untuk analisis sentimen pelanggan menggunakan Java Spring Boot.

## Features
- **Add Feedback**: Tambah ulasan baru.
- **Filter by Sentiment**: Dapatkan ulasan berdasarkan sentimen.
- **Count by Sentiment**: Hitung ulasan berdasarkan sentimen.
- **Pagination**: Mendukung paginasi ulasan.

## Technologies
- Java 17
- Spring Boot
- Spring IoC 
- Java Stream
- Native SQL Query
- JPA (Hibernate)
- H2 Database (In-Memory)
- JUnit & Mockito (Testing)

## Endpoints
1. `POST /api/feedback/add`: Tambah ulasan baru.
2. `GET /api/feedback/sentiment/{sentiment}`: Dapatkan ulasan berdasarkan sentimen.
3. `GET /api/feedback/sentiment/count?sentiment={sentiment}`: Hitung ulasan berdasarkan sentimen.
4. `GET /api/feedback/sentiment/paged?sentiment={sentiment}&page={page}&size={size}`: Ulasan dengan paginasi.

