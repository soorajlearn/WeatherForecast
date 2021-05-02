#WeatherForecast

#Tools

1. Built using Java with Selenium(Page Object Model) & Rest Assured 
2. TestNG is used as an execution framework & for reporting, and project is built in such a way that it is configurable to any other reporting tools 
3. Maven is the build tool & the project is ready for CI/CD integration 
4. Excel is the test data file which is handled using Apache POI
5. Project level data (constants) are configurable under config file(under resources folder)

#Execution

1. Classes under testcases package can be executed individually
2. Can be executed through testng.xml
3. Can be executed through Maven goals or using any CI/CD tools via Maven 
4. Can be executed in parallel and it is implemented using ThreadLocal concept 


