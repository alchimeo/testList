package digitalmedia.gatest;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Activity2 extends ListActivity {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";

    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_PAPERS = "papers";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE_MOBILE = "mobile";

    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, Ebook>> contactList;
    private String rootKey="";

    public class Ebook {

        public String name;
        public String email;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        contactList = new ArrayList<HashMap<String, Ebook>>();

        ListView lv = getListView();
        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Activity2.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            String jsonString = "{\n" +
                    "    \"contacts\": [\n" +
                    "        {\n" +
                    "                \"id\": \"c200\",\n" +
                    "                \"name\": \"Ravi Tamada\",\n" +
                    "                \"email\": \"ravi@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c2011\",\n" +
                    "                \"name\": \"Kate Winslet\",\n" +
                    "                \"email\": \"kate_winslet@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "    ],\n" +
                    "    \"people\": [\n" +
                    "        {\n" +
                    "                \"id\": \"c200\",\n" +
                    "                \"name\": \"toto toto\",\n" +
                    "                \"email\": \"toto@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"male\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "        {\n" +
                    "                \"id\": \"c2011\",\n" +
                    "                \"name\": \"momo\",\n" +
                    "                \"email\": \"momo@gmail.com\",\n" +
                    "                \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
                    "                \"gender\" : \"female\",\n" +
                    "                \"phone\": {\n" +
                    "                    \"mobile\": \"+91 0000000000\",\n" +
                    "                    \"home\": \"00 000000\",\n" +
                    "                    \"office\": \"00 000000\"\n" +
                    "                }\n" +
                    "        },\n" +
                    "    ]\n" +
                    "}";

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonString);

                    //String rootKey="";
                    Iterator keys = jsonObj.keys();
                    while(keys.hasNext()) {
                        // loop to get the dynamic key
                        rootKey = (String)keys.next();
                        JSONArray jsonArray = jsonObj.optJSONArray(rootKey);

                        // tmp hashmap for single contact
                        HashMap<String, Ebook> ebookList = new HashMap<>();


                    // looping through All Contacts
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                        Ebook ebook = new Ebook();
                        ebook.name = c.getString(TAG_NAME);
                        ebook.email = c.getString(TAG_EMAIL);

                        // adding each child node to HashMap key => value
                        ebookList.put(rootKey, ebook);

                    }
                        // adding contact to contact list
                        contactList.add(ebookList);
                    }





                   /* // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_CONTACTS);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String email = c.getString(TAG_EMAIL);

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject(TAG_PHONE);
                        String mobile = phone.getString(TAG_PHONE_MOBILE);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_EMAIL, email);
                        contact.put(TAG_PHONE_MOBILE, mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(getApplicationContext(), rootKey,
                    Toast.LENGTH_LONG).show();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Activity2.this, contactList,
                    R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
                    TAG_PHONE_MOBILE }, new int[] { R.id.name,
                    R.id.email, R.id.mobile });

            setListAdapter(adapter);
        }

    }

}