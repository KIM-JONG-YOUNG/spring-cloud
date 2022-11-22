package edu.jong.spring.gateway.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import edu.jong.spring.common.constants.HeaderNames;
import edu.jong.spring.login.client.LoginOperations;
import edu.jong.spring.login.request.AccessCheckParam;
import edu.jong.spring.login.request.AccessCheckParam.AccessCheckParamBuilder;
import edu.jong.spring.role.enums.APIMethod;
import lombok.Data;

@Component
public class TokenCheckFilter extends AbstractGatewayFilterFactory<TokenCheckFilter.Config>{

	public TokenCheckFilter(
//			@Autowired LoginOperations loginOperations
			) {
		super(Config.class);
//		this.loginOperations = loginOperations;
	}

//	private final LoginOperations loginOperations;
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
        	
            return chain.filter(exchange);

//            System.out.println(config.getPermitAll());
//            if (!request.getHeaders().containsKey(HeaderNames.AUTH_TOKEN)) {
//            	response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            	return response.setComplete();
//            }
//            
//            List<String> accessToken = request.getHeaders().getValuesAsList(HeaderNames.AUTH_TOKEN);
//            HttpMethod method = request.getMethod();
//            String url = request.getURI().toString();
//            
//            System.out.println(url);
//            System.out.println(accessToken);
//            
//            if (method == HttpMethod.HEAD || method == HttpMethod.OPTIONS || method == HttpMethod.TRACE) {
//                return chain.filter(exchange);
//            } else {
//            	AccessCheckParamBuilder paramBuilder = AccessCheckParam.builder();
//            	paramBuilder.checkURL(url);
//            	
//            	switch (method) {
//				case GET:
//					paramBuilder.checkMethod(APIMethod.GET);
//					break;
//				case POST:
//					paramBuilder.checkMethod(APIMethod.POST);
//					break;
//				case PUT:
//					paramBuilder.checkMethod(APIMethod.PUT);
//					break;
//				case DELETE:
//					paramBuilder.checkMethod(APIMethod.DELETE);
//					break;
//				default:
//					break;
//				}
//            	return chain.filter(exchange);
////                ResponseEntity<Boolean> loginCheckAPIResponse = loginOperations.check(accessToken.get(0), paramBuilder.build());
////                if (loginCheckAPIResponse.getStatusCode() != HttpStatus.OK) {
////                	response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
////                	return response.setComplete();
////                } else {
////                	if (loginCheckAPIResponse.getBody()) {
////                        return chain.filter(exchange);
////                	} else {
////                    	response.setStatusCode(HttpStatus.FORBIDDEN);
////                    	return response.setComplete();
////                	}
////                }
//            }
        });
	}
	
	@Data
	public static class Config {
		private List<String> permitAll;
	}

}
