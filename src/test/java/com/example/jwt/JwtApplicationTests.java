package com.example.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtApplicationTests {

	@Value("${custom.jwt.secretKey}")
	private String secretKetPlain;

	@Test
	@DisplayName("시크릿 키 존재 여부 체크")
	void test1() {
		assertThat(secretKetPlain).isNotNull();
	}

	@Test
	@DisplayName("secretKetPlain을 이용하여 암호화 알고리즘 SecretKey 객체 만들기")
	void test2() {
		String KeyBase64Encoded = Base64.getEncoder().encodeToString(secretKetPlain.getBytes());

		SecretKey secretKey = Keys.hmacShaKeyFor(KeyBase64Encoded.getBytes());
		assertThat(secretKey).isNotNull();
	}

}
