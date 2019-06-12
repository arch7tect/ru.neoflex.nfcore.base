package ru.neoflex.nfcore.base.cdo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.ecore.EObject;

import java.io.IOException;

public class CDOReferenceSerializer extends JsonSerializer<EObject> {

    @Override
    public void serialize(EObject eObject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final CDOObject cdoObject = CDOUtil.getCDOObject(eObject);
        final CDOID cdoid = CDOIDUtil.getCDOID(cdoObject);

        if (cdoid != null) {
            jsonGenerator.writeNumber(CDOIDUtil.getLong(cdoid));
        } else {
            jsonGenerator.writeNull();
        }
    }
}
