package com.sync.dao;

import com.sync.models.Users;

public interface UserBo {

	public Users getLogin(String login_id,String password);
	public int register(Users usr);

}
