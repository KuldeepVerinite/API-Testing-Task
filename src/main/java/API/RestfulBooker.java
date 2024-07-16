package API;

public enum RestfulBooker implements ProductUrl{

    Base("https://restful-booker.herokuapp.com"),
    Create_token("/auth"),
    Booking("/booking");

    private String Url;
    RestfulBooker(String url){
        this.Url= url;
    }

    @Override
    public String getUrl(Object... params) {
        return this.Url;
    }
}
