This side project stands as one of my most complex and ambitious undertakings, showcasing my growing skills and commitment.

As it stands, the project surrounds three main web pages, among them a login and signup interface. 
While my primary focus has been backend development, I took the opportunity to expand my horizons by crafting 
a basic frontend. This not only enabled me to deploy the project online but also provided a realistic context 
for employing the Spring Framework.

The project's components break down as follows:

- Frontend: Comprising a login page, signup page, and a work-in-progress webpage intended to showcase products.

- Backend: Encompassing a RestApi, incorporating custom Spring annotations and exceptions, configuring 
the Spring environment, implementing security measures, and utilizing Spring JPA for database interaction.

While building this project I gained valuable knowledge in many fields of development and here is glimpse 
of what I have learned so far:

1. Git
- gaining the ability to revert to a functional commit after encountering coding errors
- branching for modular development
- streamlining commit and addition operations with git commit -am

2. GitHub
- recognizing the potential for automation in code management and collaboration
- GitHub pages provides https by default and for free use
- insight gives you the ability to see the project data in charts

3. Spring Boot
- exploring the transformative power of @Configuration for customizing Spring Boot behavior
- crafting personalized annotations and exceptions
- utilizing Spring Data JPA's safeguard against SQL injection attacks
- comprehending how invalid inputs trigger exceptions even during custom validation testing.

4. Tests
- gaining skills in writing correct test statements
- there are two types of tests: isolated logic and integration logic
- Mock: fake version of an object, but looks and acts like the real one
- InjectMock: providing the tested object with the fake version of the object that it needs for testing

5. Databases
- embracing the superiority of UUID over SERIAL as an ID type.
- protocols
- indexes
- Object-Orientated Database

6. Security
- gaining clarity on the role of HTTP and HTTPS 
- understanding SSL/TLS certificates for secure communication
- grappling with CORS errors as a mechanism for restricting resource access 
- ngrok tunnels

The diagram outlines how the frontend gets connected to the local backend through the internet, 
highlighting the architectural connections and interactions.

![DiagramConnection](DiagramConnection.png)

While there are certain limitations, such as the backend shutting down if the host computer powers off and the ngrok 
tunnel's time constraint, these obstacles can be addressed through innovative solutions. For example, automating 
tunnel changes using GitHub Actions can effectively resolve the tunnel-related challenges.