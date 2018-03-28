package labstuff.gcu.me.org.coursework;

/**
 * Craig Higney S1630775
 */

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class IncidentsSession extends ListActivity{

    private SearchView searchBar;
    private ArrayAdapter<String> currentAdapter = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadworks);

        listView = (ListView) findViewById(android.R.id.list);
        searchBar = (SearchView) findViewById(R.id.searchView);




        ArrayList<String> localData = new ArrayList<String>();
        IncidentsParse getXML = new IncidentsParse();
        getXML.execute();
        localData = getXML.getIncidentsFeed();


        final ArrayAdapter PlannedRoadWorks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, localData);
        listView.setAdapter(PlannedRoadWorks);


        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                text.replace(" ","");
                PlannedRoadWorks.getFilter().filter(text);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                text.replace(" ","");
                PlannedRoadWorks.getFilter().filter(text);
                return false;
            }
        });

    }

}
