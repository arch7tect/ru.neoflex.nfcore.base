package ru.neoflex.nfcore.base.report

import ru.neoflex.nfcore.base.test.report.Report

class ReportInit {
    {
        Report.metaClass.hello = {->
            "Hello from report ${application.name}.${name}"
        }
    }
}
