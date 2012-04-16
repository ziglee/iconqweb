

<%@ page import="iconqweb.Concurso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'concurso.label', default: 'Concurso')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src='${resource(dir:"js", file:"concurso.js")}'></script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${concursoInstance}">
            <div class="errors">
                <g:renderErrors bean="${concursoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="instituicao.id"><g:message code="instituicao.label" default="Instituicao" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'instituicao', 'errors')}">
                                    <g:select name="instituicao.id" from="${iconqweb.Instituicao.list()}" optionKey="id" value="${concursoInstance?.instituicao?.id}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="estado.id"><g:message code="estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'estado', 'errors')}">
                                    <g:select name="estado.id" from="${iconqweb.Estado.list()}" optionKey="id" value="${concursoInstance?.estado?.id}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="concurso.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${concursoInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataDivulgacao"><g:message code="concurso.dataDivulgacao.label" default="Data Divulgacao" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'dataDivulgacao', 'errors')}">
                                    <g:datePicker name="dataDivulgacao" precision="day" value="${concursoInstance?.dataDivulgacao}"  />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataInscricaoInicio"><g:message code="concurso.dataInscricaoInicio.label" default="Data Inscricao Inicio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'dataInscricaoInicio', 'errors')}">
                                    <g:datePicker name="dataInscricaoInicio" precision="day" value="${concursoInstance?.dataInscricaoInicio}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataInscricaoFim"><g:message code="concurso.dataInscricaoFim.label" default="Data Inscricao Fim" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'dataInscricaoFim', 'errors')}">
                                    <g:datePicker name="dataInscricaoFim" precision="day" value="${concursoInstance?.dataInscricaoFim}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vagas"><g:message code="concurso.vagas.label" default="Vagas" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'vagas', 'errors')}">
                                    <g:textField name="vagas" value="${fieldValue(bean: concursoInstance, field: 'vagas')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remuneracaoDe"><g:message code="concurso.remuneracaoDe.label" default="Remuneracao De" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'remuneracaoDe', 'errors')}">
                                    <g:textField name="remuneracaoDe" value="${fieldValue(bean: concursoInstance, field: 'remuneracaoDe')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remuneracaoAte"><g:message code="concurso.remuneracaoAte.label" default="Remuneracao Ate" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'remuneracaoAte', 'errors')}">
                                    <g:textField name="remuneracaoAte" value="${fieldValue(bean: concursoInstance, field: 'remuneracaoAte')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nivel"><g:message code="concurso.nivel.label" default="Nivel" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'nivel', 'errors')}">
                                    <g:textField name="nivel" value="${concursoInstance?.nivel}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="area"><g:message code="concurso.area.label" default="Area" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'area', 'errors')}">
                                    <g:textField name="area" value="${concursoInstance?.area}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="detalhes"><g:message code="concurso.detalhes.label" default="Detalhes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concursoInstance, field: 'detalhes', 'errors')}">
                                    <g:textField name="detalhes" value="${concursoInstance?.detalhes}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="situacao"><g:message code="concurso.situacao.label" default="Situacao" />:</label>
                                </td>
                                <td valign="bottom" class="value ${hasErrors(bean: concursoInstance, field: 'situacao', 'errors')}">
                                    <g:select name="situacao" from="${iconqweb.SituacaoConcurso?.values()}" keys="${iconqweb.SituacaoConcurso?.values()*.name()}" value="${concursoInstance?.situacao?.name()}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
