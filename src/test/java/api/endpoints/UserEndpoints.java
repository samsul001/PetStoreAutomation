package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Create UserEndPoints.java class 
// (It consists of Endpoint methods implementation and created to perform CRUD operations on the USER APIs)


public class UserEndpoints {
	
	public static Response createUser(User payload){
		Response res =
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return res;
	}
	
	public static Response readUser(String username){
		Response res =
		given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
		return res;
	}
	
	public static Response updateUser(String username, User payload){
		Response res =
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
			.pathParam("username", username)
		.when()
			.put(Routes.put_url);
		return res;
	}
	
	public static Response deleteUser(String username) {
		Response res =
		given()
			.pathParam("username", username)
		.when()
			.delete(Routes.delete_url);
		return res;
	}

}
