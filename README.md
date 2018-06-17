# eHope

![app icon](https://image.ibb.co/dQVnhy/Screenshot_20180617_222719.jpg)

An Android App coded fully with Kotlin that uses Firebase as Backend, for helping people and encouraging them to donate their blood 

App features : 

  - Login / Register from firebase 
  - Logout from app in Firebase account
  - Use Google Maps API for showing nearby blood center (still working on it).
  - Calculating if the user can donate his blood.
  - Adding new blood donation to firebase realtime database . 
  - Settings page for Informations about the blood donor . 

# Google Maps API:
For the google maps API you need to add xml value file to values folder with your google maps API key to use maps in the app for more informations visit : 
https://developers.google.com/maps/documentation/android-sdk/start

# Setup firebase:
1. Create an new firebase project 
2. Add just the google-services.json to /app folder
3. Enable email authentication method in you firebase 
4. Change Realtime Database rules with this : 

```sh
{
  "rules": {
    ".read": true,
    ".write": "auth != null"
  }
}
```



# Screenshots!
- Screenshot 1 :
![](https://image.ibb.co/jnQbaJ/Screenshot_20180617_230049.jpg)
- Screenshot 2 :
![app icon](https://image.ibb.co/h45W9d/Screenshot_20180617_230052.jpg)
- Screenshot 3 :
![](https://image.ibb.co/eGtwaJ/Screenshot_20180617_230057.jpg)
- Screenshot 4 :
![](https://image.ibb.co/guiJpd/Screenshot_20180617_222723.jpg)
- Screenshot 5 :
![](https://image.ibb.co/jX59vJ/Screenshot_20180617_222734.jpg)
- Screenshot 6 :
![](https://image.ibb.co/dH59vJ/Screenshot_20180617_222748.jpg)
- Screenshot 7 :
![](https://image.ibb.co/jEQypd/Screenshot_20180617_222814.jpg)

License
----

MIT

