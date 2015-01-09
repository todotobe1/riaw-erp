package com.riawworks.riaw.erp.context;

import com.riawworks.riaw.erp.model.bo.User;

public class UserContext {

	private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

	public static void set(User user) {
		threadLocal.set(user);
	}

	public static User get() {
		return threadLocal.get();
	}

}
