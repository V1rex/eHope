# eHope

![App Slogon](https://i.imgur.com/SQA8xXR.png)

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)
* [Google Maps API](#maps)
* [Code examples in the app](#code-examples-in-the-app)

An Android App coded fully with Kotlin that uses Firebase as Backend, for helping people and encouraging them to donate their blood 

## General info
eHope is an app where users can learn about blood donations, test abilities ( see if he can do a blood donations ) , see near blood donations centers . In general you can be a hero with this app .  

## Screenshots
![Screenshot 1](https://i.imgur.com/21kCvcs.png)

## Technologies
* This apps uses firebase as backend service for the app .

## Features
* Adding donations that user have done 
* Uploading user profile photo 
* Testing user ability of donating blood
* See near blood donations center (still not developed) 
* Get informations about blood donations 

## Code examples in the app 
This app show many codes example in kotlin on :
* Login / Register from firebase auth .  
* Uploading profile photo of user in Firebase storage . 
* Get a list of data and updating it in firebase realtime database . 
* Handiling google maps API . 
* Retrieving photos from firebase storage using Glide Library 

# Google Maps API:
For the google maps API you need to add xml value file to values folder with your google maps API key to use maps in the app for more informations visit : 
https://developers.google.com/maps/documentation/android-sdk/start

# Setup firebase:
1. Create an new firebase project 
2. Add just the google-services.json to /app folder
3. Enable email authentication method in your firebase project
4. Change Realtime Database rules with this : 

```sh
{
  "rules": {
    ".read": true,
    ".write": "auth != null"
  }
}
```
License
----

MIT

