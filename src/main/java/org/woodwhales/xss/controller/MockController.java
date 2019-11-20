package org.woodwhales.xss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woodwhales.xss.controller.model.ReqBody;
import org.woodwhales.xss.controller.model.ResponseDTO;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mock")
public class MockController {

	@PostMapping("/post/")
	public Object postMock(@RequestBody ReqBody reqBody) {
		log.debug("return {}", JSON.toJSONString(reqBody));
		return ResponseDTO.success(reqBody);
	}
	
	@GetMapping("/get")
	public Object getMock() {
		ReqBody reqBody = ReqBody.builder()
								.name("<script>alert('hackName from server')</script>")
								.description("<script>alert('hack script from server')</script>")
								.build();
		
		log.debug("return {}", JSON.toJSONString(reqBody));
		return ResponseDTO.success(reqBody);
	}
}
