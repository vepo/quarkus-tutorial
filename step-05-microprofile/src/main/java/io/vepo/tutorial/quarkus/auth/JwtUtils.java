package io.vepo.tutorial.quarkus.auth;

import static io.vepo.tutorial.quarkus.auth.Roles.ADMIN_ROLES;
import static io.vepo.tutorial.quarkus.auth.Roles.USER_ROLES;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import io.vepo.tutorial.quarkus.user.User;

public class JwtUtils {
    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer", defaultValue = "https://vepo.io/issuer")
    String issuer;

    @Inject
    @ConfigProperty(name = "mp.jwt.expiration.time.in.minutes", defaultValue = "1000")
    int expirationTime;

    public String generate(User user) {
        return Jwt.issuer(issuer)
                  .upn(user.getUsername())
                  .subject(user.getId().toString())
                  .issuedAt(Instant.now())
                  .expiresAt(Instant.now().plus(expirationTime, MINUTES))
                  .groups(user.isAdmin() ? ADMIN_ROLES : USER_ROLES)
                  .claim(Claims.family_name.name(), user.getLastName())
                  .preferredUserName(user.getFirstName())
                  .sign();
    }
}
