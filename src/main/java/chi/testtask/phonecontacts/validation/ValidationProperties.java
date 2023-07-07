package chi.testtask.phonecontacts.validation;

public class ValidationProperties {

    public static final String emailRegexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    public static final String phoneNumberRegexp = "^(\\+38|0)\\d{9}$\n";
}
