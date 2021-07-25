import java.io.Serializable;

public class Admin implements Serializable {
    private String pengumuman = "";

    public void setPengumuman(String pengumuman) {
        this.pengumuman = pengumuman;
    }

    public String getPengumuman() {
        return pengumuman;
    }
}

