# Step 04 - Using MicroProfile specifications

As I'm writing this post, Quarkus implements MicroProfile version 3.2. MicroProfile, as Jarkarta EE, is a set of specification that can be implemented by any Framework. The main difference is that Jakarta EE addresses a wide range of problems, MicroProfile is focused on MicroServices patterns.

If you don't know what is MicroServices pattern, you need to know [The 12 Factor App](https://12factor.net/). This is not an extensive description of all patterns, but just 12 best practices written in 2011 to Heroku developers. Based on these best practices we can build applications that do not depend on the environment. From 12 Factor, we can build a lot Patterns, some of then you can find on [Microservices.io](https://microservices.io/patterns/index.html). In this post we will show how to implement:
* [Externalized configuration](https://microservices.io/patterns/externalized-configuration.html) that reffers to [Factor III. Config](https://12factor.net/config)
* [Health Check API](https://microservices.io/patterns/observability/health-check-api.html)
* [Application metrics](https://microservices.io/patterns/observability/application-metrics.html)

Microprofile has more specifications, but for this post we will focus only in this tree.

## Externalizing Configuration

Why we need to Externalize all Configuration and what this means?

The main advantage is to avoid rebuilding the service. Imagine that for each environment (development, testing, staging, production) you have different settings, if we build everything again, how can we ensure that everything is fine? If we got the same error only on production package, we will have an error on production. How to avoid this situation?

These values can be inside a properties file or on the environment variables. How is best way to read it? To solve this problem, MicroProfile proposed the [Configuration Specification](https://download.eclipse.org/microprofile/microprofile-config-1.4/microprofile-config-spec.html).

MicroProfile Config allows your application to acces a value vase on any source. By default we have 
* System properties (default ordinal=400).
* Environment variables (default ordinal=300).
* A ConfigSource for each property file META-INF/microprofile-config.properties found on the classpath. (default ordinal = 100).

