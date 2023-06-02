package nl.dsh.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import nl.dsh.api.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWT {
    @Value("${dsh.jjwt.secret}")
    private String secret;

    @Value("${dsh.jjwt.expiration}")
    private String expiration;

    private Key key;

    @PostConstruct
    public void setup() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getClaims(String token) {
        if("null".equals(token)) return null;
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String genToken(User usr) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("role", usr.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        long expTime = Long.parseLong(expiration);
        final Date createdDate = new Date();
        final Date expDate = new Date(createdDate.getTime() + expTime * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setAudience(usr.getEmail())
                .setIssuedAt(createdDate)
                .setExpiration(expDate)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !getClaims(token).getExpiration().before(new Date());
    }
}
