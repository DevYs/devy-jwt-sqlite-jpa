package devy.jwt.sqlite.jpa.config;

import devy.jwt.sqlite.jpa.util.JwtCreator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jws = request.getHeader("Authorization");

//        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JwtCreator.SIGN_KEY).parseClaimsJws(jws);
//        } catch(Exception e) {
//            return false;
//        }


        return true;
    }
}
