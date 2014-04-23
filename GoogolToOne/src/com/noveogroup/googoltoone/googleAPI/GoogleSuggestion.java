package com.noveogroup.googoltoone.googleAPI;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.fragment.QueryFragment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GoogleSuggestion extends AsyncTask<String, Void , ArrayList<String>> {

    private static final String GOOGLEAPIURL = "http://suggestqueries.google.com/complete/search?client=toolbar&q=";
    private final TextView suggestions;
    private final EditText query;
    private static final int numberOfSuggestions = 5;

    public GoogleSuggestion(QueryFragment fragment) {
        this.suggestions = (TextView) fragment.getView().findViewById(R.id.results);
        this.query = (EditText) fragment.getView().findViewById(R.id.query);
    }

    @Override
    protected ArrayList<String> doInBackground(String... query) {

        if(getXML(getURL(query[0])) == null) {
            return null;
        }
        else {
            return getXML(getURL(query[0]));
        }

    }

    private static String getURL(String query) {
        try {
            return GOOGLEAPIURL + URLEncoder.encode(query, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private ArrayList<String> getXML (String queryURL) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            URL url = new URL(queryURL);
            URLConnection connection = url.openConnection();
            Document doc = parseXML(connection.getInputStream());
            NodeList suggestions = doc.getElementsByTagName("suggestion");
            if(suggestions.getLength() >= numberOfSuggestions) {
                for (int i = 0; i < numberOfSuggestions; i++) {
                    Element element = (Element) suggestions.item(i);
                    result.add(element.getAttribute("data"));
                }
            }
            else return null;
        }
        catch (Exception e){
            return null;
        }
        return result;
    }

    private Document parseXML (InputStream input) throws Exception{
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(input);
        }
        catch (Exception e) {
            throw e;
        }
        return doc;
    }

    @Override
    protected void onPostExecute(ArrayList<String> arrayList) {

        if (query.getText().toString().equals("")) {
            suggestions.setText(R.string.query_empty);
        }
        else if(arrayList != null) {
            suggestions.setText("");
            for (String suggestion : arrayList) {
                suggestions.append(suggestion + "\n");
            }
        }
        else {
            suggestions.setText(R.string.query_invalid);
        }
    }

}