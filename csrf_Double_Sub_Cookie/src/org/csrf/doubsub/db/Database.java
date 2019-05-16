package org.csrf.doubsub.db;


public class Database {
	
	//user name and password hardcoded as "test"
	 private static final String USERNAME = "test";

	  private static final String PASSWORD = "test";
	  
   //validate user against the username and password in the system
	  public static boolean isValidUser(String username, String password)
	  {
	    return USERNAME.equalsIgnoreCase(username) && PASSWORD.equalsIgnoreCase(password);
	  }
}
