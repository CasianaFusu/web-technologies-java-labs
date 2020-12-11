package beans;

public class Price {
    private String value;

    public Price(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
