package ru.neoflex.nfcore.base.cdo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.ecore.EObject;

import java.io.IOException;

public class CDOIDSerializer {

    public void serialize(EObject object, JsonGenerator jg, SerializerProvider provider) throws IOException {
        final CDOObject cdoObject = CDOUtil.getCDOObject(object);
        final CDOID cdoid = CDOIDUtil.getCDOID(cdoObject);

        jg.writeNumberField("_id", CDOIDUtil.getLong(cdoid));
    }

}
