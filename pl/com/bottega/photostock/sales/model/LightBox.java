package pl.com.bottega.photostock.sales.model;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz.paszkowski on 12.03.2016.
 */
public class LightBox {

    private String name; //tu sa pola(fields)
    private Client owner;
    private List<Picture> items = new ArrayList<>(); //[]-ozanacza tablice
    private boolean closed = false;
    private List<LightBox> lightBoxes= new ArrayList<>();
    private String number;
    private String company;

    public LightBox(Client owner) {
        this.owner = owner;
    }
    public LightBox(String number, Client owner) {
        this.owner = owner;
        this.number = number;
    }
    public LightBox(String number, Client owner, String company) {
        this.owner = owner;
        this.number = number;
        this.company = company;
    }

    public LightBox(Client owner, String name) {
        this.owner = owner;
        this.name = name;
    }
    public LightBox(LightBox lightBox) { //konstruktor kopiujący// TODO
        this.number = lightBox.number + ".1";
        this.owner = lightBox.owner;
        this.items = lightBox.items;
    }

    public void close(){
        this.closed = true;
    }


    public void changeName(String newName){
        validate();
        this.name = newName;
    }

    /*
    public - modyfikator dostępu
    static - opcjonalnie aby metoda na rzeczklasy a nie obiektu
    void - typ zwracalny, bez zracania
    remove - nazewnik metody
    () - parametry
        Picture - typ
        pictureToRemove - nazewnik parametu
    */

    public void add (Picture pictureToAdd)throws IllegalStateException , IllegalArgumentException {
        validate();
        if (items.contains(pictureToAdd))
            throw new IllegalArgumentException("already contains ");
        if (!pictureToAdd.isAvailable())
            throw new ProductNotAvailableException("tring to reseve", pictureToAdd.getNumber(), LightBox.class);
        items.add(pictureToAdd);
    }

    public void remove(Picture pictureToRemove){
        validate();
        boolean removed = items.remove(pictureToRemove);
        if (!removed)
            throw new IllegalArgumentException("does not contain ");
    }

    public String getName() {
        return name;
    }

    public int getItemsCount() {
        return items.size();
    }

    private void validate(){
        if (closed)                        //!-znaczy zaprzecenie
            throw new IllegalStateException("closed!");
        if (!Client.isActive())
            throw new IllegalStateException("owner is not active");
    }

    public static void displayLbxPictures(ArrayList<LightBox> lightBoxes) {
        int n = 1;

        for (LightBox lbx : lightBoxes){
            System.out.println("\n"+ n++ + "." + lbx.getName()+ " - owner: " + lbx.getOwnerName());
            int nn = 1;
            for (Picture pic : lbx.getPictures()){

                if (pic.isAvailable()){
                    System.out.println(nn +" "+ pic.getNumber()+ " | " + pic.getPrice());
                }
                else{
                    System.out.println(nn + " "+  pic.getNumber()+ " | " + pic.getPrice() + " "+ "not available");
                }
                nn++ ;
            }
        }
    }

    public List<Picture> getPictures() {
        return items;
    }
    public String getOwnerName(){
        return this.owner.getOwnerName();
    }
    public String getNumber(){
        return  number;
    }
    public String getCompany(){
        return company;
    }
    public Client getOwner(){
        return owner;
    }


}


/*
    public void add2(Picture pictureToAdd)throws IllegalStateException , IllegalArgumentException {

        // sprawdza czy item zawiera juz ten pictur
       // for (int cursor =0; cursor < items.length; cursor++){
        for (Picture pic : items)  {         //  <- pętla automatyczna
            //Picture pic = items[cursor];
            if (pic != null){
                String  nr1 = pic.getNumber();
                String  nr2 = pictureToAdd.getNumber();

                if (nr1.equals(nr2)){
                    throw new IllegalArgumentException("Lightbox already contains pictre " + pictureToAdd.getNumber());
                }
            }
            //
            //cursor++; // !!!!!!! zwiększ numer kursora
        }
        boolean added = false;  // flaga :)

        //dodaje picture jeżeli znajdzie puste miejsce
        for (int cursor =0; cursor < items.length; cursor++){
            Picture reference = items[cursor];
            if (reference == null){
                items[cursor] = pictureToAdd;
                added = true;
                break; //break !!!!!!!!!
            }
        }
        if (added) {
            throw new IllegalStateException("Lightbox is full !!!");
        }
    }
    */