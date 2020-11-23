package com.engagement.aspect;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.engagement.controller.AdminController;
import com.engagement.service.AdminService;





@ExtendWith(MockitoExtension.class)
class LoggingAspectTest {

	@Mock
	private AdminService as;
	
	@InjectMocks	
	private AdminController ac;
	
	private LoggingAspect la;

	@Test
	void testSpringBeanPointcut() {
		la = new LoggingAspect();
		AspectJProxyFactory factory = new AspectJProxyFactory(ac);
		factory.addAspect(la);
		
		AdminController proxyAc = factory.getProxy();
		Mockito.when(proxyAc.findAll()).thenReturn(null);
		assertNull(proxyAc.findAll());
	}
	
	@Test
	void testException() {
		la = new LoggingAspect();
		AspectJProxyFactory factory = new AspectJProxyFactory(ac);
		factory.addAspect(la);
		
		AdminController proxyAc = factory.getProxy();
		
		Mockito.when(proxyAc.delete(1)).thenThrow(IllegalArgumentException.class);
		
		assertThrows(IllegalArgumentException.class, () -> {
	        proxyAc.delete(1);
	    });
		
	}

}
