package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.core.internal.jobs.ObjectMap;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOQuery;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.application.*;
import ru.neoflex.nfcore.base.cdo.CDOServer;
import ru.neoflex.nfcore.base.report.Report;
import ru.neoflex.nfcore.base.report.ReportFactory;
import ru.neoflex.nfcore.base.report.ReportPackage;
//import org.eclipse.emf.cdo.server.ocl.OCLQueryHandler;
import java.util.List;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @Autowired
    CDOSvc cdoSvc;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/application")
    JsonNode application(@RequestParam(defaultValue = "false") Boolean create) {
        CDOServer cdoServer = cdoSvc.getServer();
        CDOSession cdoSession = cdoServer.getSessionConfiguration().openSession();
        try {
            cdoSession.getPackageRegistry().putEPackage(ApplicationPackage.eINSTANCE);
            cdoSession.getPackageRegistry().putEPackage(ReportPackage.eINSTANCE);
            CDOTransaction cdoTransaction = cdoSession.openTransaction();
            try {
                if (create) {
                    Application application1 = ApplicationFactory.eINSTANCE.createApplication();
                    application1.setName("MyApp1");
                    Module forms = ApplicationFactory.eINSTANCE.createModule();
                    forms.setName("forms");
                    application1.getModules().add(forms);
                    Report report = ReportFactory.eINSTANCE.createReport();
                    report.setName("My best report");
                    application1.getForms().add(report);
                    forms.getForms().add(report);
                    Module modules = ApplicationFactory.eINSTANCE.createModule();
                    modules.setName("modules");
                    application1.getModules().add(modules);
                    ListForm modulesListForm = ApplicationFactory.eINSTANCE.createListForm();
                    modulesListForm.setName("Modules List");
                    modulesListForm.setEClass(ApplicationPackage.eINSTANCE.getModule());
                    application1.getForms().add(modulesListForm);
                    modules.getForms().add(modulesListForm);
                    Application application2 = ApplicationFactory.eINSTANCE.createApplication();
                    application2.setName("MyApp2");
                    application1.getLinks().add(application2);
                    CDOResource resource1 = cdoTransaction.getOrCreateResource("test/application/" + application1.getName());
                    resource1.getContents().clear();
                    resource1.getContents().add(application1);
                    CDOResource resource2 = cdoTransaction.getOrCreateResource("test/application/" + application2.getName());
                    resource2.getContents().clear();
                    resource2.getContents().add(application2);
                    cdoTransaction.commit();
                }
                CDOResource resource = cdoTransaction.getOrCreateResource("test/application/MyApp1");
                EcoreUtil.resolveAll((Resource) resource);
                return objectMapper.valueToTree(resource);
            }
            catch (Throwable t) {
                cdoTransaction.rollback();
                throw t;
            }
            finally {
                cdoTransaction.close();
            }
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
        finally {
            cdoSession.close();
        }
    }

    @GetMapping("/query")
    JsonNode query(@RequestParam(defaultValue = "instances") String language, @RequestParam String text) {
        CDOServer cdoServer = cdoSvc.getServer();
        CDOSession cdoSession = cdoServer.getSessionConfiguration().openSession();
        try {
            cdoSession.getPackageRegistry().putEPackage(ApplicationPackage.eINSTANCE);
            cdoSession.getPackageRegistry().putEPackage(ReportPackage.eINSTANCE);
            CDOTransaction cdoTransaction = cdoSession.openTransaction();
            try {
                CDOQuery cdoQuery = cdoTransaction.createQuery(language, text, null, false);
                List result = cdoQuery.getResult();
                return objectMapper.valueToTree(result);
            }
            catch (Throwable t) {
                cdoTransaction.rollback();
                throw t;
            }
            finally {
                cdoTransaction.close();
            }
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
        finally {
            cdoSession.close();
        }
    }

    @GetMapping("/object/{id}")
    JsonNode getObject(@PathVariable("id") Long id) {
        CDOServer cdoServer = cdoSvc.getServer();
        CDOSession cdoSession = cdoServer.getSessionConfiguration().openSession();
        try {
            cdoSession.getPackageRegistry().putEPackage(ApplicationPackage.eINSTANCE);
            cdoSession.getPackageRegistry().putEPackage(ReportPackage.eINSTANCE);
            CDOTransaction cdoTransaction = cdoSession.openTransaction();
            try {
                CDOID cdoid = CDOIDUtil.createLong(id);
                CDOObject cdoObject = cdoTransaction.getObject(cdoid);
                return objectMapper.valueToTree(cdoObject);
            }
            catch (Throwable t) {
                cdoTransaction.rollback();
                throw t;
            }
            finally {
                cdoTransaction.close();
            }
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
        finally {
            cdoSession.close();
        }
    }

    @GetMapping("/resource")
    JsonNode getResource(@RequestParam String path) {
        CDOServer cdoServer = cdoSvc.getServer();
        CDOSession cdoSession = cdoServer.getSessionConfiguration().openSession();
        try {
            cdoSession.getPackageRegistry().putEPackage(ApplicationPackage.eINSTANCE);
            cdoSession.getPackageRegistry().putEPackage(ReportPackage.eINSTANCE);
            CDOTransaction cdoTransaction = cdoSession.openTransaction();
            try {
                CDOResource cdoResource = cdoTransaction.getResource(path);
                return objectMapper.valueToTree(cdoResource);
            }
            catch (Throwable t) {
                cdoTransaction.rollback();
                throw t;
            }
            finally {
                cdoTransaction.close();
            }
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
        finally {
            cdoSession.close();
        }
    }
}
