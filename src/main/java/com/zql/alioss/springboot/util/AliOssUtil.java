package com.zql.alioss.springboot.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.19
 */
public class AliOssUtil {


    public static OSS createOssClient(ApplicationContext applicationContext, String configName) {

        Environment environment = applicationContext.getEnvironment();

        if (environment instanceof ConfigurableEnvironment) {
            String prefix = "alioss";
            MutablePropertySources mutablePropertySources = ((ConfigurableEnvironment) environment).getPropertySources();
            Map<String, String> propertiesMap = getPropertiesMap(mutablePropertySources, prefix);
            String enable = propertiesMap.get(prefix + "." + configName + ".enable");
            String endpoint = propertiesMap.get(prefix + "." + configName + ".endpoint");
            String accessKeyId = propertiesMap.get(prefix + "." + configName + ".access-key-id");
            String accessKeySecret = propertiesMap.get(prefix + "." + configName + ".access-key-secret");

            if (enable == null || enable.equals("false")) {
                throw new RuntimeException("alioss properties error , enable is null or false");
            }

            if (endpoint == null || endpoint.equals("")) {
                throw new RuntimeException("alioss properties error , endpoint is blank");
            }

            if (accessKeyId == null || accessKeyId.equals("")) {
                throw new RuntimeException("alioss properties error , access-key-id is blank");
            }

            if (accessKeySecret == null || accessKeySecret.equals("")) {
                throw new RuntimeException("alioss properties error , access-key-secret is blank");
            }

            // 创建ClientConfiguration实例，按照您的需要修改默认参数。
            ClientBuilderConfiguration conf = new ClientBuilderConfiguration();

            // 开启支持CNAME。CNAME是指将自定义域名绑定到存储空间上。
            conf.setSupportCname(true);

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);

            return ossClient;
        }

        throw new RuntimeException("environment isnot instanceof ConfigurableEnvironment");
    }


    private static Map<String, String> getPropertiesMap(PropertySources propertySources, String prefix) {

        Map<String, String> result = new HashMap<String, String>();

        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource propertySource = iterator.next();
            if (propertySource.getName().startsWith("applicationConfig")) {
                Map sourceMap = (Map) propertySource.getSource();
                if (prefix == null || prefix.equals("")) {
                    result.putAll(sourceMap);
                } else {
                    Set<Map.Entry> entrySet = sourceMap.entrySet();
                    Iterator<Map.Entry> entryIterator = entrySet.iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry item = entryIterator.next();
                        String key = item.getKey().toString();
                        String value = item.getValue().toString();
                        if (key.startsWith(prefix)) {
                            result.put(key, value);
                        }
                    }
                }
            }
        }

        return result;
    }
}
