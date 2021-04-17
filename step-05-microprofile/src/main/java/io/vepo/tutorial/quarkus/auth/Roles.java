package io.vepo.tutorial.quarkus.auth;

import java.util.Set;

public class Roles {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final Set<String> USER_ROLES = Set.of(USER);
    public static final Set<String> ADMIN_ROLES = Set.of(ADMIN, USER);

}
