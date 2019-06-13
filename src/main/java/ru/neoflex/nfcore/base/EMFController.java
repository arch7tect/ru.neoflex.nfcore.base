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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.nfcore.base.application.Application;
import ru.neoflex.nfcore.base.application.ApplicationFactory;
import ru.neoflex.nfcore.base.application.ApplicationPackage;
import ru.neoflex.nfcore.base.cdo.CDOServer;
import ru.neoflex.nfcore.base.report.ReportPackage;
import org.eclipse.emf.cdo.server.ocl.OCLQueryHandler;
import java.util.List;

@RestController()
@RequestMapping("/emf")
public class EMFController {
    @Autowired
    CDOSvc cdoSvc;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/application")
    Application application() {
        Application application = ApplicationFactory.eINSTANCE.createApplication();
        application.setName("MyApp");
        return application;
    }

    @GetMapping("/query")
    List query(@RequestParam(defaultValue = "instances") String language, @RequestParam String text) {
        CDOServer cdoServer = cdoSvc.getServer();
        CDOSession cdoSession = cdoServer.getSessionConfiguration().openSession();
        try {
            cdoSession.getPackageRegistry().putEPackage(ApplicationPackage.eINSTANCE);
            cdoSession.getPackageRegistry().putEPackage(ReportPackage.eINSTANCE);
            CDOTransaction cdoTransaction = cdoSession.openTransaction();
            try {
                Application application = ApplicationFactory.eINSTANCE.createApplication();
                application.setName("MyApp2");
                CDOResource resource = cdoTransaction.getOrCreateResource(language);
                resource.getContents().add(application);
                cdoTransaction.commit();
                CDOQuery cdoQuery = cdoTransaction.createQuery(language, text, null, false);
                List result = cdoQuery.getResult();
                cdoTransaction.commit();
                return result;
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
    JsonNode object(@PathVariable("id") Long id) {
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
}
