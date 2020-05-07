package com.test;


public class SBAPPTEST {

	/*
	 * // 连接管理器 private static PoolingHttpClientConnectionManager pool; // 请求配置
	 * private static RequestConfig requestConfig; //設置URL private static final
	 * String httpurl="http://127.0.0.1/SpringBootTest/sayhello"; //设置请求
	 * 
	 * // HTTP内容类型。相当于form表单的形式，提交数据 public static final String
	 * CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
	 * 
	 * // HTTP内容类型。相当于form表单的形式，提交数据 public static final String
	 * CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
	 * 
	 * public static final String
	 * paramJson="{"+"\""+"uName"+"\":"+"\"wangziyu\","+"\"pwd"+"\":"+"\"123\"}"; //
	 * utf-8字符编码 public static final String CHARSET_UTF_8 = "utf-8";
	 * 
	 * static { try { //System.out.println("初始化HttpClientTest~~~开始");
	 * SSLContextBuilder builder = new SSLContextBuilder();
	 * builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
	 * SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	 * builder.build()); // 配置同时支持 HTTP 和 HTPPS Registry<ConnectionSocketFactory>
	 * socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>
	 * create().register( "http",
	 * PlainConnectionSocketFactory.getSocketFactory()).register( "https",
	 * sslsf).build(); // 初始化连接管理器 pool = new PoolingHttpClientConnectionManager(
	 * socketFactoryRegistry); // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
	 * pool.setMaxTotal(200); // 设置最大路由 pool.setDefaultMaxPerRoute(2); //
	 * 根据默认超时限制初始化requestConfig int socketTimeout = 10000; int connectTimeout =
	 * 10000; int connectionRequestTimeout = 10000; requestConfig =
	 * RequestConfig.custom().setConnectionRequestTimeout(
	 * connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
	 * connectTimeout).build();
	 * 
	 * //System.out.println("初始化HttpClientTest~~~结束"); } catch
	 * (NoSuchAlgorithmException e) { e.printStackTrace(); } catch
	 * (KeyStoreException e) { e.printStackTrace(); } catch (KeyManagementException
	 * e) { e.printStackTrace(); }
	 * 
	 * 
	 * // 设置请求超时时间 requestConfig =
	 * RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
	 * .setConnectionRequestTimeout(50000).build(); }
	 * 
	 * 
	 * @Test public void test1() { System.out.println(paramJson);
	 * 
	 * }
	 * 
	 * @Test public void test2() { System.out.println(PostJson(httpurl,paramJson));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //设置连接池 public static CloseableHttpClient gethttpclient() {
	 * CloseableHttpClient cc=HttpClients.custom().setConnectionManager(pool)
	 * .setDefaultRequestConfig(requestConfig).setRetryHandler(new
	 * DefaultHttpRequestRetryHandler(0, false)).build(); return cc; }
	 * 
	 * //初始化POST请求 public static String PostJson(String httpUrl, String paramsJson)
	 * { HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost try { // 设置参数 if
	 * (paramsJson != null && paramsJson.trim().length() > 0) { StringEntity
	 * stringEntity = new StringEntity(paramsJson, "UTF-8");
	 * stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
	 * httpPost.setEntity(stringEntity); } } catch (Exception e) {
	 * e.printStackTrace(); } return sendPost(httpPost); }
	 * 
	 * 
	 * 
	 * //发送POST请求
	 * 
	 * public static String sendPost(HttpPost httppost) { CloseableHttpClient
	 * client=null; CloseableHttpResponse response=null; HttpEntity entity=null;
	 * client=gethttpclient(); String responseContent=null;
	 * httppost.setConfig(requestConfig); try { response=client.execute(httppost);
	 * entity=response.getEntity(); Header[]
	 * headers=response.getHeaders(HttpHeaders.CONTENT_TYPE);
	 * 
	 * for(Header header:headers) { System.out.println(header.getName()); } //
	 * 得到响应类型
	 * System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType
	 * ());
	 * 
	 * //判断响应结果类型1 if(response.getStatusLine().getStatusCode()>=300) { throw new
	 * Exception( "HTTP Request is not success, Response code is " +
	 * response.getStatusLine().getStatusCode()); } //判断响应结果类型2 if (HttpStatus.SC_OK
	 * == response.getStatusLine().getStatusCode()) { responseContent =
	 * EntityUtils.toString(entity, CHARSET_UTF_8); EntityUtils.consume(entity); } }
	 * catch (Exception e) { e.printStackTrace(); } finally { try { // 释放资源 if
	 * (response != null) { response.close(); } } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return null;
	 * 
	 * }
	 */

}
