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
/home
![Screenshot 2025-01-18 214557](https://github.com/user-attachments/assets/e0b56c4b-3a17-4e84-9b05-38f7763c92cc)
/spotik
![Screenshot 2025-01-18 214539](https://github.com/user-attachments/assets/a7586652-0c95-47bc-9c37-18836b7c2e75)
![Screenshot 2025-01-18 214549](https://github.com/user-attachments/assets/135103a1-5175-4fd6-8f01-4858538f8bfc)
/dashboard
![Screenshot 2025-01-18 214527](https://github.com/user-attachments/assets/3a73f851-8a52-42d2-b452-ca2fe8a2fb21)

## after running go to port localhost:8082/home

## features 
- **user authentication with jwt /api/signup and /api/login**
- **post and get request in /api/plant and /api/plants for saving the data**
- **jwt utilities with methods**
- **parse last watered feature**
- **many to one, one to many db design in postgresql** 
