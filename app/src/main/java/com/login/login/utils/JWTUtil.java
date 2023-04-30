package com.login.login.utils;
import com.login.login.entities.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;


@Component
public class JWTUtil {
    // @Value("${security.jwt.secret}")
    public static String key = "ghk45jgherogho834go3h4g";

    // @Value("${security.jwt.ttlMillis}")
    private static long ttlMillis = 604800000;


    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public static String generateToken(User user) {
           // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(Integer.toString(user.getId())).setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key)).parseClaimsJws(token).getBody();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
}

}
