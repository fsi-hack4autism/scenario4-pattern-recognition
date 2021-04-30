# Visualizer Front End

## Overall Purpose
1) A database for therapist, patient and session recordings with a simple GUI for management and appropriate security guardrails
2) A marketplace or repository of benchmark (pattern recognition) files that is shareable to different users
4) A metadata store for the time-series pattern recognition against therapy sessions
5) A GUI/visuals that matches the metadata to the session recording 

## Set Up
* Azure App Service and App Service Plan
* git
* node.js
* npm
* Yarn and Angular CLI

 
## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


## Screens

* Login:
![image](https://user-images.githubusercontent.com/82239191/116743758-63a0ca00-a9c7-11eb-9050-7c8d306adb52.png)

* File upload screen:
![image](https://user-images.githubusercontent.com/82239191/116743853-816e2f00-a9c7-11eb-9652-a0469afc25cc.png)

*visualizer 
![image](https://user-images.githubusercontent.com/82239191/116743927-9c40a380-a9c7-11eb-946d-92c5ecee418d.png)





## Objectives
There are 4 functionalities that we want to build (mentioned in below)

https://github.com/fsi-hack4autism/scenario4-pattern-recognition/wiki


## current state:

Front end templates are build:
1) Allow users to login
2) Allow therapist / researcher to upload benchmark and test recordings.
3) Visualize results and ability to put comments and save it.

## next state:
1) integrate with login API and implement authentication.
2) Upload and store recordings to storage (blob storage??)
3) Save visualization results from lineanddance api to permanent storage using the api that was built in a differnt branch

## future state:
1) A database for therapist, patient and session recordings with a simple GUI for management and appropriate security guardrails
2) A marketplace or repository of benchmark (pattern recognition) files that is shareable to different users
4) A metadata store for the time-series pattern recognition against therapy sessions
5) A GUI/visuals that matches the metadata to the session recording 






