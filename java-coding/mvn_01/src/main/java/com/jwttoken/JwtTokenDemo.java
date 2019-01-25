package com.jwttoken;

import java.util.Map;

import com.auth0.jwt.interfaces.Claim;

public class JwtTokenDemo {

	public static void main(String[] args) {
		String token = JwtToken.createToken();
		System.out.println("token: " + token);

		Map<String, Claim> claims = JwtToken.verifyToken(token);
		for (Map.Entry<String, Claim> map : claims.entrySet()) {
			System.out.println(map.getKey() + " ----> " + map.getValue());
		}
		/**
		 * 验证token失效
		 */
		// String tokenExpire = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
		// +
		// ".eyJvcmciOiJ4aWFvbWkiLCJuYW1lIjoieGlhb3hpYW8iLCJleHAiOjE1NDYwNzkzMzYsImlhdCI6MTU0NjA3OTI3NiwiYWdlIjoiMjkifQ"
		// + ".t7FegBSSTkp66LRmuG882az2r-ZlM9uGnvDrWpzXr_o";
		//
		// Map<String, Claim> claimsExpire = JwtToken.verifyToken(tokenExpire);
	}

}
