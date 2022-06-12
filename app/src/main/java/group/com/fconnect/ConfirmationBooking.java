package group.com.fconnect;
public class ConfirmationBooking
{
    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getIdsan() {
        return idsan;
    }

    public void setIdsan(String idsan) {
        this.idsan = idsan;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    int id=0;
    String idsan;
    String user;
    String price;
    String time;

}
