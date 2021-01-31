Implementing API testing using RestAssured + JUnit + Cucumber

I have implemented apis found in : https://gorest.co.in/

Guide:
Ensure that :
    1. You have maven installed in your machine and path configured.

Steps
1. Clone the project.
2. On the pom.xml file set the tools jar path as per your machines location of the tools.jar file. On latest JDK versions, you can find it on the JDK lib's folder.
3. Update maven dependencies to ensure all the required jar files on the pom.xml file have been downloaded.
4. Run the project either via cmd through  "mvn clean test" or by Running the testRunner class.
5. To view reports go to your projects folder/target/cucumber-html-reports

Sample Report :
![image](https://user-images.githubusercontent.com/28442760/106382374-41031300-63d0-11eb-9325-af8325884d4b.png)
