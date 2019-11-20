package org.woodwhales.xss.filter;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Primary
@Component
public class XssStringJsonSerializer extends JsonSerializer<String> {
	
    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(value)) {
            String encodedValue = StringEscapeUtils.escapeHtml4(value);
            jsonGenerator.writeString(encodedValue);
        }
    }
}