package ru.neoflex.nfcore.base.cdo;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emfjson.jackson.databind.EMFContext;
import org.emfjson.jackson.databind.deser.ReferenceEntry;
import org.emfjson.jackson.errors.JSONException;
import org.emfjson.jackson.handlers.URIHandler;

import java.io.IOException;
import java.util.Collection;

public class CDOReferenceDeserializer extends JsonDeserializer<ReferenceEntry> {

    private final CDOTransaction transaction;

    public CDOReferenceDeserializer(CDOTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public ReferenceEntry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (JsonToken.VALUE_NUMBER_INT.equals(jsonParser.getCurrentToken())) {
            final EObject parent = EMFContext.getParent(deserializationContext);
            final EReference reference = EMFContext.getReference(deserializationContext);
            final JsonLocation location = jsonParser.getCurrentLocation();
            final long value = jsonParser.getLongValue();

            return new ReferenceEntry() {
                @Override
                public void resolve(DatabindContext context, URIHandler handler) {
                    CDOID cdoid = CDOIDUtil.createLong(value);

                    CDOObject object;
                    try {
                        object = transaction.getObject(cdoid);
                    } catch (Exception e) {
                        parent.eResource().getErrors().add(new JSONException(e, location));
                        return;
                    }

                    try {
                        if (!reference.isMany()) {
                            parent.eSet(reference, object);
                        }
                        else {
                            Collection<EObject> values = (Collection<EObject>) parent.eGet(reference);
                            if (values != null) {
                                values.add(object);
                            }
                        }
                    } catch (Exception e) {
                        parent.eResource().getErrors().add(new JSONException(e, location));
                    }
                }
            };
        }

        return null;
    }
}
