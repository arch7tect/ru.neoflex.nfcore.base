package ru.neoflex.nfcore.base.report

class ReportInit {
    {
        Report.metaClass.hello = {->
            "Hello from report ${application.name}.${name}"
        }
    }
}
