
<%@ page import="iconqweb.Estado" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'estado.label', default: 'Estado')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'estado.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'estado.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="sigla" title="${message(code: 'estado.sigla.label', default: 'Sigla')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${estadoInstanceList}" status="i" var="estadoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${estadoInstance.id}">${fieldValue(bean: estadoInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: estadoInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: estadoInstance, field: "sigla")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${estadoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
