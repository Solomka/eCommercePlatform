package ukma.eCommerce.core.paymentModule.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.beans.PropertyEditorSupport;

/**
 * <p>
 *     Converts string into instance of {@linkplain org.joda.time.DateTime}
 * </p>
 * Created by Максим on 10/15/2016.
 */
public final class JodaTimeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

        try {
            setValue(formatter.parseDateTime(text));
        } catch (Exception e) {
            setValue(null);
        }
    }
}
