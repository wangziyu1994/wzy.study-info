package com.mapper1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.model.HelloUser;


public interface HelloMapper {

	
	@Select(value="select s_id,s_name from student")
	@Results({
		@Result(column="s_id",property="sId"),
		@Result(column="s_name",property="userName")
	})
	public List<HelloUser> selectHello();
}
