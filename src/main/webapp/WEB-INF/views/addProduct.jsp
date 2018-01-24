<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<html> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> 
    <title><spring:message code="addProduct.title" /> </title>
</head> 
<body> 
		<section> 
        	<div class="pull-right" style="padding-right:50px"> 
            	<a href="?language=en" >English</a>|<a href="?language=nl" >Dutch</a>
            	 <a href="<c:url value="/logout" />">Logout</a>              
        	</div> 
      	</section> 

         <section> 
            <div class="jumbotron"> 
               <div class="container"> 
                  <h1><spring:message code="addProduct.headline" /></h1> 
                  <p><spring:message code="addProduct.action" /></p> 
               </div> 
            </div> 
         </section> 
         
         <section class="container"> 
            <form:form  
            		method="POST" modelAttribute="newProduct" class="form-horizontal" 
            		enctype="multipart/form-data"> 
				
				<fieldset> 
                <legend><spring:message code="addProduct.legend" /></legend> 
 
 				<div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="productId">
	                	<spring:message code="addProduct.form.productId.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
	                	<form:input id="productId" path="productId" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="name">
		                <spring:message code="addProduct.form.name.label" /> 
					</label> 
	                <div class="col-lg-10"> 
 						<form:input id="name" path="name" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="unitPrice">
	                	<spring:message code="addProduct.form.unitPrice.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
                        <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="manufacturer">
	                	<spring:message code="addProduct.form.manufacturer.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
                        <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="category">
	                	<spring:message code="addProduct.form.category.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
                        <form:input id="category" path="category" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
	                	<spring:message code="addProduct.form.unitsInStock.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
                        <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                <!-- 
                <div class="form-group"> 
	                <label class="control-label col-lg-2 col-lg-2" for="unitsInOrder">
	                	<spring:message code="addProduct.form.unitsInOrder.label" /> 
	                </label> 
	                <div class="col-lg-10"> 
						<form:input id="unitsInOrder" path="unitsInOrder" type="text" class="form:input-large"/> 
	                </div> 
                </div> 
                  -->
                <div class="form-group"> 
                	<label class="control-label col-lg-2" for="description">
                		<spring:message code="addProduct.form.description.label" /> 
                	</label> 
                    <div class="col-lg-10"> 
                    	<form:textarea id="description" path="description" rows = "2"/> 
                    </div> 
                </div> 
 				<!--  
                <div class="form-group"> 
                	<label class="control-label col-lg-2" for="discontinued">
                		<spring:message code="addProduct.form.discontinued.label" /> 
                	</label> 
                    <div class="col-lg-10"> 
                    	<form:checkbox  id="discontinued"  path="discontinued"/> 
                    </div> 
                </div> 
              -->
                <div class="form-group"> 
                	<label class="control-label col-lg-2" for="condition">
                		<spring:message code="addProduct.form.condition.label" /> 
                	</label> 
                    <div class="col-lg-10"> 
                    	<form:radiobutton path="condition" value="New" />New  
                        <form:radiobutton path="condition" value="Old" />Old  
                        <form:radiobutton path="condition" value="Refurbished" />Refurbished 
                    </div> 
                </div> 
                
                <div class="form-group"> 
			         <label class="control-label col-lg-2" for="productImage">       
			         	<spring:message code="addProduct.form.productImage.label"/>
			         </label> 
			         <div class="col-lg-10"> 
			            <form:input id="productImage" path="productImage" type="file" class="form:input-large" /> 
			         </div> 
			      </div> 
                
             
                <div class="form-group"> 
	                <div class="col-lg-offset-2 col-lg-10"> 
	                	<input type="submit" id="btnAdd" class="btn btn-primary" value ="Add"/> 
	                </div> 
                </div> 
                
               </fieldset> 
            </form:form> 
         </section> 
</body> 
</html> 