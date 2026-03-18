package com.ecom_app.micro_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import com.ecom_app.product_service.ProductServiceApplication;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
	, classes = ProductServiceApplication.class
)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static{
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProducts() {

		String reqeustBody = """
							{
			"id": "p1002",
			"name": "Laptop",
			"description": "High performance gaming laptop",
			"price": 75000
			}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(reqeustBody)
				.when()
				.post("api/products/create")
				.then()
				.statusCode(201)
				.body("id" , Matchers.notNullValue());
	}

}
