modules = {
    application {
        dependsOn 'jquery'
        resource url:'/js/application.js'
    }

    'style' {
        resource url:'/css/main.css'
    }

    overrides {
        'jquery' {
            resource id:'js', url:[dir:'js/jquery', file:"jquery-1.6.4.min.js"]
        }

        'jquery-dev' {
            resource id:'js', url:[dir:'js/jquery', file:"jquery-1.6.4.js"], disposition:'head'
        }
    }

}