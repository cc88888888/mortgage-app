package com.gc.api.rest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.gc.model.RestAPIExceptionInfo;
import com.gc.exception.HTTP400Exception;
import com.gc.exception.HTTP404Exception;

public class AbstractRestController implements ApplicationEventPublisherAware {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	protected ApplicationEventPublisher eventPublisher;
	protected static final String DEFAULT_PAGE_SIZE = "30";
	protected static final String DEFAULT_PAGE_NUM = "0";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HTTP400Exception.class)
	public @ResponseBody RestAPIExceptionInfo handleDataStoreException(HTTP400Exception ex, WebRequest request,
			HttpServletResponse response) {
		log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());
		return new RestAPIExceptionInfo(ex, "The Request did not have correct parameters / body etc. Please check");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(HTTP404Exception.class)
	public @ResponseBody RestAPIExceptionInfo handleResourceNotFoundException(HTTP404Exception ex, WebRequest request,
			HttpServletResponse response) {
		log.info("ResourceNotFoundException handler:" + ex.getMessage());
		return new RestAPIExceptionInfo(ex, "The Endpoint was not found.");
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	public static <T> T checkResourceFound(final T resource) {
		if (resource == null) {
			throw new HTTP404Exception("resource not found");
		}
		return resource;
	}
}
