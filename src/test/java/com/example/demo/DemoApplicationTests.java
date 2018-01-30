package com.example.demo;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.demo.book.Book;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;


	@BeforeClass
	public static void beforeClass(){
		System.out.println("Hitting Before class");
	}

	@Before
	public void addAuth(){
		System.out.println("Hitting before");
		testRestTemplate = testRestTemplate.withBasicAuth("chida", "chida123");
	}

	@Test
	public void testHello() {
		ResponseEntity<String> responseEntity =  testRestTemplate.getForEntity("/hello",String.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Hi Spring boot...", responseEntity.getBody().toString());
	}

	@Test
	public void getBook() {


		ResponseEntity<Book> responseEntity =  testRestTemplate.getForEntity("/books/1", Book.class);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Book book = responseEntity.getBody();
		assertEquals(1, book.getUnid());
		//assertEquals("Hi Spring boot...", responseEntity.getBody().toString());
	}

	@After
	public void after(){
		System.out.println("Hitting after");
	}

	@AfterClass
	public static void afterClass(){
		System.out.println("Hitting After class");
	}

}
