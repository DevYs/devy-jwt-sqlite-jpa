package devy.jwt.sqlite.jpa.util;

import devy.jwt.sqlite.jpa.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 생성 도구
 */
public class JwtCreator {

    /** 사인 키 */
    public static final String SIGN_KEY = "12hf8128ef870ergh8dfubhskjdfqweuhp";

    /**
     * jwt 생성
     * @param member 회원정보
     * @param passwordEncoder 사인 키의 단방향 암호화를 위한 인코더
     * @return 생성된 jwt
     */
    public static String create(Member member, PasswordEncoder passwordEncoder){

        member.setPassword(null);

        int afterDays = 30;
        long today = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long after = LocalDateTime.now().plusDays(afterDays).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Date tdy = new Date(today);
        Date exp = new Date(after);

        String jwt = Jwts.builder()
                .setSubject(member.getMemberEmail())
                .setExpiration(exp)
                .setNotBefore(tdy)
                .setIssuedAt(tdy)
                .setIssuer("devy")
                .setAudience(member.getMemberEmail())
                .claim("member", member)
                .claim("scope", "all")
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .compact();
        return jwt;
    }

}
