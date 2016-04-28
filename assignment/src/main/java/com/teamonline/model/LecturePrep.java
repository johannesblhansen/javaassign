package com.teamonline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class LecturePrep {
    
	@Id
    @GeneratedValue
    private int id;
	
	@NotNull
    private String title;

    @NotNull
    private String content;
}
