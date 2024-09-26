<b>Product Service</b>

<b>Prerequisite:</b>
 Open UnitTest class in rightside window of IntelliJ IDE and corresponding class definition in leftside window 
1. Begin with ServiceClass test method
2. Test Data / Repository layer is something we would like to mock. So @Mock applied to Repository instance
3. Service class is something we would inject the mock so, @InjectMocks to Service instance

<b>Functionality getAllProducts steps</b><br/>
5. Start with each atomic test in Service - getAllProducts() - Red<br/>
6. Build up the corresponding getAllProducts in Service class with constant return value - Green<br/>
7. Replace the constant return value in Service class with Repository - Introduce the Repository layer so persistence is handled - Blue (Refactor)

<b>Application test for getProducts</b> <br/>
Request: curl "http://localhost:8080/products" <br/>
Response: [{"id":1,"name":"Product 1","price":100.0,"quantity":10},{"id":2,"name":"Product 2","price":200.0,"quantity":20},{"id":3,"name":"Product 3","price":300.0,"quantity":30}] <br/>

<b>Functionality searchProducts steps</b> <br/>
8. Start with each atomic test in Service - searchProducts() - Red <br/>
9. Build up the corresponding searchProducts in Service class with constant return value - Green <br/>
10. Replace the constant return value in Service class with Repository - Introduce the Repository layer so persistence is handled - Blue1 (Refactor) <br/>
11. Introduce Validation of the input search parameter in Product Object using Validator annotations - Blue2 (Refactor) <br/>
12. Introduce Validation of the input search parameter in Controller searchProducts method to RequestBody using Validate annotation - Blue3 (Refactor) <br/>

<b>Application test for searchProducts & parameter to search for</b> <br/>
Request: curl -X GET "http://localhost:8080/products/search?id=1&name=Product%201&price=100&quantity=10" <br/>
Response: [{"id":1,"name":"Product 1","price":100.0,"quantity":10}] <br/>
