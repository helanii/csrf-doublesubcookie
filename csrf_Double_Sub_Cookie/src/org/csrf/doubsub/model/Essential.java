package org.csrf.doubsub.model;

import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class Essential {

	//create a cookie which contains the sessionID of a particular session up on session creation
	public static final Function<HttpSession, Cookie> COOKIE_WITH_SESSION_ID = session -> new Cookie("session_ID", session.getId());

	//create a cookie which contains the csrf token upon csrf token generation
	  public static final Function<String, Cookie> COOKIE_WITH_CSRF_ID = csrfToken -> new Cookie("CSRF_TOKEN", csrfToken);
}
