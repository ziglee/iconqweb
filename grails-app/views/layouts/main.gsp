<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <title><g:layoutTitle default="iConq" /></title>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <r:require modules="jquery-ui, style, application"/>
        <r:layoutResources/>
        <g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo">
            <a href="${createLink(uri: '/')}"><img src="${resource(dir:'images',file:'brasao.jpeg')}" alt="iConq" border="0" /></a>
        </div>
        <g:layoutBody />
        <r:layoutResources/>
    </body>
</html>