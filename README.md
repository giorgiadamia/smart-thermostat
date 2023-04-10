Smart Thermostat management system that allows users to manage their thermostats, as well as viewing temperature information for each device. To generate temperature data, you need to develop a console-based simulator that generates random temperature values. If the temperature exceeds the configured degree value, the device must be marked as critical.

To implement the system, you need to create a Spring Boot project with the necessary dependencies, including Spring Security, and Spring Data JPA. Using REST API endpoints, you should implement device management functionality that allows users to create and delete thermostats and view temperature information per device. The temperature data should be stored in a database along with the thermostat devices.

To secure the API, you should use Spring Security, which allows only authenticated users to perform CRUD operations on the thermostat devices.

In summary, your implementation should meet the following requirements:
1. Create a Spring Boot project with the required dependencies.
2. Implement device management functionality using REST API endpoints, including the ability to create, update and delete thermostats, and view temperature information per device.
3. Implement a simple simulator on a console application that will throw randomly generated temperature values to the server.
4. Secure the API using Spring Security, and allow only authenticated users to perform CRUD operations on the thermostat devices.