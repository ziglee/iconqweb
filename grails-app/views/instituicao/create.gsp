

<%@ page import="iconqweb.Instituicao" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instituicao.label', default: 'Instituicao')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src='${resource(dir:"js", file:"jquery.maskedinput-1.1.4.pack.js")}'></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#telefone").mask("(99) 9999-9999");
            });
        </script>
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
            <g:hasErrors bean="${instituicaoInstance}">
            <div class="errors">
                <g:renderErrors bean="${instituicaoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sigla">Sigla</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instituicaoInstance, field: 'sigla', 'errors')}">
                                    <g:textField name="sigla" value="${instituicaoInstance?.sigla}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="instituicao.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instituicaoInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${instituicaoInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="site"><g:message code="instituicao.site.label" default="Site" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instituicaoInstance, field: 'site', 'errors')}">
                                    <g:textField name="site" value="${instituicaoInstance?.site}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefone"><g:message code="instituicao.telefone.label" default="Telefone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instituicaoInstance, field: 'telefone', 'errors')}">
                                    <g:textField name="telefone" value="${instituicaoInstance?.telefone}" id="telefone"/>
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
