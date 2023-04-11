package cinema;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    public static boolean isValidDateTime(String dateTimeStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            Date dateTime = sdf.parse(dateTimeStr);
            if (!dateTimeStr.equals(sdf.format(dateTime))) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}

