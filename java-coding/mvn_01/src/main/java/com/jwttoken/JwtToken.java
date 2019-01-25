package com.jwttoken;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtToken {
	/**
	 * 公钥，放在服务器端，客户端不知道秘钥
	 */
	public static final String SECRET = "asdfsdsfa";

	/**
	 * 生成 token
	 * 
	 * @returntoken
	 */
	public static String createToken() {
		Date iatDate = new Date();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, 1);
		Date expiresDate = nowTime.getTime();
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token = JWT.create().withHeader(map).withClaim("name", "xiaoxiao").withClaim("age", "29")
				.withClaim("org", "xiaomi").withExpiresAt(expiresDate).withIssuedAt(iatDate)
				.sign(Algorithm.HMAC256(SECRET));
		return token;
	}

	public static Map<String, Claim> verifyToken(String token) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			throw new RuntimeException("token已失效，请重新登录.........");
		}
		return jwt.getClaims();
	}
}
