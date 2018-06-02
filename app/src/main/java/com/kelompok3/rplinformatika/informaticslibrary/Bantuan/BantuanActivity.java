package com.kelompok3.rplinformatika.informaticslibrary.Bantuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.kelompok3.rplinformatika.informaticslibrary.Adapter.ExpandableListAdapter;
import com.kelompok3.rplinformatika.informaticslibrary.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BantuanActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
     @Override
     protected void onCreate(Bundle saveInstanceState) {
         super.onCreate(saveInstanceState);
         setContentView(R.layout.activity_bantuan);

         listView = (ExpandableListView)findViewById(R.id.lvExp);
         initData();
         listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
         listView.setAdapter(listAdapter);
     }

    private void initData() {
         listDataHeader = new ArrayList<>();
         listHash = new HashMap<>();

         listDataHeader.add("Cari Buku/Jurnal");
         listDataHeader.add("Download Buku/Jurnal");

         List<String>cariBj = new ArrayList<>();
         cariBj.add("Untuk mencari buku/jurnal, " +
                    "anda harus membuka halaman cari buku/jurnal, " +
                    "dengan menenkan tombol cari buku. Disana anda " +
                    "dapat melihat list buku/jurnal yang telah kami sediakan.");


         List<String>downloadBj = new ArrayList<>();
         downloadBj.add("Untuk mendownload buku/jurnal, " +
                        "anda harus membuka halaman detail buku/jurnal. " +
                        "Setelah itu anda menenkan tombol download, " +
                        "maka buku/jurnal yang anda pilih akan terdownload.");

         listHash.put(listDataHeader.get(0),cariBj);
         listHash.put(listDataHeader.get(1),downloadBj);

    }

}
