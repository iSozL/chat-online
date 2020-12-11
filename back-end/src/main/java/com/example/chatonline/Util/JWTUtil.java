package com.example.chatonline.Util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
//jwt含有三部分：头部（header）、载荷（payload）、签证（signature）
/*
*(1)头部一般有两部分信息：声明类型、声明加密的算法（通常使用HMAC SHA256）
*(2)载荷该部分一般存放一些有效的信息。jwt的标准定义包含五个字段：
*    -iss：该JWT的签发者
    - sub: 该JWT所面向的用户
    - aud: 接收该JWT的一方
    - exp(expires): 什么时候过期，这里是一个Unix时间戳
    - iat(issued at): 在什么时候签发的
* (3)签证（signature） JWT最后一个部分。该部分是使用了HS256加密后的数据；包含三个部分：
* */

@Component("jwtutil")
public class JWTUtil {

    private final Logger logger = Logger.getLogger(JWTUtil.class.toString());

    @Value("${jwtSecret}")
    private String SECRET;

    @Value("${jwtIssuer}")
    private String JWT_ISSUER;

    //获取秘钥
    private  Key getKeyInstance() {
//        return MacProvider.generateKey();
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }
    public  String creatJwtToken(String userID,String password){
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        final long nowMillis = System.currentTimeMillis();
        //过期时间5分钟
        final long ttlMillis = 5 * 60 * 100000;
        final long expMillis = nowMillis + ttlMillis;

        final Date now = new Date(nowMillis);
        final Date exp = new Date(expMillis);

        //Create the Signature SecretKey
        final Key signingKey = getKeyInstance();

        final Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        Map<String,Object> claims = new HashMap<String,Object>();//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        claims.put("uid", userID);
        claims.put("password", password);
        //add JWT Parameters
        final JwtBuilder builder = Jwts.builder()
                .setHeaderParams(headerMap)
                .setClaims(claims)      //自定义信息
                .setIssuedAt(now)       //发送时间
                .setExpiration(exp)    //到期时间
                .setIssuer(JWT_ISSUER) //token的组织者
                .setSubject(userID)   //token的拥有者
                .signWith(signatureAlgorithm, signingKey);

//        logger.info("JWT[" + builder.compact()+ "]");
        return builder.compact();
    }



    public Map<String, Object> verifyJavaWebToken(String token) {
        try {

            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody();
            return jwtClaims;
        } catch (Exception e) {
            logger.info("Parse JWT errror " + e.getMessage());
            return null;
        }
    }



    public Claims parseJWTToken(String token)
    {
        Claims claims = null;
        try
        {
            claims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException e)
        {
            logger.info("Parse JWT errror " + e.getMessage());
            return null;
        }
        return claims;
    }




}











