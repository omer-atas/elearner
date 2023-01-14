package com.turkcell.elearner.ws.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.elearner.application.features.courses.commands.create.CreateCourseCommand;
import com.turkcell.elearner.ws.models.CreateCourseModel;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CommandGateway commandGateway;

	@Autowired
	public CourseController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping()
	public void CreateCourse(@RequestBody CreateCourseModel createCourseModel) {

		CreateCourseCommand createCourseCommand = CreateCourseCommand.builder()
				.courseName(createCourseModel.getCourseName()).
				price(createCourseModel.getPrice())
				.description(createCourseModel.getDescription()).
				build();

		createCourseCommand.setCourseId(UUID.randomUUID().toString());

		this.commandGateway.sendAndWait(createCourseCommand);
	}

}
