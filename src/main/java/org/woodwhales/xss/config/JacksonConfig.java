package org.woodwhales.xss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.woodwhales.xss.filter.XssStringJsonDeSerializer;
import org.woodwhales.xss.filter.XssStringJsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig implements WebMvcConfigurer {
	
	@Autowired
	private XssStringJsonDeSerializer xssStringJsonDeSerializer;
	
	@Autowired
	private XssStringJsonSerializer xssStringJsonSerializer;
	
	@Bean
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        
        SimpleModule simpleModule = new SimpleModule(XssStringJsonSerializer.class.getSimpleName());
        simpleModule.addDeserializer(String.class, xssStringJsonDeSerializer)
        			.addSerializer(String.class, xssStringJsonSerializer);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}