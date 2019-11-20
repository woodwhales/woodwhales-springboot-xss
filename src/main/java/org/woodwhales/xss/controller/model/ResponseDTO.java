package org.woodwhales.xss.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

	private boolean success;
	
	private ReqBody response;
	
	private String errorMsg;
	
	public static ResponseDTO success(ReqBody reqBody) {
		return ResponseDTO.builder().success(true).response(reqBody).build();
	}
	
	public static ResponseDTO error(String errorMsg) {
		return ResponseDTO.builder().success(false).errorMsg(errorMsg).build();
	}
}
