package digitalmedia.gatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {
    private String jsonString = "{\"2015 Assembly Papers\":[\n" +
            "\t{\"date\":1434927600,\"title\":\"Assembly Paper\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/thumbnail_0.jpg\",\"description\":\"This year's General Assembly daily publications are available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/church-of-scotland-assembly-papers-including-friday_2.epub\",\"size\":\"1137022\"}],\n" +
            "\"2014 Assembly Papers\":[\n" +
            "\t{\"date\":1402959600,\"title\":\"Saturday\\/Monday (pink paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/j322431_thumb.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/church-of-scotland-the-church-of-scotland-general-assembly-2014-assembly-papers-saturday-17-monday-19-may.epub\",\"size\":\"789589\"},\n" +
            "\t{\"date\":1403132400,\"title\":\"Tuesday (blue paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/j322430_thumb.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/the-scottish-government-the-church-of-scotland-general-assembly-2014-assembly-papers-tuesday-20-may.epub\",\"size\":\"212037\"},\n" +
            "\t{\"date\":1403218800,\"title\":\"Wednesday (green paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/J322432_thumb.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/church-of-scotland-the-church-of-scotland-general-assembly-2014-assembly-papers-wednesday-21-may.epub\",\"size\":\"134439\"},\n" +
            "\t{\"date\":1403305200,\"title\":\"Thursday (yellow paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/j322433_thumb.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/church-of-scotland-the-church-of-scotland-general-assembly-2014-assembly-papers-thursday-22-may.epub\",\"size\":\"197675\"},\n" +
            "\t{\"date\":1403391600,\"title\":\"Friday (white paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/j322434_thumb.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/church-of-scotland-the-church-of-scotland-general-assembly-2014-assembly-papers-friday-23-may.epub\",\"size\":\"331917\"}],\n" +
            "\"2013 Assembly Papers\":[{\"date\":1371423600,\"title\":\"Saturday\\/Monday (pink paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/cover_1_0.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/assemblySat-Mon.epub\",\"size\":\"268093\"},\n" +
            "\t{\"date\":1371682800,\"title\":\"Tuesday (blue paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/cover_2_0.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/assemblyTues.epub\",\"size\":\"143086\"},\n" +
            "\t{\"date\":1371769200,\"title\":\"Wednesday (green paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/cover_4_0.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/assemblyWed.epub\",\"size\":\"81722\"},\n" +
            "\t{\"date\":1371855600,\"title\":\"Thursday (yellow paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/cover_3_0.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/assemblyThurs.epub\",\"size\":\"93044\"},\n" +
            "\t{\"date\":1371942000,\"title\":\"Friday (white paper)\",\"cover\":\"https:\\/\\/www.gapublications.co.uk\\/sites\\/default\\/files\\/cover_5_0.jpg\",\"description\":\"Last year's General Assembly daily publications are still available to download as e-books\",\"epub\":\"http:\\/\\/files.gapublications.co.uk\\/assemblyFri.epub\",\"size\":\"131582\"}]}}";

    List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
    //HashMap<String, List<Map<String,String>>> listPapers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Locate the button in activity_main.xml
        Button btn_activity2 = (Button) findViewById(R.id.btn_activity2);
        // Capture button clicks
        btn_activity2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Home.this,
                        Activity2.class);
                startActivity(myIntent);
            }
        });

       /* // hashMap with multiple values with default size and load factor
        HashMap<String, ArrayList<String>> multiMap = new HashMap<String, ArrayList<String>>();

        // create an arrayList to store values
        ArrayList<String> listPapers = new ArrayList<String>();
        listPapers.add("date = 2015 ");
        listPapers.add("description = something");
        listPapers.add("link = something");

        // create list two and store values
        ArrayList<String> listTwo = new ArrayList<String>();
        listTwo.add("date = 2015");
        listTwo.add("description = something");
        listTwo.add("link = something");

        // put values into map
        multiMap.put("2015 Assembly Papers", listPapers);
        multiMap.put("2014 Assembly Papers", listTwo);*/

     /*HashMap with Multiple Values
        Key = 'P color' has values: [Pink, Purple]
        Key = 'B color' has values: [Blue, Black, Brown]*/

        initList();
        ListView listView = (ListView) findViewById(R.id.listView1);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList, android.R.layout.simple_list_item_1,
                new String[] {"2014 Assembly Papers"}, new int[] {android.R.id.text1});
        listView.setAdapter(simpleAdapter);
    }



    private void initList(){

        try{

            JSONObject response = new JSONObject(jsonString);
            String rootKey="";
            Iterator keys = response.keys();
            while(keys.hasNext()) {
                // loop to get the dynamic key
                rootKey = (String)keys.next();
                JSONArray jsonArray = response.optJSONArray(rootKey);
                for(int i = 0; i< jsonArray.length();i++){
                    JSONObject jsonChildNode = jsonArray.getJSONObject(i);
                    String name = jsonChildNode.optString("date");
                    String description = jsonChildNode.optString("title");
                    String outPut = name + " - " + description;
                    dataList.add(createEmployee(rootKey, outPut));
                }
            }
            Toast.makeText(getApplicationContext(), rootKey,
                    Toast.LENGTH_LONG).show();

        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String>createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }



}
