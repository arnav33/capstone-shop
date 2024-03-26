package com.capstone.usermanagementservice.config;

import com.capstone.usermanagementservice.service.CustomUserDetailsService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static com.capstone.usermanagementservice.constant.Constants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customUserDetailsService) {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests($ -> $
                        .requestMatchers(HttpMethod.GET, WHITELISTED_GET_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, WHITELISTED_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.PUT, WHITELISTED_PUT_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .httpBasic().and().oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }


    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }
}

//class MyDecoder implements JwtDecoder {
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        KeyPairGenerator generator = null;
//        try {
//            generator = KeyPairGenerator.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        generator.initialize(2048);
//        RSAPublicKey rsaPublicKey = (RSAPublicKey) generator.generateKeyPair().getPublic();
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
//        Jwt jwt = jwtDecoder.decode(token);
//
//        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
//        SecretKey key = alg.key().build();
//
//        Jwts.parser().verifyWith()
//        return jwt;
//    }
//
//    public Jwt decodeJwt(String token) {
//        try {
//            var parsedJwt = JWTParser.parse(token);
//            Map<String, Object> headers = new LinkedHashMap<>(parsedJwt.getHeader().toJSONObject());
//            Map<String, Object> claims = new LinkedHashMap<>(parsedJwt.getJWTClaimsSet().getClaims());
//            if (claims.get(JwtClaimNames.IAT) instanceof Date) {
//                LocalDate calendarDate = Instant.ofEpochMilli(((Date) claims.get(JwtClaimNames.IAT)).getTime())
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate();
//                ZonedDateTime zdt = calendarDate.atStartOfDay(ZoneId.of("Europe/Paris"));
//                Instant iat = zdt.toInstant();
////                var iat = DateUtils.toInstant((Date) claims.get(JwtClaimNames.IAT));
//                claims.put(JwtClaimNames.IAT, iat);
//            }
//            if (claims.get(JwtClaimNames.EXP) instanceof Date) {
//                LocalDate calendarDate = Instant.ofEpochMilli(((Date) claims.get(JwtClaimNames.EXP)).getTime())
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate();
//                ZonedDateTime zdt = calendarDate.atStartOfDay(ZoneId.of("Europe/Paris"));
//                Instant exp = zdt.toInstant();
////                var exp = DateUtils.toInstant((Date) claims.get(JwtClaimNames.EXP));
//                claims.put(JwtClaimNames.EXP, exp);
//            }
//            return Jwt.withTokenValue(parsedJwt.getParsedString())
//                    .headers(h -> h.putAll(headers))
//                    .claims(c -> c.putAll(claims))
//                    .build();
//        } catch (ParseException e) {
//            throw new JwtException("Provided token is not valid");
//        } catch (Exception ex) {
//            throw new JwtException("Error during parse token");
//        }
//    }
//}