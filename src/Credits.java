package com.example.touchme;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Credits extends Activity{
public String[] fakta;
public int random;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		Random rand = new Random();
		fakta = new String[10];
		fakta[0]= "'Diperkirakan 6.4 juta ton sampah masuk ke laut setiap tahunnya di seluruh dunia, dan 80% merupakan plastik; yang membuat lebih dari 1 juta binatang laut mati akibat plastik setiap tahunnya'";
		fakta[1]= "'Supermarket di seluruh dunia memberikan lebih dari 17 milyar kantong plastik setiap tahunnya'";
		fakta[2]= "'Setiap tahun diperlukan 12 juta barel minyak serta 14 juta pohon untuk membuat semua plastik'";
		fakta[3]= "'Data tahun 2006,  Sulsel dengan jumlah penduduk 7,6 juta menghasilkan sampah plastik 1,3 miliar  setiap tahun'";
		fakta[4]= "'Setiap hari sampah kertas di dunia berasal dari 27.000 batang kayu'";
		fakta[5]= "'Aneka jenis tisu diproduksi dari serat kayu dan tidak dapat didaur ulang. Gunakan lap/serbet yang bisa dipakai berulang kali'";
		fakta[6]= "'Di Cina, sampah telah diolah menjadi listrik dengan hitungan kasar 1 ton sampah/jam menghasilkan listrik 31.8 kWh dengan biaya investasi 2.5 juta $ (Rp 24 M)/MWh atau 79 ribu $/ton sampah'";
		fakta[7]= "'Indonesia berpeluang menghasilkan listrik dari sampah; dengan 11.330 ton sampah/hr dan diperkirakan dapat menjadi listrik sebesar 566.6 MWh'";
		fakta[8]= "";
		fakta[9]= "";
		
		TextView fakta = (TextView)findViewById(R.id.fakta);
		random = rand.nextInt(8);
		String teks = this.fakta[random];
		fakta.setText(teks);
		
	}
	

}
