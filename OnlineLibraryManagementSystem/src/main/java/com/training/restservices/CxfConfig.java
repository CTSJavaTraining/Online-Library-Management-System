package com.training.restservices;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author 447482
 *
 */
@Configuration
@EnableAutoConfiguration
public class CxfConfig {

/*	@Autowired
	private Service peopleRestService;

	@Autowired
	private UserServices userServices;

	*//**
	 * 
	 * @return new SpringBus for apache CXF which is the central point for rest
	 *         service
	 *//*
	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}

	*//**
	 * 
	 * @return
	 *//*
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

	*//**
	 * 
	 * @return
	 *//*
	@Bean
	public ServletRegistrationBean cxfServlet() {
		final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}*/
}
