package org.csrf.doubsub.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.csrf.doubsub.db.Database;
import org.csrf.doubsub.model.Essential;





@WebServlet("/MainController")
public class MainController extends HttpServlet
{
  //create new hashmap to hold the tokens
  public static Map<String, String> csrfTokenStore = new HashMap<String, String>();
  private static final long serialVersionUID = 1L;

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

 
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {

    String username = request.getParameter("username");//get username
    String password = request.getParameter("password");//get password
    HttpSession session = request.getSession(true); //create a new session if session has expired/doesn't exist

    if (Database.isValidUser(username, password)) //if the username and password are valid
    {
      String csrfToken = generateCSRFToken(session.getId()); //generate a csrf token under the session
      csrfTokenStore.put(session.getId(), csrfToken); // adding to token store
      response.addCookie(Essential.COOKIE_WITH_SESSION_ID.apply(session)); //send cookie with session id in response body
      response.addCookie(Essential.COOKIE_WITH_CSRF_ID.apply(csrfToken));//send cookie with csrf token in response body

      session.removeAttribute("invalidCredentials");
      response.sendRedirect("./Home.jsp"); //redirect to Home.jsp page
    }
    else //if username and password are invalid
    {
      session.setAttribute("invalidCredentials", "retry");
      response.sendRedirect("./Login.jsp");//redirect to Login.jsp page
    }
  }

  private String generateCSRFToken(String strClearText) //generate a csrf token with a randomly generated string
  {
    return strClearText + "." + getRandomString();
  }

  private String getRandomString() //generate a random string 
  {
    UUID randomUuid = UUID.randomUUID();
    return randomUuid.toString();
  }
}
