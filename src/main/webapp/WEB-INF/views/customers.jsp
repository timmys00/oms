<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<html> 
<head> 
 	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> 
    <title><spring:message code="customers.title" /></title> 
</head>
<body>
	<section> 
              <div class="jumbotron"> 
                 <div class="container"> 
                    <h1><spring:message code="customers.headline" /></h1> 
                    <p><spring:message code="customers.action" /></p> 
                 </div> 
              </div> 
           </section> 
 
           <section class="container"> 
              <div class="row"> 
                 <c:forEach items="${customers}" var="product"> 
                    <div class="col-sm-6 col-md-3"> 
                       <div class="thumbnail"> 
                          <div class="caption"> 
                             <h3>${product.name}</h3> 
                             <p>${product.address}</p> 
        					 <p><spring:message code="customers.orderspercustomer" /></p> 
                          </div> 
                       </div> 
                    </div> 
                 </c:forEach> 
              </div> 
           </section> 
        </body> 
</body>
</html>