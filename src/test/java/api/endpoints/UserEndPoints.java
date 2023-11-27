package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;

import io.restassured.response.*;
 
 
/*

* This is for the CRUD Operations on the User Module

*/
 
public class UserEndPoints {

	public static Response createUser(User payload) {

		Response response = given()

		.contentType(ContentType.JSON)

		.accept(ContentType.JSON)

		.body(payload)

		.when()

		.post(Routes.post_url);

		return response;

	}

	public static Response readeUser(String userName) {

		Response response = given()

		.pathParam("username", userName)

		.when()

		.get(Routes.get_url);

		return response;

	}


	public static Response updateUser(String username,User payload) {

		Response response = given()

		.contentType(ContentType.JSON)

		.accept(ContentType.JSON)

		.pathParam("username", username)

		.body(payload)

		.when()

		.put(Routes.put_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		Response response = given()

		.pathParam("username", userName)

		.when()

		.delete(Routes.delete_url);

		return response;

	}

}

