
# scenario4-pattern-recognition

The key goal of use case 4 is to aid with data collection of these repetitive behaviors and allow the therapist to focus more on the session vs. data collection. A secondary objective is to aid in identification of autism in recognizing specific repetitive motions and behaviors that are indicative of autism, finding similarities in patterns and potential recommendations on treatments.

For this challenge, we partnered with LinedanceAI on this initiative. They created AI algorithms for human activities & movement recognition - the AVAR API runs over video or video streams to identify and analyze humans pattern of behaviors. The API provided by LinedanceAI team will be used to achieve the secondary goal.

The use case is divided into 2 parts 
1. Front end - UI 
   - to take in user inputs 
   - video to upload
   - enter the commentary from Therapist after analyzing with benchmark
   - visualize the results after analysis
2. The backend 
   - Takes the video inputs and persists into storage
   - connects to LineDanceAI and analyze a test sequence with a benchmark sequence.
   - persists the user commentary along with the video meta data into the database
   - API to download these results for further research

## Technologies used
* Java
* Spring Boot
* Maven
* Cosmos DB
* App Service
* GitHub
* Azure App Service and App Service Plan
* node.js
* npm
* Yarn and Angular CLI
* Angular

## Proposed Architecture:

![image](https://user-images.githubusercontent.com/82239191/116749761-6a333f80-a9cf-11eb-8bcb-e5f5ecccf3fb.png)

## Screens

* Login:
![image](https://user-images.githubusercontent.com/82239191/116743758-63a0ca00-a9c7-11eb-9050-7c8d306adb52.png)

* File upload screen:
![image](https://user-images.githubusercontent.com/82239191/116743853-816e2f00-a9c7-11eb-9652-a0469afc25cc.png)

*visualizer 
![image](https://user-images.githubusercontent.com/82239191/116743927-9c40a380-a9c7-11eb-946d-92c5ecee418d.png)


## Current state:

Front end templates are build for:
1) Allow users to login
2) Allow therapist / researcher to upload benchmark and test recordings.
3) Visualize results and ability to put comments and save it.

Backend API's that were built
1) connect to LinedanceAI API and authenticate
2) Save the results of analysis using LinedanceAI along with the commentary from therapist, into the database
3) Download the results from the database for future analysis
4) A database for therapist, patient and session recordings

API's that were built -

![samplerequests](https://user-images.githubusercontent.com/81990069/117555296-fd055700-b02b-11eb-8602-8892c08b88ef.PNG)

## Next state:
1) integrate frontend screens with the backend service for authentication and other APIs.
2) Upload and store recordings to storage (blob storage / Media service)
3) Save visualization results from lineanddance api to permanent storage using the backend API.

## Future state:

1) GUI to retrieve the benchmark files to share with multiple users.
2) A metadata store for the time-series pattern recognition against therapy sessions
3) A GUI/visuals that matches the metadata to the session recording 




 
