<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Home</a>
   
   <a href="${pageContext.request.contextPath}/productList">
      Product List
   </a>
   
   <a href="${pageContext.request.contextPath}/shoppingCart">
      My Cart
   </a>
   
   <security:authorize  access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
     <a href="${pageContext.request.contextPath}/orderList">
         Order List
     </a>
     
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_ADMIN')">
         <a href="${pageContext.request.contextPath}/product">
                        Create Product
         </a>
         
   </security:authorize>
  
</div> 


