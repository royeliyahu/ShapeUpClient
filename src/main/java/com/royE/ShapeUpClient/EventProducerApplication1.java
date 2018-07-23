package com.royE.ShapeUpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class EventProducerApplication1 {

	private static ExecutorService exeutor;

	public static void main(String[] args) {

		SpringApplication.run(EventProducerApplication1.class, args);

		exeutor = Executors.newFixedThreadPool(10);

//		produce("first");
		produce("second");


//		String url = "http://localhost:8080/event";
//
//
//		RestTemplate restTemplate = new RestTemplate();
//
//		String requestJson;
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		int counter = 0;
//		while(true){
//
//			requestJson = "{\"event_type\": \"first\"," +
//					"\"data\": \"Roy\"," +
//					"\"timestamp\": " + LocalTime.now() + " }";
//
//			HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
//
//			restTemplate.postForObject(url, entity, String.class);
//			System.out.println("posted: " + requestJson);
//
//
//			ResponseEntity responseEntity;
//			responseEntity = restTemplate.getForEntity(url,String.class);
//			System.err.println(responseEntity.getBody());
//
//			try {
//				Thread.sleep(2500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}


	}


	public static void produce(String eventType) {


		exeutor.execute(new Runnable() {
							@Override
							public void run() {

							}
						});

		String url = "http://localhost:8080/event";


		RestTemplate restTemplate = new RestTemplate();

		String requestJson;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		int counter = 0;
		while(true){

			requestJson = "{\"event_type\": \"" + eventType + "\"," +
					"\"data\": \"Roy\"," +
					"\"timestamp\": " + LocalTime.now() + " }";

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
