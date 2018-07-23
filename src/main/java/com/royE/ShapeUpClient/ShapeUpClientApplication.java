package com.royE.ShapeUpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ShapeUpClientApplication {

	public static void main(String[] args) {
		String url = "http://localhost:8081/rightTriangle";

		SpringApplication.run(ShapeUpClientApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();

		String requestJson;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		int counter = 0;
		while(true){

			requestJson = new String("{\"id\": " + (++counter) + " ," +
					"\"firstEdge\": " + (Math.random() * 100) + "," +
					"\"secondEdge\": " + (Math.random() * 100) + " }");
			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

			restTemplate.postForObject(url, entity, String.class);
			System.out.println("posted: " + requestJson);


			ResponseEntity responseEntity;
			responseEntity = restTemplate.getForEntity(url,String.class);
			System.err.println(responseEntity.getBody());

			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}
}
