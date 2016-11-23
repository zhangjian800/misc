package com.dolphin.webapp.context;


public class AppContext {
	
	  private static ThreadLocal<SessionState> threadLocal4User = new ThreadLocal<SessionState>();
	  
	  /**
	   * get Current User from Application Context
	   * 
	   * @return AppUser
	   */
	  public static SessionState getCurrentSessionState() {
		  SessionState appUser = threadLocal4User.get();
	    return appUser;
	  }	  
	  /**
	   * set Current User to Application Context
	   * 
	   * @param appUser
	   */
	  public static void setCurrentSessionState(SessionState appUser) {
	    threadLocal4User.set(appUser);
	  }	  
}
