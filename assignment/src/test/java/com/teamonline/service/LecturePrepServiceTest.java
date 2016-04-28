package com.teamonline.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.teamonline.BaseSpringTest;
import com.teamonline.model.LecturePrep;

import static org.junit.Assert.*;

public class LecturePrepServiceTest extends BaseSpringTest {
	
	@Autowired
	private LecturePrepService lecturePrepService;
	
	@Test
	public void testGettingALectureWithId(){
		LecturePrep l = lecturePrepService.getLecturePrep(2);
		assertNotNull(l);
	}
}
