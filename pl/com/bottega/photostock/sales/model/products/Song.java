package pl.com.bottega.photostock.sales.model.products;

import pl.com.bottega.photostock.sales.model.AbstractProduct;
import pl.com.bottega.photostock.sales.model.Money;

/**
 * Created by bartosz.paszkowski on 07.04.2016.
 */
public class Song extends AbstractProduct{




    public enum File_Extension {
        MP3("MP3"), WAV("WAV"), WMA("WMA");

        private String fileExtension;
        File_Extension(String fileExtension){
            this.fileExtension = fileExtension;
        }
    }

    public enum Channel {
        STEREO("2.0"), FIVEONE("5.1"), SEVENONE("7.1");

        private String channel;
        Channel(String channel) {
            this.channel = channel;
        }
    }


    private String name;
    private String duration;
    //private String[] tags;
    private String genre;
    //private Money price;
    //private boolean isAvailable;
    private Channel channel;
    private File_Extension fileExtension;

    public Song (String number, String name, String duration, String[] tags,
                 String genre, Money price, boolean isAvailable, Channel channel, File_Extension fileExtension){
        super(number,price,tags,isAvailable);
        this.name = name;
        this.duration = duration;
        this.tags = tags;
        this.genre = genre;
        this.price = price;
        this.isAvailable = isAvailable;
        this.channel = channel;
        this.fileExtension = fileExtension;
    }
    public Song(String title, Money price, String[] tags, boolean b) {
        super(title, price, tags, b);
    }

    @Override
    public String[] export() {
        return new String[0];
    }
}
