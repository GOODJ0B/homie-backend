docker login -u joabm

call mvn install -DskipTests

docker build -t homie-backend .

docker tag homie-backend joabm/homie-backend:latest

docker push joabm/homie-backend

ssh pi@192.168.0.31 "bash ./homie-backend/update.sh"
