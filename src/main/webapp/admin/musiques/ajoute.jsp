<%@ page pageEncoding="UTF-8"%>
<%@ include file="../enTetePage.html"%>
<%@ page import="commerce.catalogue.domaine.modele.Musique" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%
  Musique musique = new Musique() ;
%>
  <div id="content" class="col-full">
    <div class="col-1">
      <form action="controleMusiques.jsp?commande=ajouter" method="post">
        <div id="form">
<%
  Field[] articleFields = musique.getClass().getSuperclass().getDeclaredFields();
  ArrayList<Field> list = new ArrayList<Field>(Arrays.asList(articleFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("refArticle")) {
	  list.remove(i);
    }
  }
  articleFields = list.toArray(new Field[articleFields.length-1]);
  for (Field articleField : articleFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	String value = "" ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   value = "0";
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      value = "0.0";
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      value = "";
      pattern = ".*" ;
    }
%>
          <div>
            <label class="required" for="form_<%=articleField.getName() %>"><%=articleLabel %></label>
		    <input id="form_<%=articleField.getName() %>" type="text" size="70" name="<%=articleField.getName() %>" value="<%=value %>" required pattern="<%=pattern %>"/>
		  </div>
<%
  }
  Field[] musiqueFields = musique.getClass().getDeclaredFields();
  list = new ArrayList<Field>(Arrays.asList(musiqueFields));
  for (int i = 0; i < list.size(); i++) {
    if (list.get(i).getName().equals("dateDeParution")) {
	  list.remove(i);
    }
	if (list.get(i).getName().equals("pistes")) {
	  list.remove(i);
    }
  }
  musiqueFields = list.toArray(new Field[musiqueFields.length-2]);
  for (Field articleField : musiqueFields) {
    String articleLabel = articleField.getName().substring(0,1).toUpperCase() + articleField.getName().substring(1) ;
	String value = "" ;
	String pattern = ".*" ;
	if(articleField.getType().toString().equals("int")) {
	   value = "0";
	   pattern = "\\d+" ;
	}
    if(articleField.getType().toString().equals("double")) {
      value = "0.0";
      pattern = "\\d+.\\d+" ;
	}
    if(articleField.getType().toString().equals("class java.lang.String")) {
      value = "";
      pattern = ".*" ;
    }
%>
          <div>
            <label class="required" for="form_<%=articleField.getName() %>"><%=articleLabel %></label>
		    <input id="form_<%=articleField.getName() %>" type="text" size="70" name="<%=articleField.getName() %>" value="<%=value %>" required pattern="<%=pattern %>"/>
	      </div>
<%
  }
%>
	    </div>
        <div>
          <input id="form_valider" type="Submit" name="Submit" Value="Valider"/>
	    </div>
	  </form>
    </div>
  </div>
<%@ include file="../piedDePage.html"%>
