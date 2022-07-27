package com.mylearning.executorservicedemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

@Service
public class ExecService {
	
	  public String ConcurrentService() throws InterruptedException, ExecutionException {
		  Callable<String> callable = () -> {
			  System.out.println("Thread is running");
			  return "From Callable";
		  };
		  
		  ExecutorService exec = Executors.newFixedThreadPool(5);
		  Future<String> future = exec.submit(callable);
		  String result = future.get();
		  System.out.println(result);
		  return result;
	  }
	  
	  public String MultipleConcurrentService() throws InterruptedException, ExecutionException {
		  String appendedString = "";
		  Callable<String> callable = () -> {
			  System.out.println("Thread is running");
			  return "From Callable";
		  };
		  ArrayList<Callable<String>> callableList = new ArrayList<>();
		  callableList.add(callable);
		  callableList.add(callable);
		  callableList.add(callable);
		  callableList.add(callable);
		  callableList.add(callable);
		  ExecutorService exec = Executors.newFixedThreadPool(3);
		  List<Future<String>> futureList = exec.invokeAll(callableList); //Debug and check console at this step
		  for(Future<String> future : futureList) {
			  String result = future.get();
			  appendedString += result;
			  System.out.println(result);
		  }
		  return appendedString;
	  }
	 
}
