package org.woodwhales.xss.filter;

import java.io.IOException;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Primary
@Component
public class XssStringJsonDeSerializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		return StringEscapeUtils.escapeHtml4(jsonParser.getText());
	}

}
