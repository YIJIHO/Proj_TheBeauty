package com.kosa.theBeauty.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosa.theBeauty.user.domain.UserVO;

@Mapper
public interface UserDAO {

	UserVO findPassword(String userMail, int userRegistration);
}
