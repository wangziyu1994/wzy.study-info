/*
package com.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.google.gson.JsonObject;

import antlr.collections.List;

public class Test1 {

	@Test
	public void test() {
		Map map1=new HashMap();
		Map map2=new HashMap();
		Map map3=new HashMap();
		map1.put(1, "a");
		map2.put(2, "b");
		map3.put(3, "c");
		ArrayList li= new ArrayList();
		li.add(map1);
		li.add(map2);
		li.add(map3);
		JSONObject js1=new JSONObject();
		JSONObject js2=new JSONObject();
		JSONObject js3=new JSONObject();
			try {
				js1.put("1", map1.get(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				js2.put("2", map1.get(2));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				js3.put("3", map1.get(3));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(js1);
		
	}
@Test
	public void test2() throws IOException {
		File f=new File("D:\\info\\text.csv");
		
		if(!f.exists()) {
			f.createNewFile();
		}
		Writer writer = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(f,true), "UTF-8"));
		
		for(int i=0;i<=10;i++) {
			writer.write("1,");
			writer.write("2,");
			writer.write("3,");
			writer.write("4,");
			writer.write("5");
			writer.write("\n");
		}
		writer.flush();
		writer.close();
}

@Test
public void test3() throws IOException {
	//String s="\\w+_\\w+_\\d+.csv";
	//String q="affds_ff_34234.csv";
	try {
		System.out.println("1");
		System.out.println(1/0+"2");
		System.out.println("3");
	}
	catch(Exception e){
		System.out.println("异常处理");
	}
	System.out.println("4");
 //boolean f=q.matches(s);
// System.out.println(f);
}

}
*/
