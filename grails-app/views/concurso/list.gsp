
<%@ page import="iconqweb.Concurso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
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
                            <g:sortableColumn property="id" title="${message(code: 'concurso.id.label', default: 'Id')}" />
                            <g:sortableColumn property="nome" title="${message(code: 'concurso.nome.label', default: 'Nome')}" />
                            <g:sortableColumn property="instituicao" title="${message(code: 'instituicao.label', default: 'instituicao')}" />
                            <g:sortableColumn property="estado" title="${message(code: 'estado.label', default: 'Estado')}" />
                            <g:sortableColumn property="dataDivulgacao" title="${message(code: 'concurso.dataDivulgacao.label', default: 'Data Divulgacao')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${concursoInstanceList}" status="i" var="concursoInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${concursoInstance.id}">${fieldValue(bean: concursoInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: concursoInstance, field: "nome")}</td>
                            <td>${fieldValue(bean: concursoInstance, field: "instituicao.sigla")}</td>
                            <td>${fieldValue(bean: concursoInstance, field: "estado.sigla")}</td>
                            <td><g:formatDate date="${concursoInstance.dataDivulgacao}" format="yyyy-MM-dd"/></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${concursoInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
