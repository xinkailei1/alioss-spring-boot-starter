package com.zql.alioss.springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.18
 */
@Data
@ConfigurationProperties("alioss")
public class AliOssProperties {

    /**
     * Endpoint以杭州为例，其它Region请按实际情况填写。
     */
    private String endpoint;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

}
