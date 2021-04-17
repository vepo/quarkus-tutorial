# Step 05 - Enabling Resilience with Cache

In our previous tutorial, we create a REST API, accessing a Database with JPA and added some validations using Bean Validations.

Now we will start adding some resilience to our service. First, let's start talking about what is Resilience and why it is important.

According to [Cambridge](https://dictionary.cambridge.org/dictionary/english/resilience) Dictionary **Resilience** means:

| the ability of a substance to return to its usual shape after being bent, stretched, or pressed.

How this could be applied to microservices? 

In a world of Microservices, your services do not live alone, it communicates with others. And we can say that other services will fail, the network will fail. If our service depends from other, how can we avoid it from failling too?

Or imagine another situation. Our service depends on others, and this service is slow. This slowness can be caused by the network, it is not an implementation issue. How can we reduce the impact from it?

One important feature we can add to avoid failure is caching. 