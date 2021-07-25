import Interface.LevelUp;

import java.util.ArrayList;

public class Pemain extends Akun implements LevelUp {
    private boolean block=true;
    private boolean regis=false;
    private ArrayList<String> lencana = new ArrayList<>();
    private int level=0;

    public Pemain(String username, String password) {
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

    public void TambahLencana(String lencanaa){
        lencana.add(lencanaa);
    }

    @Override
    public void TambahLevel() {
        level = level+1;
    }

    @Override
    public void KurangLevel() {
        level = level-1;
    }

    public ArrayList<String> getLencana() {
        return lencana;
    }

    public int getLevel() {
        return level;
    }
}
