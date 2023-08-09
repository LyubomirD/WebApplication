This is a side project from which I am learning new information
about Git, GitHub, RestApis, Spring Boot, Databases and Security in the internet.

What have I learned so far from this side project:
1. Git
- after the writing terrible code that introduces only bugs, you can go back to your last working commit
- you can divide to project code into branches that would function as a separate application/projects
- git commit -am "commit text", combine the "commit" and "add ." in one line

2. Github
- there is a way to automate part of your code

3. Spring Boot
- @Configuration can change how Spring Boot works, but you use it to your advantage
- To create and set custom annotations and exceptions
- Spring Data JPA prevents by default SQL injection attacks
- Even when testing custom validations they will throw an exceptions when provided invalid input 

4. Databases
- UUID is better for ID that uses as type SERIAL

5. Security
- http can be used when you are working locally with localhost
- https requires SSL or TLS certificate
- GitHub pages provides https
- to connect to https code for example that uses GitHub pages a CORS error occurs
- CORS is a mechanism to restrict users from accessing shared resources
- to bypass so to say we need a tunnel, ngrok can make one
- a tunnel that is in one end https and in the other localhost:PORT

Rough diagram of the project connections:

This diagram presents how I connect my frontend through the internet with my local backend!

![DiagramConnection](DiagramConnection.png)

Some downsides are that if the computer is turned off the backend would be shutdown completely, 
because the computer works as a server. In addition to that, the ngrok that makes the https tunnel between the 
frontend and the backend can only work for 2 hours, after tha the tunnel needs to be restarted and changed with new one.

However, the issue with the tunnel can be overcome by automating the change of the tunnel, with the help of GitHub Actions!
