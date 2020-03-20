package com.hechuan.waimai.action;


import com.hechuan.waimai.service.impl.ProductServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Action 基类
 */
@Controller
public class BaseAction {

	/* 日志 */
	protected final Log log = LogFactory.getLog(getClass());
	protected static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = this.getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}


}
