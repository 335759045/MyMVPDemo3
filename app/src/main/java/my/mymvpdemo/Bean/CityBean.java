package my.mymvpdemo.Bean;

/**
 * Created by kw on 2018/7/25.
 */

public class CityBean {

    /**
     * provinceid : 510000
     * cityid : 510100
     * city : 成都
     * phone : 15202894508
     * user : 唐先生
     */

    private int provinceid;
    private int cityid;
    private String city;
    private String phone;
    private String user;

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
