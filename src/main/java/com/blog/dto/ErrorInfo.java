package com.blog.dto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInfo {
	
private int error_code;
private boolean success;
private String error_message;
private LocalDateTime error_time;

}
