public class Organizer extends Akun{
    private boolean block=true;
    private boolean regis=false;

    public Organizer(String username, String password) {
        super(username, password);
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public boolean isBlock() {
        return block;
    }

    public boolean isRegis() {
        return regis;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setRegis(boolean regis) {
        this.regis = regis;
    }
}
