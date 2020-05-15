# Weather API
Weather api provides convenient endpoints to query weather information. 
* GET /current?location=Berlin - gives current weather information for the given location 
* GET /history?location=London - gives history of weather for the given location and average of last 5
records (in case there are less than 5 records than average of all records)

The service is using Open Weather API underneath to request weather information. 

### Test
There are integration and unit tests implemented. 
Unit test are testing main application logic mocking the response from Open Weather.

To run the tests run
```bash
$  ./gradlew clean test
```

### Run

To run the service
 ```bash
 $  ./gradlew bootRun
 ```

### Things to Improve
- Add docker file

### Author
Gohar Gasparyan