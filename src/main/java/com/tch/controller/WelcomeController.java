/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tch.controller;

import java.util.Date;
import java.util.Map;

import com.tch.dao.CustomerRepository;
import com.tch.domain.document.Customer;
import com.tch.service.Customerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private Customerservice customerservice;

	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

	@RequestMapping("/elk")
	public void saveCustomers() {
		this.repository.deleteAll();
		System.out.println("aaaaa");
		this.repository.save(new Customer("Alice", "Smith"));
		this.repository.save(new Customer("Bob", "Smith"));
		customerservice.saveOrUpdate();
	}

//	@GetMapping("/")
//	public String creatIndex(Map<String, Object> model) {
//		elasticsearchTemplate.getClient();
//		return "welcome";
//	}



}
