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
@MapperScan(basePackages= {"com.mapper1"},sqlSessionFactoryRef="sqlSessionFactory1")
public class DataSouce1SqlSession {

	@Autowired
	@Qualifier("Datasource1")
	private DataSource Datasource1;
	
	@Bean
	public SqlSessionTemplate getSqlSessionTemplate () throws Exception {
		return new SqlSessionTemplate(getSqlSessionFactory());
	}

	
	@Bean(name="sqlSessionFactory1")
	public SqlSessionFactory  getSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(Datasource1);
		return sqlSessionFactoryBean.getObject();
	}
	
	
	
}
