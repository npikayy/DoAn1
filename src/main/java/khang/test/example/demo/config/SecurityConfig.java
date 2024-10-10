package khang.test.example.demo.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE)
@EnableMethodSecurity
public class SecurityConfig {

//    @Autowired
//    CustomJwtDecoder customJwtDecoder;

    //static final String Signer_Key = "ADQbLUG7grHERg3yaK/Vho+512TQ1/IJ6axLXuYzOxG/bE5Jue1qEhpwkIZIE/Bz";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeHttpRequests(request ->
            request.requestMatchers(HttpMethod.POST).permitAll()
                    .requestMatchers(HttpMethod.GET).permitAll()
                    .requestMatchers(HttpMethod.PUT).permitAll()
                    .requestMatchers(HttpMethod.DELETE).permitAll()
                    .anyRequest().authenticated());

//    httpSecurity.oauth2ResourceServer(oauth2 ->
//        oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder).jwtAuthenticationConverter(jwtAuthenticationConverter()))
//            );

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
