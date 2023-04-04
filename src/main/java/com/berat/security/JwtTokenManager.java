package com.berat.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.berat.exception.ErrorType;
import com.berat.exception.PigeonManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {

    @Value("${security.secretKey}")
    private String secretKey;
    @Value("${security.issuer}")
    private String issuer;

    public Optional<String> createToken(Long id){
        String token=null;
        Long exDate =1000L*60*60;

        try {
            token = JWT.create().withAudience()
                    .withClaim("id",id)
                    .withClaim("lastjoin",System.currentTimeMillis())
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);

        }catch (Exception exception){
            return Optional.empty();
        }
    }

    public Boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null)
                return false;
        }catch (Exception exception){
            throw new PigeonManagerException(ErrorType.NOT_DECODED);
        }
        return true;
    }

    public Optional<Long> getByIdFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT==null)
                throw new PigeonManagerException(ErrorType.INVALID_TOKEN);
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            throw new PigeonManagerException(ErrorType.NOT_DECODED);
        }
    }

}
