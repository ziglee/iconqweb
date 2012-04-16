
<%@ page import="iconqweb.Concurso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="instituicao.label" default="Instituicao" /></td>
                            <td valign="top" class="value"><g:link controller="instituicao" action="show" id="${concursoInstance?.instituicao?.id}">${concursoInstance?.instituicao?.encodeAsHTML()}</g:link></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="estado.label" default="Estado" /></td>
                            <td valign="top" class="value"><g:link controller="estado" action="show" id="${concursoInstance?.estado?.id}">${concursoInstance?.estado?.encodeAsHTML()}</g:link></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.nome.label" default="Nome" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "nome")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.dataDivulgacao.label" default="Data Divulgacao" /></td>
                            <td valign="top" class="value"><g:formatDate date="${concursoInstance?.dataDivulgacao}" /></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.dataInscricoesFim.label" default="Data Fim Inscricoes" /></td>
                            <td valign="top" class="value"><g:formatDate date="${concursoInstance?.dataInscricaoFim}" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.dataInscricoesInicio.label" default="Data Inicio Inscricoes" /></td>
                            <td valign="top" class="value"><g:formatDate date="${concursoInstance?.dataInscricaoInicio}" /></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.situacao.label" default="Situacao" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "situacao.nome")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.vagas.label" default="Vagas" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "vagas")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.remuneracaoDe.label" default="Remuneracao De" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "remuneracaoDe")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.remuneracaoAte.label" default="Remuneracao Ate" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "remuneracaoAte")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.nivel.label" default="Nivel" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "nivel")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.area.label" default="Area" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "area")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.detalhes.label" default="Detalhes" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concursoInstance, field: "detalhes")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${concursoInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>

            <h1>Documentos</h1>
            <div class="dialog">
                <table>
                    <tbody>
                        <g:each in="${documentos}" var="documento">
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <g:link controller="documento" action="show" id="${documento.id}">${documento.nome}</g:link>
                                </td>
                                <td valign="top" class="value">
                                    <g:link controller="documento" action="download" id="${documento.id}">Download</g:link>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>

            <div class="buttons">
                <g:form controller="documento">
                    <g:hiddenField name="concurso.id" value="${concursoInstance?.id}" />
                    <span class="button"><g:actionSubmit class="create" action="create" value="Novo documento" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
