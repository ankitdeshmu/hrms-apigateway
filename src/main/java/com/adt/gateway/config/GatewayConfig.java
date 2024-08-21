package com.adt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adt.gateway.routers.AuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Value("${hrms.service.url}")
	private String hrmsUrl;

	@Value("${auth.service.url}")
	private String authUrl;

	@Value("${payroll.service.url}")
	private String payrollUrl;

	@Value("${expense.service.url}")
	private String expenseUrl;

	@Value("${server.servlet.context-path}")
	private String gatewayContext;
	
	@Value("${utility.service.url}")
	private String utilityUrl;

	@Autowired
	AuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("auth",
						r -> r.path(gatewayContext + "/api/**").filters(f -> f.rewritePath(gatewayContext, ""))
								.uri(authUrl))

				.route("hrms",
						r -> r.path(gatewayContext + "/hrms/**").filters(f -> f.rewritePath(gatewayContext, ""))
								.uri(hrmsUrl))

				.route("payroll",
						r -> r.path(gatewayContext + "/payroll/**").filters(f -> f.rewritePath(gatewayContext, ""))
								.uri(payrollUrl))

				.route("expensemanagement",
						r -> r.path(gatewayContext + "/expensemanagement/**")
								.filters(f -> f.rewritePath(gatewayContext, "")).uri(expenseUrl))
				
				.route("utility",
						r -> r.path(gatewayContext + "/utility/**")
								.filters(f -> f.rewritePath(gatewayContext, "")).uri(utilityUrl))

				.build();
	}

}
