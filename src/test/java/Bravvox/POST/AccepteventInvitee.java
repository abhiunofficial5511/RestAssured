package Bravvox.POST;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AccepteventInvitee {

	
	@Test
	public void acceptinvitee() {

		String[] items = null;
		String token;

		RestAssured.baseURI = "https://qa.bravvox.com";

		RequestSpecification request = RestAssured.given();

		String payload = "{\r\n"
				+ "    \"username\": \"Mike\",\r\n"
				+ "    \"password\": \"Test123@\"\r\n"
				+ "}\r\n"
				+ "}";

		request.header("Content-Type","application/json");
		Response responseFormToken =   request.body(payload).post("/services/auth/v1/login");

		String jsont = responseFormToken.asString();
		responseFormToken.prettyPrint();
		String[] couple = jsont.split("\",\"refreshToken\"");
		String[] nexts = couple[0].split("token\":\"");
		token = nexts[1];


		// Accept invitee by admin      


		RestAssured.given()
		.header("Authorization",token)
		.when()
		.post("/services/event/v1/event/c9aj6ipcg4h0nn8g6vm0/attendee/c8gs04gvgnppvl8n4uag/accept")
		.then()
		.log().body();



	}

}










