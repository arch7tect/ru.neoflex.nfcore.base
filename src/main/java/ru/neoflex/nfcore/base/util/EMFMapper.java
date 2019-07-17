package ru.neoflex.nfcore.base.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.annotations.EcoreTypeInfo;
import org.emfjson.jackson.databind.EMFContext;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.utils.ValueWriter;

public class EMFMapper {
    public static EMFModule getModule() {
        EMFModule emfModule = new EMFModule();
        emfModule.configure(EMFModule.Feature.OPTION_USE_ID, true);
        emfModule.setTypeInfo(new EcoreTypeInfo("eClass"));
        emfModule.setIdentityInfo(new EcoreIdentityInfo("_id",
                new ValueWriter<EObject, Object>() {
                    @Override
                    public Object writeValue(EObject eObject, SerializerProvider context) {
                        URI eObjectURI = EMFContext.getURI(context, eObject);
                        if (eObjectURI == null) {
                            return null;
                        }
                        return eObjectURI.fragment();
                        /*Object id = null;
                        Resource resource = EMFContext.getResource(context, eObject);
                        if (resource instanceof JsonResource) {
                            id = ((JsonResource) resource).getID(eObject);
                        }
                        URI eObjectURI = EMFContext.getURI(context, eObject);
                        if (eObjectURI != null) {
                            String fragment = eObjectURI.fragment();
                            if (fragment != null) {
                                while (fragment.startsWith("#")) {
                                    fragment = fragment.substring(1);
                                }
                                if (id == null) {
                                    id = fragment;
                                }
                                else {
                                    id = id + "#" + fragment;
                                }
                            }
                        }
                        return id;*/
                    }
                }));
        //emfModule.setReferenceInfo(new EcoreReferenceInfo("$ref"));
        return emfModule;
    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(getModule());
        return mapper;
    }


}
