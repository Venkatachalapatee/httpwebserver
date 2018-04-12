There are two solutions in this code base. 

Solution1: 

Creates the Server Socket (ServerSocket), and responds to all request with "Hello World"

Does not convert the input Stream to HttpRequest/HttpContext, so it responds to all request.


Solution2: 

Uses com.sun.net.httpserver.HttpServer to create the Http Server. 

For the purpose of this exercise i am skipping the following TODOs

TODO:
- [ ] In Solution1 Convert the Stream into HttpRequest/HttpContext to verify the path, reject other requests
- [ ] Error Handling 
- [ ] Test Case
- [ ] Server Configuration to be injected using configuration file
- [ ] Build, Package, Deployment


 
 
 

