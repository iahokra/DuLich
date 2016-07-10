package com.thinh.dulich;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

import draw.Direction;
import model.DanhSachDiaDiem;
import model.DiaDiem;

//thử lần 2
public class ViTriActivity extends AppCompatActivity {

    private GoogleMap mMap;
    ProgressDialog progress;
    double kinhdo,vido;
    ArrayList<DiaDiem> dsdiadiem=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_tri);
        addControls();
        addEvents();
        //layds();
        hienthihientai();

    }

   // private void layds() {


    //}

    private void hienthihientai() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                kinhdo=location.getLatitude();
                vido=location.getLongitude();
                LatLng sieuthi = new LatLng(location.getLatitude(), location.getLongitude());
                Marker maker= mMap.addMarker(new MarkerOptions().position(sieuthi).title("Vị trí hiện tại"));
                maker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sieuthi,15));

            }
        });
    }

    private void addEvents() {
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if(progress!=null)
                {
                    progress.dismiss();
                    Toast.makeText(ViTriActivity.this,"Đã Tải xong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addControls() {
        progress=new ProgressDialog(ViTriActivity.this);
        progress.setTitle("thông báo");
        progress.setMessage("Đang tải map! vui lòng chờ!");
        progress.show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMap=mapFragment.getMap();
        DanhSachDiaDiem ds=new DanhSachDiaDiem();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ds= (DanhSachDiaDiem) bundle.getSerializable("Ds");
        dsdiadiem=ds.getDsdiadiem();
        Marker maker;
      LatLng pos;
      for(int i=0;i<dsdiadiem.size();i++)
        {
            pos =new LatLng(dsdiadiem.get(i).getKinhDo(), dsdiadiem.get(i).getViDo());

            maker=mMap.addMarker(new MarkerOptions().position(pos).title(""+dsdiadiem.get(i).getTen()));
             maker.showInfoWindow();
             mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,15));
        }

      /* LatLng sieuthi = new LatLng(dsdiadiem.get(0).getKinhDo(), 106.667456);
       // LatLng sieuthi = new LatLng(dsdiadiem.get(0).getKinhDo(), dsdiadiem.get(0).getViDo());
        Marker maker= mMap.addMarker(new MarkerOptions().position(sieuthi).title(""));
        maker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sieuthi,15));
        Toast.makeText(ViTriActivity.this,""+ dsdiadiem.get(0).getKinhDo(),Toast.LENGTH_LONG).show();*/

    }


}
