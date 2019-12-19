# alipay-spring-boot-starter

## pom
```
 <!-- alipay-spring-boot-starter -->
<dependency>
    <groupId>com.zql</groupId>
    <artifactId>alioss-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

## application.yml
```$xslt
alioss:
  # yourAccessKeyId
  access-key-id: "xxxxxxxxxx"
  # yourAccessKeySecret
  access-key-secret: "xxxxxxxxxxx"
  # Endpoint以杭州为例，其它Region请按实际情况填写。
  endpoint: "http://oss-cn-hangzhou.aliyuncs.com"
```

## 使用方式
OSSClient为官方SDK提供，参考[官方文档](https://help.aliyun.com/document_detail/32011.html?spm=a2c4g.11186623.6.766.6f516328sqEQER). 

```
    @Autowired
    private OSSClient ossClient;

```
