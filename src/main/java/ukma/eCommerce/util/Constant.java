package ukma.eCommerce.util;

/**
 * Created by Максим on 10/17/2016.
 */
public final class Constant {

    private Constant() {
        throw new RuntimeException();
    }

    public final int countryMinLen = 3, countryMaxLen = 50;
    public final int stateMinLen = 3, stateMaxLen = 50;
    public final int regionMinLen = 3, regionMaxLen = 50;
    public final int cityMinLen = 3, cityMaxLen = 50;

}
