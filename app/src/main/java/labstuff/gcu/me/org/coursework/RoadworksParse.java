package labstuff.gcu.me.org.coursework;

/**
 * Craig Higney S1630775
 */

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RoadworksParse extends AsyncTask {
    //declare variables
    private URL link;
    private ArrayList<String> items = new ArrayList();
    private boolean isItem = false;


    @Override
    protected Object doInBackground(Object[] objects) {

        try{
            //declaring the url to be accessed
            link = new URL("http://trafficscotland.org/rss/feeds/plannedroadworks.aspx");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getXMLStream(link), "UTF_8");

            int eType = xpp.getEventType();

            //Parsing the data recieved from the url
            while (eType != XmlPullParser.END_DOCUMENT) {
                if (eType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        isItem = true;

                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (isItem)
                            items.add(xpp.nextText());
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (isItem)
                            items.add(xpp.nextText().replace("<br />", "\n"));
                    }else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (isItem)
                            items.add(xpp.nextText());
                    }

                } if (eType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {

                    isItem = false;
                }

                eType = xpp.next();
            }
        } catch(MalformedURLException e){

            System.out.println("RoadWorksFeed.AsyncTask Error(MalformedURLException): " + e);
        } catch(XmlPullParserException e){

            System.out.println("RoadWorksFeed.AsyncTask Error(XmlPullParserException): " + e);
        } catch(IOException e){

            System.out.println("RoadWorksFeed.AsyncTask Error(IOException): " + e);
        } catch(Exception e){

            System.out.println("RoadWorksFeed.AsyncTask Error(Exception): " + e);
        }

        //return the rss feed parsed
        return items;
    }


    public InputStream getXMLStream(URL link) {

        try {

            return link.openConnection().getInputStream();
        } catch (IOException e) {
            System.out.println("RoadWorksFeed.etXMLStream Error(Exception): " + e);
            return null;
        }
    }
    public ArrayList<String> getRoadworksfeed(){return items;}
}
