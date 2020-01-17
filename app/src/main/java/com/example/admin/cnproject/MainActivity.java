package com.example.admin.cnproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        Itemdata x = new Itemdata();
//         x.setdata("HeadSet 1.0","550","https://assets.myntassets.com/dpr_2,q_60,w_210,c_limit,fl_progressive/assets/images/2004043/2017/8/1/11501570663883-JBL-Unisex-Headphones-5601501570663797-1.jpg","Sony","headphones");
/////            x.setdata(name,price,url,company,catagory);
//
//        x.setdata("Touch Mouse t12","9900","http://www.shophive.com/media/catalog/product/cache/1/small_image/165x/9df78eab33525d08d6e5fb8d27136e95/m/i/microsoft_touch_mouse_white_4.jpg","Microsoft","mouse");
//
//        x.setdata("USB PRO 32GN","1500","https://static-01.daraz.pk/p/3cf4bb4fa1e4b287cd40cddae8f7e869.jpg","HP","storage");
//        db.collection("Items").add(x);
//        x.setdata("Gaming Mouse r350","5500","https://www.bhphotovideo.com/images/images2500x2500/logitech_910_005091_g703_lightspeed_wireless_gaming_1374580.jpg","Logitech","mouse");
//        db.collection("Items").add(x);
//        x.setdata("Mechanical Keyboard P50","9000","https://images-na.ssl-images-amazon.com/images/I/61EDQaJph3L._SX425_.jpg","Logitech","keyboard");
//        db.collection("Items").add(x);
//        x.setdata("Super base 101","3500","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/5748/5748828cv11d.jpg","Sony","sound");
//        db.collection("Items").add(x);
//        x.setdata("Gamepad Generic","900","https://images-na.ssl-images-amazon.com/images/I/61Pb-r646aL._SX425_.jpg","Logitech","gamepad");
//        db.collection("Items").add(x);
//        x.setdata("Gamer hand 2.0","1600","https://rukminim1.flixcart.com/image/832/832/j3dbtzk0/gamepad/motion-detection-via-a-three-axis-gyroscope/h/f/h/sony-dualshock-4-wireless-controller-for-playstation-4-black-v2-original-imaeugk5grhpffxd.jpeg?q=70","Sony","gamepad");
//        db.collection("Items").add(x);
//        x.setdata("Speakers 4.1","2100","https://rukminim1.flixcart.com/image/704/704/speaker/desktop-speakers/h/9/p/creative-inspire-m4500-original-imad7s7ccca2afkz.jpeg?q=70","Creative","sound");
//        db.collection("Items").add(x);
//        x.setdata("Ultra Base Pill","1800","https://www.beatsbydre.com/content/dam/beats/web/pdp/beats-pill-plus/hero_mobile/pillplus_BLACK_INTRO_MOBILE_retina_V2.png","Creative","sound");
//        db.collection("Items").add(x);
//        x.setdata("Buffer Mic","700","https://www.bhphotovideo.com/images/images2000x2000/Sony_FV100_F_V100_Vocal_Mic_1463589351000_225513.jpg","Sony","sound");
//        db.collection("Items").add(x);
//
//        x.setdata("SteelSeries Ikari Laser Gaming Mouse","5700","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/s/t/steelseries-ikarilaser-3.jpg","SteelSeries","mouse");
//        db.collection("Items").add(x);
//        x.setdata("SteelSeries Iron Lady","5900","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/l/a/lady_ikari_laser.jpg","SteelSeries","mouse");
//        db.collection("Items").add(x);
//        x.setdata("Logitech Gaming Keyboard G510","9050","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/g/1/g150-3.jpg","Logitech","keyboard");
//        db.collection("Items").add(x);
//        x.setdata("SteelSeries Apex RAW Gaming Keyboard","9900","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/s/t/steelseries_apex_raw_gaming_keyboard.jpg","Steel series","keyboard");
//        db.collection("Items").add(x);
//        x.setdata("SteelSeries Spectrum 4xb Gaming Headset","7500","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/s/t/steelseries_spectrum_4xb_gaming_headset.jpg","Steel series","audio");
//        db.collection("Items").add(x);
//        x.setdata("Logitech Ultimate Ears 4000 Headphones","1200","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/l/o/logitech_ultimate_ears_4000_headphones.jpeg","Logitech","audio");
//        db.collection("Items").add(x);
//        x.setdata("Logitech Gaming Mouse G100","3100","http://www.shophive.com/media/catalog/product/cache/1/image/600x600/9df78eab33525d08d6e5fb8d27136e95/g/1/g100.jpg","Logitech","mouse");
//        db.collection("Items").add(x);
    }
    void open(View view)
    {
        Intent x = new Intent(this,login.class);
        startActivity(x);
    }
    void openlogin(View view)
    {
        Intent x = new Intent(this,login.class);
        startActivity(x);
    }
    void openca(View view)
    {
        Intent x = new Intent(this,createaccount.class);
        startActivity(x);
    }
}
