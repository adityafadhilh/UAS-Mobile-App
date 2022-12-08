package id.ac.umn.uas_mobileapp;

public class Item {

    String type;
    String nominal;
    int image;


    public Item(String type, String nominal, int image) {
        this.type = type;
        this.nominal = nominal;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
