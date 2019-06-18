package ru.neoflex.nfcore.base.application

import ru.neoflex.nfcore.base.test.application.Application

class ApplicationInit {
    {
        Application.metaClass.getFormsCount {->
            getForms().size()
        }
        Application.metaClass.getLinksCount = {->
            def application = delegate as Application
            application.getLinks().size()
        }
        Application.metaClass.title << {Map params->
            def application = delegate as Application
            "${application.getName()}(links: ${getLinksCount()}, forms: ${getFormsCount()})"
        }
        Application.metaClass.title2 << {Map params->
            def application = delegate as Application
            def title = application.title()
            for (form in application.forms) {
                title = title + " " + form.hello()
            }
            title
        }
    }
}
