package com.xinma.portal.util;

import java.util.Random;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * 对象池
 */
public class JerseyClientPool {
	private static Random random = new Random();
	private static Client[] clients = null;

	/**
	 * Jersey 创建池
	 * 
	 * @return
	 */
	public synchronized static void initPool() {
		if (clients != null) {
			return;
		}

		clients = new Client[5];

		for (int i = 0; i < clients.length; i++) {
			clients[i] = ClientBuilder.newClient();
		}
	}

	/**
	 * 获取对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Client getClient() {
		return clients[random.nextInt(clients.length)];
	}

	/**
	 * 销毁对象
	 * 
	 * @param client
	 */
	public synchronized static void destroy() {
		for (int i = 0; i < clients.length; i++) {
			if (clients[i] != null) {
				clients[i].close();
			}
		}
	}

}
