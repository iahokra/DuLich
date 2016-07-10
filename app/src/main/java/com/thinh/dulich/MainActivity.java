package com.thinh.dulich;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import model.DanhSachDiaDiem;
import model.DiaDiem;


public class MainActivity extends ActionBarActivity {
    String DATABASE_NAME="DuLich.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    Button btnXemViTri;

    ArrayList<DiaDiem> dsdiadiem=new ArrayList<>();
    DanhSachDiaDiem ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        addControls();
        addEvents();
        showaall();

    }

    private void showaall() {
        database=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor=database.rawQuery("select * from DiaDiem",null);
        dsdiadiem.clear();
        ds=new DanhSachDiaDiem();
        while(cursor.moveToNext())
        {
            DiaDiem di=new DiaDiem();
            di.setID(cursor.getInt(0));
            di.setKinhDo(cursor.getDouble(2));
            di.setViDo(cursor.getDouble(3));
            di.setLoai(cursor.getString(1));
            di.setLuuY(cursor.getString(4));
            di.setVatDung(cursor.getString(5));
            di.setTen(cursor.getString(6));
            dsdiadiem.add(di);
        }
        cursor.close();
        ds.setDsdiadiem(dsdiadiem);
        Toast.makeText(MainActivity.this,""+dsdiadiem.size(),Toast.LENGTH_LONG).show();
    }

    private void addEvents() {
        btnXemViTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViTriActivity.class);
                Bundle bunđle=new Bundle();
                bunđle.putSerializable("Ds",ds);
                intent.putExtras(bunđle);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        btnXemViTri= (Button) findViewById(R.id.btnXemViTri);
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
