package com.example.nicestart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        miVisorWeb = (WebView) findViewById(R.id.webViewMain);
        String html = "<html>" +
                "<head><style>" +
                "html, body { margin:0; padding:0; height:100%; overflow:hidden; }"
                + "img { width:100%; height:100%; object-fit:cover; }" +
                "</style></head>" +
                "<body>" +
                "<img src ='https://https://media1.tenor.com/m/yheo1GGu3FwAAAAC/rick-roll-rick-ashley.gif' />" +
                "</body></html>";
        miVisorWeb.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        WebView mycontext = findViewById(R.id.webViewMain); //para el contexto (mantener pulsado)
        registerForContextMenu(mycontext); //se debe registrar
        swipeLayout = findViewById(R.id.swipe); //swipelayout, importante meter en el xml.
        swipeLayout.setOnRefreshListener(mOnRefreshListener); //la variable se inicializa.
    }

    protected SwipeRefreshLayout.OnRefreshListener //Refresh de pagina, hacer imports necesarios.
            mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            final ConstraintLayout mLayout = findViewById(R.id.mainConst);
            Snackbar snackbar = Snackbar
                    .make(mLayout, "Page reset", Snackbar.LENGTH_SHORT) //texto
                    .setAction("UNDO", new View.OnClickListener(){ //accion, se le puede meter otro snackbar
                        @Override
                        public void onClick(View view){
                            Snackbar snackbar1 = Snackbar.make(mLayout, "Action restored", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });
            snackbar.show();
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false); //Importante.
        }
    };

    public void openProfile(View v) { //Intent para abrir
        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);
    }

    //Implement context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    } //Crea el context menu y mete sus items.

    @Override
    public boolean onContextItemSelected(MenuItem item) { //Al seleccionar algo del context menu
        if (item.getItemId() == R.id.item1) {
            Toast toast = Toast.makeText(this, "item copied", Toast.LENGTH_LONG);
            toast.show();
        } else if (item.getItemId() == R.id.item2) {
            Toast toast2 = Toast.makeText(this, "item downloaded", Toast.LENGTH_LONG);
            toast2.show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Options Menu de arriba.
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Al seleccionar algo del options menu.
        if (item.getItemId() == R.id.itemCopy) {
            Toast toast = Toast.makeText(this, "item copied", Toast.LENGTH_LONG);
            toast.show();
        } else if (item.getItemId() == R.id.itemSettings) {
            Toast toast = Toast.makeText(this, "open settings", Toast.LENGTH_LONG);
            toast.show();
        } else if (item.getItemId() == R.id.itemLogOff) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setView(getLayoutInflater().inflate(R.layout.dialog_logoff, null));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i){
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int i){
                    dialog.dismiss();
                }
            });

            builder.show();
        }
        return false;
    }
}