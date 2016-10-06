package com.training.restservices;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * 
 * @author 447482
 *
 */
@Configuration
@EnableAutoConfiguration
public class CxfConfig {

	@Autowired
	private Service peopleRestService;

	@Autowired
	private UserServices userServices;

	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}

	/**
	 * 
	 * @return
	 */
	@Bean(destroyMethod = "destroy")
	@DependsOn("cxf")
	public Server jaxRsServer() {
		final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

		factory.setServiceBeanObjects(peopleRestService, userServices);
		factory.setBus(cxf());
		factory.setProvider(new JacksonJsonProvider());
		factory.setAddress("/");

		return factory.create();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean cxfServlet() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
}
