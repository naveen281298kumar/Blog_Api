package com.blog.constants;



public class ApplicationConstants {
	
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "id";
	public static final String SORT_DIR	= "asc";
	public static final String EMAIL_REGEX = "^[A-Za-z]+[.!A-Za-z0-9]+@[A-Za-z]+[.]{1}[A-Za-z]+$";
	public static final String INVALID_EMAIL_FORMAT_MESSAGE = "Email you have entered does not match the rquired format, please enter a valid email";
	public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*[\\d])(?=.*[!@#$%^&*()])[\\w!@#$%^&*()]{8,24}$";
	public static final String INVALID_PASSWORD_FORMAT_MESSAGE = "please enter a valid password, there should be atleast one special character, atleast one upper and one lower case character and one digit";
	public static final String IMAGE_FORMAT_REGEX = "\\.(png|apng|avif|gif|jpg|jpeg|jfif|pjpeg|pjp|svg|webp)";
	public static final String INVALID_IMAGE_FORMAT_MESSAGE = "Please enter a valid image format (use the following extensiions only {png|apng|avif|gif|jpg|jpeg|jfif|pjpeg|pjp|svg|webp})";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final int NORMAL_USER = 502;
	public static final int ADMIN_USER = 501;

}
