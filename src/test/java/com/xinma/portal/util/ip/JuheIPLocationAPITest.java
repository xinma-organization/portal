package com.xinma.portal.util.ip;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.portal.util.JerseyClientPool;

public class JuheIPLocationAPITest {

	@BeforeClass
	public void init() {
		JerseyClientPool.initPool();
	}

	@Test
	public void location() throws JsonProcessingException {
		JuheIPLocationResponse response = JuheIPLocationAPI.location("112.65.18.58");
		System.out.println(new ObjectMapper().writeValueAsString(response));
	}

	@AfterClass
	public void destroy() {
		JerseyClientPool.destroy();
	}
}
