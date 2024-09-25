package io.formulate.common.auth.web.constant;

public class AuthConstants {
    public static final class Header {
        public static final String AUTHORIZATION = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";

        private Header() {}
    }

    public static final class Jwt {
        public static final String EXPIRES_AFTER = "EXPIRES_AFTER";
        public static final String PERMISSIONS = "per";
        public static final String SECRET = "JWT_SECRET";
        public static final String TOKEN = "token";

        private Jwt() {}
    }
}
