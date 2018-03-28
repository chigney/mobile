package labstuff.gcu.me.org.coursework;
/**
 * Craig Higney S1630775
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button button_roadworks;
    private Button button_incidents;
    private Button button_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring buttons found on the main page/menu
        button_roadworks = (Button) findViewById(R.id.button_roadworks);
        button_incidents = (Button) findViewById(R.id.button_incidents);
        button_exit = (Button) findViewById(R.id.button_exit);

        button_roadworks.setOnClickListener(this);
        button_incidents.setOnClickListener(this);
        button_exit.setOnClickListener(this);
    }

    @Override
    //clicks that the user performs takes them to either a new page or closes the application
    public void onClick(View view) {
        if (view == button_roadworks){
            startActivity(new Intent(MainActivity.this, RoadworksSession.class));
        }
        else if (view == button_incidents){
            startActivity(new Intent(MainActivity.this, IncidentsSession.class));
        }
        else if (view == button_exit){
            finish();
            System.exit(0);
        }
    }
}
