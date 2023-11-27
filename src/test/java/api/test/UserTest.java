package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5,20));
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		System.out.println("==========Running TestPOST User=====================");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(),200 );
		
	}
	
	@Test(priority = 2)
	public void getUserByName() {
		System.out.println("==========Running getUserByName Test ================");
		Response response = UserEndPoints.readeUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
	@Test(priority = 3)
	public void updateUserByName() {
		System.out.println("=========Running UpdateUserByName ====================");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		System.out.println("========Response After Update ==========================");
		Response responseAfterUpdate = UserEndPoints.readeUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
	}
	
	@Test(priority = 4)
	public void deleteUserByName() {
		System.out.println("========Deleting User By Name ============================");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	

}
