package com.xinma.portal.util.ip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 聚合数据IP定位响应结果
 * 
 * @author Alauda
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JuheIPLocationResponse {

	private String resultcode; // 200正确

	private String reason;

	private JuheIPLocationResult result;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public JuheIPLocationResult getResult() {
		return result;
	}

	public void setResult(JuheIPLocationResult result) {
		this.result = result;
	}

	public boolean validate() {
		if (resultcode.equals("200")) {
			return true;
		} else {
			return false;
		}
	}
}
