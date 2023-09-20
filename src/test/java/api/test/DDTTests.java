package api.test;



import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTTests {
	
	//Test objective
	//Going to create 5 new users using the the "Data" dataprovider method
	//Going to delete all those 5 users using the "getUserNames" dataprovider method
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id, String uname, String fname, String lname, String email, String password, String ph) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(uname);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(ph);
		
		Response res =UserEndpoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userNames) {
		Response res =UserEndpoints.deleteUser(userNames);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
