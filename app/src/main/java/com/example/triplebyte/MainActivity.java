package com.example.triplebyte;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView costRecyclerView;
    private List<Object> costDetailsAndHeaders = new ArrayList<>();
    private CostItemsAdapter adapter;
    private Set<String> headers = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 10; i >= 0; i--){
            CostDetails details = new CostDetails(""+i, ""+i+"/"+i+"/"+i,"$"+i, ""+i);
            if (!headers.contains(details.getCategory())){
                String header = details.getCategory();
                costDetailsAndHeaders.add(header);
            }
            costDetailsAndHeaders.add(details);
        }

        sortAll();

        costRecyclerView = findViewById(R.id.costRecyclerView);
        adapter = new CostItemsAdapter(this, this.costDetailsAndHeaders);
        costRecyclerView.setAdapter(adapter);

        costRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CostDetails appendNewDetail = new CostDetails("999", "3/3/3", "$3.12", "new Transaction");
                if (!headers.contains(appendNewDetail.getCategory())){
                    String headerDetail = appendNewDetail.getCategory();
                    headers.add(headerDetail);
                    costDetailsAndHeaders.add(headerDetail);
                }
                costDetailsAndHeaders.add(appendNewDetail);
                adapter = new CostItemsAdapter(getApplicationContext(), costDetailsAndHeaders);
                adapter.notifyDataSetChanged();
                costRecyclerView.setAdapter(adapter);

                sortAll();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sortAll(){
        Collections.sort(costDetailsAndHeaders, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof CostDetails){
                    return ((String) o1).compareTo(((CostDetails) o2).getCategory());
                } else if (o2 instanceof String && o1 instanceof CostDetails){
                    return ((CostDetails) o1).getCategory().compareTo((String) o2);
                } else if (o1 instanceof CostDetails && o2 instanceof CostDetails){
                    return ((CostDetails) o1).getCategory().compareTo(((CostDetails) o2).getCategory());
                } else if (o1 instanceof String && o2 instanceof String){
                    return ((String) o1).compareTo((String) o2);
                }

                return 0;
            }
        });
    }
}
