package cn.zyk.interfacetosql.localEsTest;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author:KUN
 * @Data:2021/3/31 14:36
 * @Description: 测试连接本地ES数据库
 * @Version:1.0
 */
public class ESConnectTest {
    private static final Logger logger = LoggerFactory.getLogger(ESConnectTest.class);

    public static ResponseEntity<JSONObject> query(String url , String str) {
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject queryRoot=(JSONObject)JSONObject.parse(str);
        RestTemplate restTemplate=new RestTemplate();

        restTemplate.setRequestFactory(clientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        HttpEntity<JSONObject> entity = new HttpEntity<>(queryRoot, headers);
        return restTemplate.postForEntity(url, entity, JSONObject.class);
    }

    public static HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
            httpClientBuilder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();// 注册http和https请求
            // 开始设置连接池
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            poolingHttpClientConnectionManager.setMaxTotal(2700); // 最大连接数2700
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100); // 同路由并发数100
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)); // 重试次数
            HttpClient httpClient = httpClientBuilder.build();
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                    httpClient); // httpClient连接配置
            clientHttpRequestFactory.setConnectTimeout(20000); // 连接超时
            clientHttpRequestFactory.setReadTimeout(30000); // 数据读取超时时间
            clientHttpRequestFactory.setConnectionRequestTimeout(20000); // 连接不够用的等待时间
            return clientHttpRequestFactory;
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
        }
        return null;
    }
    @Test
    public void getUsingAccountingId() {
        try {
            String url = "http://127.0.0.1:9200/deletebyquerytest/hello/_search";
            String str = "{\"query\":{ \"bool\": { \"must\": [{ \"exists\":{\"field\": \"usingAccountingId\" }}]}}}";
            ResponseEntity<JSONObject> responseEntity = query(url ,str);
            JSONObject studenstResult=responseEntity.getBody();
            JSONArray jsonArray = studenstResult.getJSONObject("hits").getJSONArray("hits");
            if(jsonArray==null || jsonArray.size()==0){
                System.out.println("查询结果为空");
            }
            System.out.println(jsonArray.getJSONObject(0).getJSONObject("_source").getString("usingAccountingId"));
        } catch (Exception e) {
            logger.error("获取在读账期数出错!错误:{}",e);
        }
    }
}
