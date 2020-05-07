package com.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages= {"com.mapper2"},sqlSessionFactoryRef="sqlSessionFactory2")
public class DataSouce2SqlSession {
	@Autowired
	@Qualifier(value="Datasource2")
	private DataSource Datasource2;
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate () throws Exception {
		return new SqlSessionTemplate(getSqlSessionFactory());
	}
	
	
	@Bean(name="sqlSessionFactory2")
	public SqlSessionFactory  getSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(Datasource2);
		return sqlSessionFactoryBean.getObject();
	}
	
}
