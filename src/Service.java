import java.util.ArrayList;

public class Service {
    private String serviceName;
    private ArrayList<String>spName;

    public Service(String serviceName, ArrayList<String> spName) {
        this.serviceName = serviceName;
        this.spName = spName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ArrayList<String> getSpName() {
        return spName;
    }

    public void setSpName(ArrayList<String> spName) {
        this.spName = spName;
    }

}
