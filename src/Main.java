import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        ArrayList<Organizer> organizers = new ArrayList<>();
        ArrayList<Pemain> pemains = new ArrayList<>();
        ArrayList<Events> event = new ArrayList<>();

        String fileAdmin = "Admin.txt";
        String fileOrganizer = "Organizer.txt";
        String filePemain = "Pemain.txt";
        String fileEvents = "Events.txt";

        try {
            ObjectInputStream inAdmin = new ObjectInputStream(new FileInputStream(fileAdmin));
            admin = (Admin) inAdmin.readObject();
            inAdmin.close();
            System.out.println("LOAD SUCCESS\n");

            ObjectInputStream inOrganizers = new ObjectInputStream(new FileInputStream(fileOrganizer));
            organizers = (ArrayList<Organizer>) inOrganizers.readObject();
            inOrganizers.close();

            ObjectInputStream inPemains = new ObjectInputStream(new FileInputStream(filePemain));
            pemains = (ArrayList<Pemain>) inPemains.readObject();
            inPemains.close();

            ObjectInputStream inEvent = new ObjectInputStream(new FileInputStream(fileEvents));
            event = (ArrayList<Events>) inEvent.readObject();
            inEvent.close();


        } catch(IOException ex) {
            System.out.println("NO DATA\n");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        Scanner read = new Scanner(System.in);
        int jawab;

        do {
            HeaderLogin(admin);

            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    MenuAdmin(admin, organizers, pemains);
                    break;
                }
                case 2 : {
                    LoginOrganizers(organizers, event);
                    break;
                }
                case 3 : {
                    LoginPemain(pemains, event);
                    break;
                }
                case 4 : {
                    String pilihan;
                    do{
                        System.out.print("Save Data? [ya/tidak]");
                        pilihan = read.nextLine();
                    }while (!pilihan.equals("ya") && !pilihan.equals("tidak"));
                    if (pilihan.equals("ya")){
                        saveData(organizers, pemains, event, admin, fileOrganizer, filePemain, fileEvents, fileAdmin);
                    }
                    // exit and save
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada\n");
                    break;
                }
            }

        }while (jawab!=4);
    }

    private static void MenuAdmin(Admin admin, ArrayList<Organizer> organizers, ArrayList<Pemain> pemains) {
        Scanner read = new Scanner(System.in);
        String username;
        String password;
        String back;
        int jawab;


        System.out.println("\n\nADMIN LOGIN");
        System.out.println("username = admin, password = admin");
        do{
            System.out.print("username : ");
            username = read.nextLine();
            System.out.print("password : ");
            password = read.nextLine();
            if (!username.equals("admin") || !password.equals("admin")){
                System.out.println("username atau password salah\n");
            }
        }while (!username.equals("admin") || !password.equals("admin"));
        System.out.println("BERHASIL LOGIN");


        do{
            System.out.println("\n\n~ADMIN MAIN MENU~");
            System.out.println("1. Posting Pengumuman");
            System.out.println("2. Registrasi Pemain & Organizer");
            System.out.println("3. Block Akun Pemain & Organizer");
            System.out.println("4. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    adminPengumuman(admin);
                    break;
                }
                case 2 : {
                    adminRegistAcc(organizers, pemains);
                    break;
                }
                case 3 : {
                    adminBlockAcc(organizers, pemains);
                    break;
                }
                case 4 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
            }

        }while (jawab!=4);
    }

    private static void adminPengumuman(Admin admin) {
        Scanner read = new Scanner(System.in);
        String pengumuman;
        System.out.println("\nMasukkan Pengumuman yang ingin dibuat [Enter untuk mengakhiri]: ");
        pengumuman = read.nextLine();
        admin.setPengumuman(pengumuman);
        System.out.println("Pengumuman Berhasil Dibuat");
    }

    private static void adminRegistAcc(ArrayList<Organizer> organizers, ArrayList<Pemain> pemains) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do {
            System.out.println("\nREGISTRASI");
            System.out.println("1. Untuk Organizer");
            System.out.println("2. Untuk Pemain");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();
            switch (jawab){
                case 1 : {
                    int pilihan;
                    if (organizers.size()>0){
                        //View All Data Organizer in Registrasi
                        viewOrganizer(organizers);

                        //choose data
                        do{
                            System.out.print("Masukkan pilihan Organizer [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>organizers.size());

                        //Boolean to Registrasi
                        pilihan=pilihan-1;
                        Organizer o =organizers.get(pilihan);
                        o.setRegis(true);
                        System.out.println("\nREGISTRASI BERHASIL");
                    } else System.out.println("\nTIDAK ADA ORGANIZER");
                    break;
                }
                case 2 : {
                    int pilihan;
                    if (pemains.size()>0){
                        //View All Data in Registrasi
                        viewPemain(pemains);

                        //choose data
                        do{
                            System.out.print("Masukkan pilihan Pemain [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>pemains.size());

                        //ubah boolean menjadi regis
                        pilihan=pilihan-1;
                        Pemain o =pemains.get(pilihan);
                        o.setRegis(true);
                        System.out.println("\nREGISTRASI BERHASIL");
                    } else System.out.println("\nTIDAK ADA PEMAIN");
                    break;
                }
                case 3 : {
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada\n");
                    break;
                }
            }
        }while (jawab!=3);
    }

    private static void adminBlockAcc(ArrayList<Organizer> organizers, ArrayList<Pemain> pemain) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do {
            System.out.println("\nBLOCK ACCOUNT");
            System.out.println("1. Organizer");
            System.out.println("2. Pemain");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();
            switch (jawab){
                case 1 : {
                    int pilihan;
                    if (organizers.size()>0){
                        viewOrganizer(organizers);

                        //choose data
                        do{
                            System.out.print("Masukkan pilihan Organizer [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>organizers.size());

                        //ubah boolean menjadi block
                        pilihan=pilihan-1;
                        Organizer o =organizers.get(pilihan);
                        o.setBlock(false);
                        System.out.println("\nBLOCK BERHASIL");
                    } else System.out.println("\nTIDAK ADA ORGANIZER");
                    break;
                }
                case 2 : {
                    int pilihan;
                    if (pemain.size()>0){
                        viewPemain(pemain);

                        do{
                            System.out.print("Masukkan pilihan Pemain [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("Wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>pemain.size());

                        pilihan=pilihan-1;
                        Pemain o =pemain.get(pilihan);
                        o.setBlock(false);
                        System.out.println("\nBLOCK BERHASIL");
                    } else System.out.println("\nTIDAK ADA PEMAIN");
                    break;
                }
                case 3 : {
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada\n");
                    break;
                }
            }
        }while (jawab!=3);
    }

    private static void LoginOrganizers(ArrayList<Organizer> organizers, ArrayList<Events> event) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do {
            System.out.println("\nPunya akun? ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("Wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    //create acc
                    //input username
                    String username, password;
                    boolean usernameC = false;
                    do {
                        do {
                            usernameC = false;
                            System.out.print("\nMasukkan username [15]: ");
                            username = read.nextLine();
                        }while (username.length()>15 || username.length()<1);

                        for (Organizer o : organizers){
                            if (o.getUsername().equals(username)){
                                usernameC = true;
                                System.out.println("Username sudah digunakan");
                            }
                        }

                    }while (usernameC);

                    //input password
                    do {
                        System.out.print("Masukkan password [15]: ");
                        password = read.nextLine();
                    }while (password.length()>15 || password.length()<1);

                        organizers.add(new Organizer(username, password));
                    System.out.println("Akun berhasil dibuat! Silahkan tunggu verifikasi admin");
                    break;
                }
                case 2 : {
                    String username, password;
                    boolean status = false, benar = false;
                    //login
                    if (organizers.size()>0){
                        System.out.println("\n DATA LOGIN");
                        System.out.println("| username        | password        |");
                        for (Organizer o : organizers){
                            System.out.printf("| %15s | %15s |\n", o.getUsername(), o.getPassword());
                        }

                        System.out.print("\nmasukkan username [15]: ");
                        username = read.nextLine();
                        System.out.print("masukkan password [15]: ");
                        password = read.nextLine();

                        for (Organizer o : organizers){
                            if (o.getUsername().equals(username)&&o.getPassword().equals(password)){
                                benar = true;
                                if (o.isBlock() && o.isRegis()){
                                    status = true;
                                }
                            }
                        }
                        if (!benar){
                            System.out.println("username atau password salah");
                        } else if (!status){
                            System.out.println("account Diblokir atau belum diregistrasi");
                        }else {
                            //menu yg bisa dilakukan organizer
                            MenuOrganizer(organizers, event,username);
                        }

                    }else {
                        System.out.println("\n\nBELUM ADA AKUN YANG DIBUAT");
                    }
                    break;
                }
                case 3 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
            }
        }while (jawab!=3);
    }

    private static void MenuOrganizer(ArrayList<Organizer> organizers, ArrayList<Events> event, String username) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do{
            System.out.println("\n\n~ORGANIZER MAIN MENU~");
            System.out.println("1. View Semua Event");
            System.out.println("2. View MyEvent");
            System.out.println("3. Buat Event");
            System.out.println("4. Batalkan atau sudahi Event");
            System.out.println("5. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 6;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    if (event.size()>0){
                        viewSemuaEvents(event);
                    }else System.out.println("\nBELUM ADA EVENT YANG DIBUAT");
                    break;
                }
                case 2 : {
                    int counter=0;

                    for (Events e : event){
                        if (e.getPembuat().equals(username)){
                            counter++;
                        }
                    }
                    if (counter>0){
                        viewEventsOrganizer(event, username);
                    }else System.out.println("\nBELUM ADA EVENT YANG DIBUAT");
                    break;
                }
                case  3 : {
                    String jenisOlahraga, level, tempat, nama;
                    int tanggal, bulan, tahun, jumlahMin, jumlahMax, biaya;

                    //nama
                    do{
                        System.out.print("Masukkan nama event [maksimal 20 character] : ");
                        nama = read.nextLine();
                    }while (nama.length()<1 || nama.length()>20);

                    //jenis olahraga
                    do {
                        System.out.print("Jenis Olahraga [Badminton | Tenis | Renang | GYM] : ");
                        jenisOlahraga = read.nextLine();
                    }while (!jenisOlahraga.equals("Badminton") && !jenisOlahraga.equals("Tenis") && !jenisOlahraga.equals("Renang") && !jenisOlahraga.equals("GYM"));

                    //level
                    do {
                        System.out.print("Level Kesusahan [Easy | Medium | Hard] : ");
                        level = read.nextLine();
                    }while (!level.equals("Easy") && !level.equals("Medium") && !level.equals("Hard"));

                    //tanggal
                    do{
                        System.out.print("Masukkan tanggal kegiatan [angka| 1-31] : ");
                        try{
                            tanggal = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            tanggal=0;
                        }
                        dummy=read.nextLine();
                    }while (tanggal<1 || tanggal>31);

                    //bulan
                    do{
                        System.out.print("Masukkan bulan kegiatan [angka | 1-12] : ");
                        try{
                            bulan = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            bulan=0;
                        }
                        dummy=read.nextLine();
                    }while (bulan<1 || bulan>12);

                    //tahun
                    do{
                        System.out.print("Masukkan tahun kegiatan [angka <3000]: ");
                        try{
                            tahun = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            tahun=0;
                        }
                        dummy=read.nextLine();
                    }while (tahun<2000 || tahun>3000);

                    //tempat
                    do{
                        System.out.print("Masukkan nama tempat [maksimal 20 character] : ");
                        tempat = read.nextLine();
                    }while (tempat.length()<1 || tempat.length()>20);

                    //jumlahMin
                    do{
                        System.out.print("Masukkan jumlah pemain minimal : ");
                        try{
                            jumlahMin = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            jumlahMin=0;
                        }
                        dummy=read.nextLine();
                    }while (jumlahMin<1 || jumlahMin>999);

                    //jumlahMax
                    do{
                        System.out.print("Masukkan jumlah pemain maksimal : ");
                        try{
                            jumlahMax = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            jumlahMax=0;
                        }
                        dummy=read.nextLine();
                    }while (jumlahMax<jumlahMin || jumlahMax>999);

                    //biaya
                    do{
                        System.out.print("Masukkan biaya pendaftaran : ");
                        try{
                            biaya = read.nextInt();
                        }catch (Exception e){
                            System.out.print("wrong input\n");
                            biaya=0;
                        }
                        dummy=read.nextLine();
                    }while (biaya<1 || biaya>99999999);

                    event.add(new Events(nama, username, jenisOlahraga, level, tanggal, bulan, tahun, tempat, jumlahMin, jumlahMax, biaya));
                    System.out.println("\nEvent BERHASIL DIBUAT");
                    break;
                }
                case 4 : {
                    String batal;
                    int counter=0;
                    boolean ada = false;

                    for (Events k : event){
                        if (k.getPembuat().equals(username)){
                            counter++;
                        }
                    }

                    if (counter>0){
                        viewEventsOrganizer(event, username);
                        System.out.print("nama event yang mau dibatalkan / diselesaikan : ");
                        batal = read.nextLine();

                        for (Events k : event){
                            if (k.getPembuat().equals(username) && k.getNama().equals(batal)){
                                ada=true;
                                k.setStatus(false);
                                System.out.println("\nEvent Berhasil dibatalkan / diselesaikan");
                            }
                        }
                        if (!ada){
                            System.out.println("\nEvent Gak Ada");
                        }
                    } else System.out.println("\nBelum Membuat Event");
                    break;
                }
                case 5 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
            }
        }while (jawab!=5);
    }

    private static void viewEventsOrganizer(ArrayList<Events> event, String username) {
        int no = 1;

        System.out.println("\n\nMyEvent");
        System.out.println("===============================================================================================================================================================================");
        System.out.println("| NO  | NAMA                 | PEMBUAT         | JENIS     | LEVEL | TANGGAL        | TEMPAT               | MIN PESERTA | MAX PESERTA | PESERTA SAAT INI | BIAYA    | STATUS |");
        System.out.println("===============================================================================================================================================================================");

        for (Events k : event){
            if (k.getPembuat().equals(username)){
                System.out.printf("| %-3d | %-20s | %-15s | %-9s | %-5s | %-2d - %-2d - %-4d | %-20s | %-11d | %-11d | %-16d | %-8d |", no, k.getNama(), k.getPembuat(), k.getJenisOlahraga(), k.getLevel(), k.getTanggal(), k.getBulan(), k.getTahun(), k.getTempat(), k.getJumlahMin(), k.getJumlahMax(), k.getJumlahSekarang(), k.getBiaya());
                if (k.isStatus()){
                    System.out.println(" OPEN   |");
                } else System.out.println(" CLOSE  |");

                no++;
            }
        }
        System.out.println("===============================================================================================================================================================================");

    }

    private static void viewSemuaEvents(ArrayList<Events> event) {
        int no = 1;

        System.out.println("\n\nSEMUA EVENT");
        System.out.println("===============================================================================================================================================================================");
        System.out.println("| NO  | NAMA                 | PEMBUAT         | JENIS     | LEVEL | TANGGAL        | TEMPAT               | MIN PESERTA | MAX PESERTA | PESERTA SAAT INI | BIAYA    | STATUS |");
        System.out.println("===============================================================================================================================================================================");

        for (Events k : event){
            System.out.printf("| %-3d | %-20s | %-15s | %-9s | %-5s | %-2d - %-2d - %-4d | %-20s | %-11d | %-11d | %-16d | %-8d |", no, k.getNama(), k.getPembuat(), k.getJenisOlahraga(), k.getLevel(), k.getTanggal(), k.getBulan(), k.getTahun(), k.getTempat(), k.getJumlahMin(), k.getJumlahMax(), k.getJumlahSekarang(), k.getBiaya());
            if (k.isStatus()){
                System.out.println(" OPEN   |");
            } else System.out.println(" CLOSE  |");
            no++;
        }
        System.out.println("===============================================================================================================================================================================");

    }

    private static void viewOrganizer(ArrayList<Organizer> organizers) {
        int no = 1;

        System.out.println("\n\nDATA PEMAIN");
        System.out.println("==============================================");
        System.out.println("| NO  | USERNAME        | REGISTRASI | BLOCK |");
        System.out.println("==============================================");
        for (Organizer o : organizers){
            System.out.printf("| %-3d | %-15s |", no, o.getUsername());
            if (o.isRegis()){
                System.out.print(" YA         |");
            } else System.out.print(" TIDAK      |");
            if (o.isBlock()){
                System.out.println(" TIDAK |");
            } else System.out.println(" YA    |");
            no++;
        }
        System.out.println("==============================================");
    }

    private static void LoginPemain(ArrayList<Pemain> pemains, ArrayList<Events> event) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do {
            System.out.println("\nMemiliki akun? ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 5;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    //create acc
                    //input username
                    String username, password;
                    boolean usernameC = false;
                    do {
                        do {
                            usernameC = false;
                            System.out.print("\nmasukkan username [15]: ");
                            username = read.nextLine();
                        }while (username.length()>15 || username.length()<1);

                        for (Pemain p : pemains){
                            if (p.getUsername().equals(username)){
                                usernameC = true;
                                System.out.println("username sudah digunakan");
                            }
                        }
                    }while (usernameC);

                    //input password
                    do {
                        System.out.print("masukkan password [15]: ");
                        password = read.nextLine();
                    }while (password.length()>15 || password.length()<1);

                    pemains.add(new Pemain(username, password));
                    System.out.println("Berhasil membuat akun silahkan tunggu verifikasi admin untuk melanjutkan");
                    break;
                }
                case 2 : {
                    String username, password;
                    boolean status = false, benar = false;
                    //login
                    if (pemains.size()>0){
                        System.out.println("\n DATA LOGIN");
                        System.out.println("| username        | password        |");
                        for (Pemain p : pemains){
                            System.out.printf("| %15s | %15s |\n", p.getUsername(), p.getPassword());
                        }

                        System.out.print("\nmasukkan username [15]: ");
                        username = read.nextLine();
                        System.out.print("masukkan password [15]: ");
                        password = read.nextLine();

                        for (Pemain p : pemains){
                            if (p.getUsername().equals(username) && p.getPassword().equals(password)){
                                benar = true;
                                if (p.isBlock() && p.isRegis()){
                                    status = true;
                                }
                            }
                        }
                        if (!benar){
                            System.out.println("username atau password salah");
                        } else if (!status){
                            System.out.println("account terblock atau belum diregistrasi");
                        }else {
                            //menu yg bisa dilakukan organizer
                            MenuPemain(pemains, event, username);
                        }

                    }else {
                        System.out.println("\n\nBELUM ADA AKUN YANG DIBUAT");
                    }
                    break;
                }
                case 3 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
            }
        }while (jawab!=3);
    }

    private static void MenuPemain(ArrayList<Pemain> pemains, ArrayList<Events> event, String username) {
        Scanner read = new Scanner(System.in);
        int jawab;

        do{
            System.out.println("\n\nMENU PEMAIN");
            System.out.println("1. View Semua Event");
            System.out.println("2. Daftar Event");
            System.out.println("3. Batalkan Pendaftaran");
            System.out.println("4. Memberi Lencana");
            System.out.println("5. Level & Lencana Saya");
            System.out.println("6. EXIT");
            System.out.print("Pilihan : ");
            try{
                jawab = read.nextInt();
            }catch (Exception e){
                System.out.print("wrong input");
                jawab = 7;
            }
            String dummy=read.nextLine();

            switch (jawab){
                case 1 : {
                    if (event.size()>0){
                        viewSemuaEvents(event);
                    }else System.out.println("\nBELUM ADA Event YANG DIBUAT");
                    break;
                }
                case 2 : {
                    if (event.size()>0){
                        int pilihan;
                        //view data
                        viewSemuaEvents(event);

                        //choose data
                        do{
                            System.out.print("Masukkan pilihan Event [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>event.size());

                        pilihan=pilihan-1;
                        Events e = event.get(pilihan);
                        if (!e.isStatus()){
                            System.out.println("\nEVENT SUDAH DITUTUP");
                        } else if (e.getPemain().contains(username)){
                            System.out.println("\nANDA SUDAH TERDAFTAR");
                        } else if (e.isPenuh()){
                            System.out.println("\nEVENT SUDAH PENUH");
                        } else {
                            for (Pemain p : pemains){
                                if (p.getUsername().equals(username)){
                                    p.TambahLevel();
                                }
                            }
                            e.TambahPemain(username);
                            System.out.println("\nPENDAFTARAN BERHASIL");
                        }
                    }else System.out.println("\nBELUM ADA EVENT YANG DIBUAT");
                    break;
                }
                case 3 : {
                    String batal;
                    int counter=0;
                    boolean ada = false;

                    for (Events e : event){
                        if (e.getPemain().contains(username)){
                            counter++;
                        }
                    }

                    if (counter>0){
                        viewEventPemain(event, username);

                        int year = Calendar.getInstance().get(Calendar.YEAR);
                        int month = Calendar.getInstance().get(Calendar.MONTH);
                        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        month=month+1;

                        System.out.print("nama event yang mau dibatalkan / diselesaikan : ");
                        batal = read.nextLine();

                        //tanggal sekarng
                        for (Events e : event){
                            if (e.getPemain().contains(username) && e.getNama().equals(batal) && e.isStatus()){
                                ada=true;
                                for (Pemain p : pemains){
                                    if (p.getUsername().equals(username) && year<e.getTahun()){
                                        p.KurangLevel();
                                        e.KurangiPemain(username);
                                        System.out.println("\nEvent Berhasil dibatalkan");
                                    } else if (p.getUsername().equals(username) && year==e.getTahun()){
                                        if (month<e.getBulan()){
                                            p.KurangLevel();
                                            e.KurangiPemain(username);
                                            System.out.println("\nEvent Berhasil dibatalkan");
                                        } else if (month==e.getBulan()){
                                            if (day<e.getTanggal()){
                                                p.KurangLevel();
                                                e.KurangiPemain(username);
                                                System.out.println("\nEvent Berhasil dibatalkan");
                                            } if (day>=e.getTanggal())System.out.println("\nMelebihi Tanggal Pembatalan");
                                        } if (month>e.getBulan())System.out.println("\nMelebihi Tanggal Pembatalan");
                                    } if (year>e.getTahun())System.out.println("\nMelebihi Tanggal Pembatalan");
                                }
                            }
                        }
                        if (!ada){
                            System.out.println("\nEvent Tidak Ada atau Sudah Ditutup");
                        }
                    }else System.out.println("\nBelum Membuat Event");

                    break;
                }
                case 4 : {
                    if (pemains.size()>1){
                        int pilihan;
                        String lencana;

                        viewPemain(pemains);

                        do{
                            System.out.print("Masukkan pilihan Pemain [angka nomer urut] : ");
                            try{
                                pilihan = read.nextInt();
                            }catch (Exception e){
                                System.out.print("wrong input");
                                pilihan=0;
                            }
                            dummy=read.nextLine();
                        }while (pilihan<1 || pilihan>pemains.size());

                        do {
                            System.out.print("Tulis Lencana [10 char]: ");
                            lencana = read.nextLine();
                        }while (lencana.length()<1 || lencana.length()>10);

                        pilihan=pilihan-1;
                        Pemain p = pemains.get(pilihan);
                        if (p.getUsername().equals(username)){
                            System.out.println("\nTidak Dapat Memberi Lencana Pada Diri Sendiri");
                        } else {
                            p.TambahLencana(lencana);
                            System.out.println("\nLencana Berhasil Ditambahkan");
                        }

                    }else System.out.println("\nBELUM ADA PEMAIN LAIN");
                    break;
                }
                case 5 : {
                    for (Pemain p : pemains){
                        if (p.getUsername().equals(username)){
                            System.out.println("\n\nLEVEL SAYA");
                            System.out.println("==========");
                            System.out.printf ("    %2d    ",p.getLevel());
                            System.out.println("\n\nLENCANA SAYA");
                            System.out.println("============");
                            for (int i = 0; i < p.getLencana().size(); i++){
                                System.out.println(p.getLencana().get(i));
                            }
                        }
                    }
                    break;
                }
                case 6 : {
                    System.out.println("\n");
                    break;
                }
                default : {
                    System.out.println("\nPilihan tidak ada");
                    break;
                }
            }
        }while (jawab!=6);
    }

    private static void viewEventPemain(ArrayList<Events> event, String username) {
        int no = 1;

        System.out.println("\n\nMyEvent");
        System.out.println("===============================================================================================================================================================================");
        System.out.println("| NO  | NAMA                 | PEMBUAT         | JENIS     | LEVEL | TANGGAL        | TEMPAT               | MIN PESERTA | MAX PESERTA | PESERTA SAAT INI | BIAYA    | STATUS |");
        System.out.println("===============================================================================================================================================================================");

        for (Events e : event){
            if (e.getPemain().contains(username)){
                System.out.printf("| %-3d | %-20s | %-15s | %-9s | %-5s | %-2d - %-2d - %-4d | %-20s | %-11d | %-11d | %-16d | %-8d |", no, e.getNama(), e.getPembuat(), e.getJenisOlahraga(), e.getLevel(), e.getTanggal(), e.getBulan(), e.getTahun(), e.getTempat(), e.getJumlahMin(), e.getJumlahMax(), e.getJumlahSekarang(), e.getBiaya());
                if (e.isStatus()){
                    System.out.println(" OPEN   |");
                } else System.out.println(" CLOSE  |");
                no++;
            }
        }
        System.out.println("===============================================================================================================================================================================");

    }

    private static void HeaderLogin(Admin admin) {
        System.out.println("EVENT APPLICATION");
        System.out.println("=================\n");

        System.out.println("Annaouncement :");
        if (admin.getPengumuman().length()<2){
            System.out.print("BELUM ADA PENGUMUMAN");
        }else{
            System.out.println(admin.getPengumuman());
        }
        System.out.println("\n");

        System.out.println("LOGIN YOUR ACCOUNT");
        System.out.println("1. Admin");
        System.out.println("2. Organizer");
        System.out.println("3. Pemain");
        System.out.println("4. EXIT");
        System.out.print("Pilihan : ");
    }

    private static void saveData(ArrayList<Organizer> organizers, ArrayList<Pemain> pemains, ArrayList<Events> event, Admin admin, String fileOrganizers, String filePemains, String fileEvents, String fileAdmin) {
        try
        {

            ObjectOutputStream outOrganizer = new ObjectOutputStream(new FileOutputStream(fileOrganizers));
            outOrganizer.writeObject(organizers);

            ObjectOutputStream outPemains = new ObjectOutputStream(new FileOutputStream(filePemains));
            outPemains.writeObject(pemains);

            ObjectOutputStream outKegiatan = new ObjectOutputStream(new FileOutputStream(fileEvents));
            outKegiatan.writeObject(event);

            ObjectOutputStream outAdmin = new ObjectOutputStream(new FileOutputStream(fileAdmin));
            outAdmin.writeObject(admin);

            System.out.println("\nDONE SAVE");

        }
        catch(IOException ex)
        {
            System.out.println("Error SAVE DATA");
        }
    }

    private static void viewPemain(ArrayList<Pemain> pemains) {
        int no = 1;

        System.out.println("\n\nDATA PEMAIN");
        System.out.println("==============================================");
        System.out.println("| NO  | USERNAME        | REGISTRASI | BLOCK |");
        System.out.println("==============================================");
        for (Pemain p : pemains){
            System.out.printf("| %-3d | %-15s |", no, p.getUsername());
            if (p.isRegis()){
                System.out.print(" YA         |");
            } else System.out.print(" TIDAK      |");
            if (p.isBlock()){
                System.out.println(" TIDAK |");
            } else System.out.println(" YA    |");
            no++;
        }
        System.out.println("==============================================");
    }



}






