package ru.neoflex.nfcore.base;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.ecore.EObject;
import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.utils.ValueReader;
import org.emfjson.jackson.utils.ValueWriter;
import org.springframework.stereotype.Component;

@Component
public class EMFModuleBean extends EMFModule {
    EMFModuleBean() {
        super();
        configure(EMFModule.Feature.OPTION_USE_ID, true);
        setIdentityInfo(new EcoreIdentityInfo("_id",
                new ValueReader<Object, String>() {
                    @Override
                    public String readValue(Object o, DeserializationContext deserializationContext) {
                        return o.toString();
                    }
                },
                new ValueWriter<EObject, Object>() {
                    @Override
                    public Object writeValue(EObject eObject, SerializerProvider serializerProvider) {
                        CDOObject cdoObject = CDOUtil.getCDOObject(eObject);
                        CDOID cdoid = CDOIDUtil.getCDOID(cdoObject);
                        Long id = CDOIDUtil.getLong(cdoid);
                        return id;
                    }
                }));
    }
}
