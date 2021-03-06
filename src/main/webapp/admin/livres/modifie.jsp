<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.domaine.modele.Livre" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%
Livre livre = (Livre)session.getAttribute("livre") ;
if (livre == null)
	response.sendRedirect(response.encodeURL("index.jsp")) ;
 else { 
%>
  <div id="content" class="col-full">
    <div class="col-1">
      <form action="controleLivres.jsp?commande=modifier" method="post">
        <div id="form">
	      <input type="hidden" name="refArticle" value="<%=livre.getRefArticle() %>"/>
<%
  Field[] articleFields = livre.getClass().getSuperclass().getDeclaredFields();
  ArrayList<Field> list = new ArrayList<Field>(Arrays.asList(articleFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("refArticle")) {
	  list.remove(i);
    }
  }
  articleFields = list.toArray(new Field[articleFields.length-1]);
  for (Field articleField : articleFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	Class noparams[] = {};
	Method method = livre.getClass().getSuperclass().getDeclaredMethod("get" + articleLabel,noparams) ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      pattern = ".*" ;
    }
%>
        <div>
          <label class="required" for="form_<%=articleField.getName() %>"><%=articleLabel %></label>
		  <input id="form_<%=articleField.getName() %>" type="text" size="70" name="<%=articleField.getName() %>" value="<% 
		  if(method.invoke(livre, null)!=null) out.print(method.invoke(livre, null)); 
          else out.print(""); 
          %>" required pattern="<%=pattern %>"/>
		</div>
<%
  }
  Field[] musiqueFields = livre.getClass().getDeclaredFields();
  list = new ArrayList<Field>(Arrays.asList(musiqueFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("dateDeParution")) {
	  list.remove(i);
    }
  }
  musiqueFields = list.toArray(new Field[musiqueFields.length-2]);
  for (Field articleField : musiqueFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	Class noparams[] = {};
	Method method = livre.getClass().getDeclaredMethod("get" + articleLabel,noparams) ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      pattern = ".*" ;
    }
%>
        <div>
          <label class="required" for="form_<%=articleField.getName() %>"><%=articleLabel %></label>
		  <input id="form_<%=articleField.getName() %>" type="text" size="70" name="<%=articleField.getName() %>" value="<% 
		  if(method.invoke(livre, null)!=null) out.print(method.invoke(livre, null)); 
          else out.print(""); 
          %>" required pattern="<%=pattern %>"/>
		</div>
<%
  }
%>
        </div>
        <div>
          <input type="Submit" name="Submit" Value="Valider"/>
	    </div>
	  </form>
	</div>
  </div>
<%
}
%>
<%@ include file="../piedDePage.html"%>
