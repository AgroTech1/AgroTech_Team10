package com.jsp.Agro.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	private String Message;
	private int status;
	private T data;

}



