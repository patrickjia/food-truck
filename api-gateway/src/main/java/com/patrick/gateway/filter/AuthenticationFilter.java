package com.patrick.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * @author han
 */
@Component
@RefreshScope
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Value("${access_control_allow_origin.domain}")
    private String domain;


    /**
     * 设置必要的响应安全头
     * @param response
     */
    private void setSecurityResponseHeader(ServerHttpResponse response){
        HttpHeaders headers =  response.getHeaders();
        if(headers!=null) {
            headers.setAccessControlAllowOrigin(domain);
            headers.setCacheControl("no cache, no store");
            headers.add("X-Frame-Options", "Sameorigin");
            headers.add("X-content-type", "nosniff");
            headers.add("Content-Security-Policy", "default-src 'self'; https://legit1.com https://legit2.com; report-uri /reportingurl;");
            headers.add("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        }
    }





    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Long operatorId;
        MDC.put("trace_uuid", UUID.randomUUID().toString().replace("-",""));
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri= request.getURI().getPath();
        log.info("the request path is {}",uri);
        //设置响应安全头
        setSecurityResponseHeader(response);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
