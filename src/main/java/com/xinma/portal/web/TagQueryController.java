package com.xinma.portal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinma.base.datastore.model.tag.TagBasicInfoEO;
import com.xinma.portal.common.PortalConstants;

/**
 ** URL中经常遇到路径中有点"."，而点是特殊字符，比如.html, .do等等， 所以Spring
 * MVC默认是把点后面的信息当作文件后缀，这时候我们就要修改这个默认值。
 * 
 * <bean class=
 * "org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping">
 * <property name="interceptors" ref="localeChangeInterceptor"/> <property name=
 * "useDefaultSuffixPattern" value="false" /> </bean>
 * 
 * 基于以上问题，标签编码时字符表中不包含"."字符
 */

@Controller
public class TagQueryController {

	@RequestMapping(value = "{code}", method = RequestMethod.GET)
	public String tagQuery(@PathVariable String code, HttpServletRequest request, Model model) {
		try {
			TagBasicInfoEO tagBasicInfo = (TagBasicInfoEO) request.getAttribute(PortalConstants.tagBasicInfoAttr);
			System.out.println(code);
			model.addAttribute("message", new ObjectMapper().writeValueAsString(tagBasicInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "query";
	}
}
