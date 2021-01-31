// package stepDefinition;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.Then;
// import io.cucumber.java.en.When;
// import io.restassured.RestAssured;
// import io.restassured.path.json.JsonPath;
// import io.restassured.response.Response;
// import io.restassured.specification.RequestSpecification;
// import org.json.JSONObject;
//
// import java.util.concurrent.ThreadLocalRandom;
//
// import org.junit.Assert;
// public class getUsers {
// private RequestSpecification request;
// private static Response response;
// private static String jsonString;
// private static int user_id;
//// public static String email;
// @Given("navigate and get all users data")
// public void GetUsers() {
// RestAssured.baseURI = "https://gorest.co.in/public-api/";
// request = RestAssured.given().log().all();
// request.header("Content-Type","Application/json").header("Authorization","Bearer
// 5f89042b1e8ca1dd80b8c777493604577fea4a09098162bb84d15e3554f2913d");
// response = request.get("/users");
// System.out.println("This is my response :: " +response.asString());
//// jsonString = response.asString();
// Assert.assertEquals(200,response.getStatusCode());
//
// }
// @When("user is added")
// public void AddUser() {
// int min = 1;
// int max = 1000;
// int rand = ThreadLocalRandom.current().nextInt(min, max + 1);
// email = "email"+rand+"@test.com";
// response = request.body("{\r\n" +
// " \"name\": \"Digambara Dhawan DC\",\r\n" +
// " \"email\": \""+email+"\",\r\n" +
// " \"gender\": \"Female\",\r\n" +
// " \"status\": \"Inactive\",\r\n" +
// " \"created_at\": \"2020-10-30T03:50:03.842+05:30\",\r\n" +
// " \"updated_at\": \"2020-10-30T03:50:03.842+05:30\"\r\n" +
// "}\r\n" +
// "").post("/users");
// System.out.println("here is the data" + response.asString());
// JsonPath eval = response.jsonPath();
// user_id = eval.get("data.id");
//
// System.out.println("My USER_ID IS" + user_id);
////
//
//
// }
// @Then("check if user is added")
// public void checkIfUserIsAdded() {
// System.out.println("Message :: Successfull.");
// JsonPath object = response.jsonPath();
// Assert.assertEquals(200,response.getStatusCode());
// Assert.assertEquals(email, object.get("data.email"));
// }
// @When("One created user is gotten")
// public void getOneeUser() {
// System.out.println("USER_IDDDD ISS : " + user_id);
// response = request.get("/users/"+user_id);
// }
// @Then("Validate that created user is gotten")
// public void checkIfOneUserIsRetrived() {
// System.out.println("RESPONSEEE" + response.asString());
// Assert.assertEquals(200,response.getStatusCode());
// JsonPath object = response.jsonPath();
// Assert.assertEquals(email,object.get("data.email"));
// }
// @When("user data is updated")
// public void updateUserData() {
// int min = 1;
// int max = 1000;
// int rand = ThreadLocalRandom.current().nextInt(min, max + 1);
// email = rand+"@test.com";
// response = request.body("{\r\n" +
// " \"name\": \"Digambara Dhawan DC\",\r\n" +
// " \"email\": \""+email+"\",\r\n" +
// " \"gender\": \"Female\",\r\n" +
// " \"status\": \"Inactive\",\r\n" +
// " \"created_at\": \"2020-10-30T03:50:03.842+05:30\",\r\n" +
// " \"updated_at\": \"2020-10-30T03:50:03.842+05:30\"\r\n" +
// "}\r\n" +
// "").patch("/users/"+user_id);
// }
// @Then("validate that the user data is updated")
// public void ValidateUserDataUpdate() {
// JsonPath object = response.jsonPath();
// Assert.assertEquals(200,response.getStatusCode());
// Assert.assertEquals(email,object.get("data.email"));
// }
// @When("user is deleted")
// public void user_is_deleted() {
// // Write code here that turns the phrase above into concrete actions
// throw new io.cucumber.java.PendingException();
// }
// @Then("check if the user is infact deleted")
// public void check_if_the_user_is_infact_deleted() {
// // Write code here that turns the phrase above into concrete actions
// throw new io.cucumber.java.PendingException();
// }
// }
