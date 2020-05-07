
package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.mapper1.HelloMapper;
import com.mapper2.HelloMapper1;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class HelloDaoImpl implements HelloDao{

	@Autowired
	private HelloMapper helloMapper;
	
	@Autowired
	private HelloMapper1 helloMapper1;
	
	
	private  List ls1=new ArrayList();
	private  List ls2 =new ArrayList();
	
	
	@Override
	public List selectHello() {
		if(ls1.size()==0) {
			System.out.println("查询数据库了");
			ls1=helloMapper.selectHello();
		}
		
		
		return ls1;
	}
	
	@Override
	public List selectHello1() {
		if(ls2.size()==0) {
			System.out.println("查询数据库了");
			ls2=helloMapper1.selectHello();
		}
		return 	ls2;
	}

	
	
}

