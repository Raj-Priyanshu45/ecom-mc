package com.order_service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.order_service.order.Stubs.InventoryClientStubs;

import io.restassured.RestAssured;

@Testcontainers
@AutoConfigureWireMock(port=0)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

	@LocalServerPort									
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}

	@Test
	void createOrder(){

		String reqeustBody = """
				{
				"skuCode": "MACBOOK-AIR-M2-13",
				"price": 499.99,
				"quantity": 2
				}
				""";

		InventoryClientStubs.stubInventoryCall("MACBOOK-AIR-M2-13", 2);


		RestAssured.given()
				.contentType("application/json")
				.body(reqeustBody)
				.when()
				.post("api/order/place")
				.then()
				.statusCode(201);
				// .body("id" , Matchers.notNullValue())
				// .body("orderNumber" , Matchers.notNullValue());
	}
}
