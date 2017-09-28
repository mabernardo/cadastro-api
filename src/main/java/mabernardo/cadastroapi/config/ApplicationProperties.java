package mabernardo.cadastroapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;

/**
 * Propriedades espec&iacute;ficas da aplica&ccedil;&atilde;o.
 *
 * Essas propriedades s&atilde;o configuradas no arquivo application.yml,
 * na se&ccedil;&atilde;o "application".
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Security security = new Security();

    private final Mail mail = new Mail();

    private final Swagger swagger = new Swagger();

    private final Ribbon ribbon = new Ribbon();

    private final CorsConfiguration cors = new CorsConfiguration();

    public Security getSecurity() {
        return security;
    }

    public Mail getMail() {
        return mail;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Ribbon getRibbon() {
        return ribbon;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public static class Security {

        private final RememberMe rememberMe = new RememberMe();

        private final ClientAuthorization clientAuthorization = new ClientAuthorization();

        private final Authentication authentication = new Authentication();

        public RememberMe getRememberMe() {
            return rememberMe;
        }

        public ClientAuthorization getClientAuthorization() {
            return clientAuthorization;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class ClientAuthorization {

            private String accessTokenUri;

            private String tokenServiceId;

            private String clientId;

            private String clientSecret;

            public String getAccessTokenUri() {
                return accessTokenUri;
            }

            public void setAccessTokenUri(String accessTokenUri) {
                this.accessTokenUri = accessTokenUri;
            }

            public String getTokenServiceId() {
                return tokenServiceId;
            }

            public void setTokenServiceId(String tokenServiceId) {
                this.tokenServiceId = tokenServiceId;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getClientSecret() {
                return clientSecret;
            }

            public void setClientSecret(String clientSecret) {
                this.clientSecret = clientSecret;
            }
        }

        public static class Authentication {

            private final Oauth oauth = new Oauth();

            private final Jwt jwt = new Jwt();

            public Oauth getOauth() {
                return oauth;
            }

            public Jwt getJwt() {
                return jwt;
            }

            public static class Oauth {

                private String clientId;

                private String clientSecret;

                private int tokenValidityInSeconds = 1800;

                public String getClientId() {
                    return clientId;
                }

                public void setClientId(String clientId) {
                    this.clientId = clientId;
                }

                public String getClientSecret() {
                    return clientSecret;
                }

                public void setClientSecret(String clientSecret) {
                    this.clientSecret = clientSecret;
                }

                public int getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }
            }

            public static class Jwt {

                private String secret;

                private long tokenValidityInSeconds = 1800;

                private long tokenValidityInSecondsForRememberMe = 2592000;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }

        public static class RememberMe {

            @NotNull
            private String key;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }

    public static class Mail {

        private String from = "";

        private String baseUrl = "";

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

    public static class Swagger {

        private String title = "Application API";

        private String description = "API documentation";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName;

        private String contactUrl;

        private String contactEmail;

        private String license;

        private String licenseUrl;

        private String defaultIncludePattern = "/api/.*";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }
    }

    public static class Ribbon {

        private String[] displayOnActiveProfiles;

        public String[] getDisplayOnActiveProfiles() {
            return displayOnActiveProfiles;
        }

        public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles) {
            this.displayOnActiveProfiles = displayOnActiveProfiles;
        }
    }

}
