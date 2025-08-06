# poc-testing-kata

Add to a docker container, add a rest interface and save results in PostgreSQL db.
Automated Threat Analysis Tests with TreatAgile


## Install docker

## Build the image: 
```bash
docker build -t poc-testing-kata .
```
## Run the container:
```bash
docker run -p 8080:8080 poc-testing-kata
```