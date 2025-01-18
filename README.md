# plantpal 

## description of the project developed with Java Spring Boot and React

this project allows the user to log in, sign up, create a plant and store it under the user, and then retrieve all the created plants in dashboard and bonus feature includes if the plant should be watered or not based on the last watered time the user has entered. 

## installation for frontend
clone: 
```bash 
git clone https://github.com/dariganarmanova/plantpal-spring-react-web.git
```
cd into frontend 
```bash 
cd frontend
```
install dependencies: 
```bash 
npm install 
```
run the app:
```bash 
npm start
```

## installation for backend
first, set up your own application.properties file in /resources folder with your postgresql username and password 
cd into backend folder 
```bash 
cd backend
```
install and clean with maven (make sure it is installed)
```bash 
mvn clean install
```
run the app:
```bash 
mvn spring-boot:run
```

## after running go to port localhost:8082/home

## features 
- **user authentication with jwt /api/signup and /api/login**
- **post and get request in /api/plant and /api/plants for saving the data**
- **jwt utilities with methods**
- **parse last watered feature**
- **many to one, one to many db design in postgresql** 