docker login -u joabm

call mvn install -DskipTests

docker build -t homie-backend .

docker tag homie-backend joabm/homie-backend:latest

docker push joabm/homie-backend
