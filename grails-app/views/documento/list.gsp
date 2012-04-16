
<%@ page import="iconqweb.Documento" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
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
                            <g:sortableColumn property="id" title="${message(code: 'documento.id.label', default: 'Id')}" />
                            <th><g:message code="documento.concurso.label" default="Concurso" /></th>
                            <g:sortableColumn property="nome" title="${message(code: 'documento.nome.label', default: 'Nome')}" />
                            <g:sortableColumn property="contentType" title="${message(code: 'documento.contentType.label', default: 'Content Type')}" />
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${documentoInstanceList}" status="i" var="documentoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${documentoInstance.id}">${fieldValue(bean: documentoInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: documentoInstance, field: "concurso")}</td>
                            <td>${fieldValue(bean: documentoInstance, field: "nome")}</td>
                            <td>${fieldValue(bean: documentoInstance, field: "contentType")}</td>
                            <td><g:link action="download" id="${documentoInstance.id}">download</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${documentoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
