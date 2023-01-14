package com.turkcell.elearner.ws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseModel {

	private String courseName;
	
	private double price;
	
	private String description;
}
