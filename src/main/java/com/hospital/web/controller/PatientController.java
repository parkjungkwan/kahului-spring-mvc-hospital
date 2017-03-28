package com.hospital.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospital.web.domain.PatientDTO;
import com.hospital.web.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	@Autowired PatientService service;
	@Autowired PatientDTO patient;
	@RequestMapping("/join")
	public String goJoin(){
		logger.info("PatientController - goJoin() {}", "ENTER");
		return "public:patient/registerForm";
	}
	@RequestMapping("/login")
	public String goLogin(){
		logger.info("PatientController - goLogin() {}", "ENTER");
		return "public:common/loginForm";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String goLogin(@RequestParam("id") String id,
			@RequestParam("password") String password,
			Model model) throws Exception{
		logger.info("PatientController - goLogin() {}", "POST");
		logger.info("PatientController - id, pw: {}", id+","+password);
		patient.setPatID(id);
		patient.setPatPass(password);
		int count=service.count();
		logger.info("DB 다녀온 갯수: {}", count);
		patient=service.login(patient);
		logger.info("DB 다녀온 결과: {}", patient.getPatID()+","+patient.getPatPass());
		model.addAttribute("name", "홍길동");
		return "patient:patient/containerDetail";
	}
	@RequestMapping("/doctor/{docID}")
	public String getDoctorInfo(@PathVariable String docID){
		logger.info("PatientController - getDoctorInfo() {}", "ENTER");
		logger.info("PatientController - docID={}", docID);
		return "patient:patient/doctorInfo";
	}
}