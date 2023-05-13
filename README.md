# Drones

This is a project built using Spring Boot 3.0.6, JAVA 17, and MAVEN 3.6.3. To build the project, use the command "mvn
clean package". To run the tests, use the command "mvn test". To run the project, use the command "mvn spring-boot:run".
To stop the project, use the `CTRL + C` keyboard shortcut in the terminal or command prompt window where the project is
running.

The project is accessible at port number 8080 and it utilizes an in-memory database called "h2-database". The
configuration of the database can be found in the "application.properties" file. The "Model" and "State" are
automatically initialized every time the project runs.

# API Documentation

1. ### Register Drones <br>

   Api Descriptions:

   | path        | dispatch/drone/register |
   |-------------|-------------------------| 
   | Http Method | POST                    | 
   | Consumes    | application/json        |
   | Produces    | application/json        |

   Request:

   | Parameter      | Description                                                              | Data Type | Mandatory | Validation |
   |----------------|--------------------------------------------------------------------------|-----------|-----------|------------|
   | serialNumber | Drone Serial Number required to identify uniqueness of drone             | String    | YES | Max length 100 character |
   | model | Defines the capacity of drone whose values are defined in Model (#model) | String    | YES | Input must be only mentioned model |
   | batteryCapacity | Defines the remaning battery level                                       | Integer | YES | Value must be bettween 0 - 100 | 

   ## Model

   | VALUE       | CAPACITY         |
   |-------------|------------------|
   |     LIGHT_WEIGHT| 100             |
   | MIDDLE_WEIGHT   | 250 |
   | CRUISER_WEIGHT  | 350 |
   | HEAVY_WEIGHT | 500 |

   Request Body

```json
   { 
   "serialNumber": "DRONE_1", 
   "model": "HEAVY_WEIGHT", 
   "batteryCapacity": "100" 
   }
```
   
   Response:

   | Parameter      | Description                                                                       | Data Type | 
   |----------------|-----------------------------------------------------------------------------------|-----------|
   | httpStatus | Indicates the Registration proccess status.                                       | String    | 
   | message | Message sent by the backend server to indicate the status of registration process | String    | 
   
   Response Body

   ### Successful Response 
```json
  { 
   "httpStatus": "OK", 
   "message": "Drone was registered successfully."
   }
```

   ### Invalid Input Error Response
```json
{
   "httpStatus": "BAD_REQUEST",
   "message": "Validation error",
   "timestamp": "13-05-2023 12:13:59 PM",
   "apiErrors": [
      {
         "field": "batteryCapacity",
         "rejectedValue": 1000,
         "message": "Battery capacity must be between 0 to 100%."
      },
      {
         "field": "serialNumber",
         "rejectedValue": "",
         "message": "Serial number is required."
      },
      {
         "field": "model",
         "rejectedValue": false,
         "message": "Invalid model. Please go through the API doc provided."
      }
   ]
}
```
   

   ### Validation Response
```json
   {
   "httpStatus": "CONFLICT",
   "message": "Provided serial number of drone already exist.",
   "timestamp": "13-05-2023 12:14:25 PM"
  }
```

   2. ### Load Drones <br>

      Api Descriptions:

      | path        | dispatch/drone/load |
         |-------------|-------------------------| 
      | Http Method | POST                    | 
      | Consumes    | application/json        |
      | Produces    | application/json        |

      Request:

      | Parameter       | Description                                               | Data Type | Mandatory | Validation                                               |
      |-----------------|-----------------------------------------------------------|-----------|-----------|----------------------------------------------------------|
      | serialNumber    | Drone Serial Number which is used in registration process | String    | YES       |                                                          |
      | medicationItems | Medication Item to be loaded in drones                    | List      | YES       |                                                          |
      | name            | Name of medication                                        | String    | YES       | Only letters, numbers, hyphen and underscore are allowed | 
      | weight          | Weight of Medication in gram                              | Double    | YES       | Medication weight should be minimum 1 gram.              | 
      | code            | Code to represent medication item                         | Integer   | YES       | allowed only upper case letters, underscore and numbers  | 
      | image           | Image of medication Item                                  | String    | NO        | Only accepts valid bas64                                 | 

Request Body

```json
   {
   "serialNumber": "DRONE_1",
   "medicationItems": [
      {
         "name": "Aspirin",
         "weight": 30,
         "code": "Aspirin"
      },
      {
         "name": "Celecoxib",
         "weight": 40,
         "code": "Celecoxib"
      },
      {
         "name": "Risperidone",
         "weight": 50,
         "code": "Risperidone",
         "image": "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAsICAoIBwsKCQ......"
      }
   ]
}
```

Response:

| Parameter      | Description                                                                       | Data Type | 
   |----------------|-----------------------------------------------------------------------------------|-----------|
| httpStatus | Indicates the Registration proccess status.                                       | String    | 
| message | Message sent by the backend server to indicate the status of registration process | String    | 

Response Body

### Successful Response
```json
  {
   "httpStatus": "OK",
   "message": "Drone successfully loaded with all the medication items."
  }
```

### Invalid Input Error Response
```json
{
   "httpStatus": "BAD_REQUEST",
   "message": "Validation error",
   "timestamp": "13-05-2023 12:44:36 PM",
   "apiErrors": [
      {
         "field": "serialNumber",
         "rejectedValue": "",
         "message": "Drone serial number is required."
      }
   ]
}
```


### Validation Response
```json
   {
   "httpStatus": "CONFLICT",
   "message": "Drone is not available for loading.",
   "timestamp": "13-05-2023 12:39:54 PM"
  }

```
```json
{
    "httpStatus": "CONFLICT",
    "message": "Drone of given serial number not available.",
    "timestamp": "13-05-2023 12:40:14 PM"
}
```

```json
{
    "httpStatus": "CONFLICT",
    "message": "The weight of the loaded item exceeds the maximum capacity.",
    "timestamp": "13-05-2023 12:44:08 PM"
}
```
3. ### Check Loaded Medication


   Api Descriptions:


   | path        | dispatch/drone/check/loaded/medication |
   |-------------|----------------------------------------| 
   | Http Method | POST                                   | 
   | Consumes    | application/json                       |
   | Produces    | application/json                       |


   Request:


   | Parameter       | Description                                               | Data Type | Mandatory | Validation                                               |
   |-----------------|-----------------------------------------------------------|-----------|-----------|----------------------------------------------------------|
   | serialNumber    | Drone Serial Number which is used in registration process | String    | YES       |                                                          | 

Request Body

```json
   {
   "serialNumber": "DRONE_1"
  }
```

Response:

| Parameter       | Description                                                                       | Data Type | 
   |-----------------|-----------------------------------------------------------------------------------|-----------|
| httpStatus      | Indicates the Registration proccess status.                                       | String    | 
| message         | Message sent by the backend server to indicate the status of registration process | String    | 
| data            | Represents the data fetched                                                       | Object    | 
| loadedDate      | Date with time in which data was loaded                                           | String    | 
| totalItem       | Count of total item which was loaded                                              | Integer   | 
| medicationItems | Medication Item Lits                                                              | List      | 
| name            | Name of medication                                                                | String    | 
| weight          | weight of medication                                                              | String    | 
| code            | Code of medication                                                                | String    | 
| image           | Image of medication                                                               | String    | 

Response Body

### Successful Response
```json
{
   "httpStatus": "OK",
   "message": "Successfully fetched loaded medication items",
   "data": {
      "loadedDate": "13-05-2023 05:45:41 AM",
      "totalItem": 3,
      "medicationItems": [
         {
            "name": "Aspirin",
            "weight": 30,
            "code": "Aspirin"
         },
         {
            "name": "Celecoxib",
            "weight": 40,
            "code": "Celecoxib"
         },
         {
            "name": "Risperidone",
            "weight": 50,
            "code": "Risperidone"
         }
      ]
   }
}
```

### Invalid Input Error Response
```json
{
   "httpStatus": "BAD_REQUEST",
   "message": "Validation error",
   "timestamp": "13-05-2023 12:44:36 PM",
   "apiErrors": [
      {
         "field": "serialNumber",
         "rejectedValue": "",
         "message": "Drone serial number is required."
      }
   ]
}
```


### Validation Response
```json
{
   "httpStatus": "CONFLICT",
   "message": "Drone with given serial number not available or not in loaded state.",
   "timestamp": "13-05-2023 12:50:02 PM"
}
```
4. ### Check available drones for loading

   Api Descriptions:

   | path        | dispatch/drone/check/available |
   |-------------|----------------------------------------| 
   | Http Method | GET                                    | 
   | Produces    | application/json                       |


Response:

| Parameter        | Description                                                                       | Data Type | 
|------------------|-----------------------------------------------------------------------------------|-----------|
| httpStatus       | Indicates the Registration proccess status.                                       | String    | 
| message          | Message sent by the backend server to indicate the status of registration process | String    | 
| data             | Represents the data fetched                                                       | Object    | 
| totalLoadable    | Count of total loadable drones                                                    | Integer   | 
| droneModelViews  | Drone details which are loadable                                                  | Object    | 
| serialNumber     | Drone Serial number                                                               | String    | 
| model            | Drone model details                                                               | Object    | 
| name             | model name                                                                        | String    | 
| description      | Model description                                                                 | String    | 
| capacity         | Max capacity of drone                                                             | Double    | 
| state            | State details of a drone                                                          | Object    | 
| name             | State name of a drone                                                             | String    | 
| registrationDate | Date in which drone was registered                                                | String    | 

Response Body

### Successful Response
```json
{
   "httpStatus": "OK",
   "message": "Successfully fetched loadable drones.",
   "data": {
      "totalLoadable": 2,
      "droneModelViews": [
         {
            "serialNumber": "3",
            "model": {
               "name": "HEAVY_WEIGHT",
               "description": "Heavy Weight",
               "capacity": 500
            },
            "state": {
               "name": "IDLE"
            },
            "registrationDate": "13-05-2023 06:58:32 AM"
         },
         {
            "serialNumber": "2",
            "model": {
               "name": "HEAVY_WEIGHT",
               "description": "Heavy Weight",
               "capacity": 500
            },
            "state": {
               "name": "LOADING"
            },
            "registrationDate": "13-05-2023 06:55:57 AM"
         }
      ]
   }
}
```
5. ### Check battery level of drone

   Api Descriptions: <br>


   | path        | dispatch/drone/check/batteryLevel |
   |-------------|-------------------------| 
   | Http Method | POST                    | 
   | Consumes    | application/json        |
   | Produces    | application/json        |

   Request: <br>

   | Parameter       | Description                                               | Data Type | Mandatory | Validation                                               |
   |-----------------|-----------------------------------------------------------|-----------|-----------|----------------------------------------------------------|
   | serialNumber    | Drone Serial Number which is used in registration process | String    | YES       |                                                          | 

Request Body

```json
   {
   "serialNumber": "DRONE_1"
  }
```

Response:

| Parameter       | Description                                                                       | Data Type | 
|-----------------|-----------------------------------------------------------------------------------|-----------|
| httpStatus      | Indicates the Registration proccess status.                                       | String    | 
| message         | Message sent by the backend server to indicate the status of registration process | String    | 
| data            | Represents the data fetched                                                       | Object    | 
| serialNumber            | Drone Serial number                                                               | String    | 
| batteryLevel          | battery level of drone                                                            | Integer   | 

Response Body

### Successful Response
```json
{
   "httpStatus": "OK",
   "message": "Successfully fetched drone battery level.",
   "data": {
      "serialNumber": "DRONE_1",
      "batteryLevel": 100
   }
}
```

### Invalid Input Error Response
```json
{
   "httpStatus": "BAD_REQUEST",
   "message": "Validation error",
   "timestamp": "13-05-2023 12:44:36 PM",
   "apiErrors": [
      {
         "field": "serialNumber",
         "rejectedValue": "",
         "message": "Drone serial number is required."
      }
   ]
}
```


### Validation Response
```json
{
   "httpStatus": "CONFLICT",
   "message": "Drone with given serial number not registered.",
   "timestamp": "13-05-2023 12:50:02 PM"
}
```
