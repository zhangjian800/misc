package com.dolphin.webapp.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.dolphin.common.log.DolpiLogger;

public class AccessServlet extends HttpServlet {
	
	DolpiLogger logger = new DolpiLogger(AccessServlet.class.getName());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();
		logger.error("Input stream is null?"+(is==null));
		if(is!=null){
			byte[] bytes = IOUtils.toByteArray(is);
			logger.error("Request is + "+ new String(bytes, "UTF-8"));
			logger.error("Request for base64 parse: + "+ new String(Base64.decodeBase64(bytes), "UTF-8"));
		}
	}
}
