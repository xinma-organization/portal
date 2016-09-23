package com.xinma.portal.util.ip;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.portal.log.PortalCustomException;
import com.xinma.portal.log.error.IpLocationError;
import com.xinma.portal.util.JerseyClientPool;

/**
 * 聚合数据IP定位接口API实现类
 * 
 * @author Alauda
 *
 */
public class JuheIPLocationAPI {

	private static final String apiUrl = "http://apis.juhe.cn/ip/ip2addr";

	private static final String apiKey = "0af2223820eac389f8ca4cfe66609fd8";

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * 聚合ip location接口实现
	 * 
	 * @param ip
	 *            ip地址
	 * @return 响应数据
	 */
	public static JuheIPLocationResponse location(String ip) {

		try {
			Client client = JerseyClientPool.getClient();
			WebTarget target = client.target(apiUrl).queryParam("ip", ip).queryParam("key", apiKey);
			String responseStr = target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);

			return mapper.readValue(responseStr, JuheIPLocationResponse.class);
		} catch (Exception e) {
			throw new PortalCustomException(e, IpLocationError.JuheIpLocationException);
		}
	}
}
