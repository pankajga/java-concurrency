package com.mylearning.executorservicedemo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.executorservicedemo.service.ExecService;

@RestController
public class ExecController {
	
	@Autowired
	ExecService execService;
	
	@GetMapping("/execute")
	public String ConcurrentController() {
		String response = null;
		try {
			response = execService.ConcurrentService();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("/executeMultiple")
	public String MultipleConcurrentController() {
		String response = null;
		try {
			response = execService.MultipleConcurrentService();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
