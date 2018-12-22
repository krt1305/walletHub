package util;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverType type)
    {

        DriverManager driverManager = null;
        switch (type) {
            case CHROME:
                driverManager=new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager=new FIrefoxDriverManager();
                break;

        }
        return driverManager;
    }
}
