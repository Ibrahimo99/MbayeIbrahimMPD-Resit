package com.gcu.mbayeibrahimmpdseconddiet;
//  Ibrahim Mbaye S1903674

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    private TextView rawDataDisplay;
    private Button startButton;
    private String result = "";
    private String url1="";
    // Traffic Scotland Planned Roadworks XML link
    private String urlSource="https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";
    private List<String> urlSources = new ArrayList<String>();
    private List<WeatherInfo> locationWeathers = new ArrayList<WeatherInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MyTag","in onCreate");
        // Set up the raw links to the graphical components
        rawDataDisplay = (TextView)findViewById(R.id.rawDataDisplay);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        Log.e("MyTag","after startButton");
        // More Code goes here
    }

    public void startProgress()
    {
        // Run network access on a separate thread;
        new Thread(new Task(urlSources)).start();
    } //

    public void initUrlSources() {
        Log.e("MyTag","in initUrlSources");

        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/5128581");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/287286");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/934154");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241");

        Log.e("MyTag","after initUrlSources");
    }

    public WeatherInfo parseXML(InputStream is) {
        Log.e("MyTag","start parse");

        String text = "";
        WeatherItem item = null;
        WeatherInfo weatherInfo = null;
        List<WeatherItem> items = new ArrayList<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            String parsing = "weather";

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("channel")) {
                            weatherInfo = new WeatherInfo();
                        }
                        else if (tagname.equalsIgnoreCase("item")) {
                            item = new WeatherItem();
                            parsing = "item";
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            // add employee object to list
                            items.add(item);
                        }else if (tagname.equalsIgnoreCase("title")) {
                            if (parsing == "weather") {
                                weatherInfo.setTitle(text);
                            } else {
                                item.setTitle(text);
                            }
                        }  else if (tagname.equalsIgnoreCase("description")) {
                            if (parsing == "weather") {
                                weatherInfo.setDescription(text);
                            } else {
                                item.setDescription(text);
                            }
                        } else if (tagname.equalsIgnoreCase("channel")) {
                            weatherInfo.setItems(items);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        Log.e("MyTag","end parse");

        return weatherInfo;
    }

    @Override
    public void onClick(View v)
    {
        Log.e("MyTag","in onClick");
        initUrlSources();
        startProgress();
        Log.e("MyTag","after startProgress");
    }

    private class Task implements Runnable
    {
        private List<String> urls;

        public Task(List<String> aurl)
        {
            urls = aurl;
        }

        @Override
        public void run()
        {

            URL aurl;
            URLConnection yc;
            WeatherInfo weatherInfo = null;


            Log.e("MyTag","in run");
            for (String url : urls) {
                try
                {
                    Log.e("MyTag","in try");
                    aurl = new URL(url);
                    yc = aurl.openConnection();
                    weatherInfo = parseXML(yc.getInputStream());
                    locationWeathers.add(weatherInfo);
                    result = result + " -1- " + weatherInfo.toString();

                    Log.e("MyTag","after ready");


                }
                catch (IOException ae)
                {
                    Log.e("MyTag", "ioexception in run");
                }
            }

            MainActivity.this.runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    rawDataDisplay.setText(result);
                    Intent intent = new Intent(getBaseContext(), WeatherActivity.class);
                    intent.putExtra("com.example.maha.EXTRA_LOCATION_WEATHERS", (Serializable) locationWeathers);
                    startActivity(intent);
                }
            });
        }

    }
}