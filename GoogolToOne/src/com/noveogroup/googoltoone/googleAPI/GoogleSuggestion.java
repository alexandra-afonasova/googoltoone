package com.noveogroup.googoltoone.googleAPI;

import android.os.AsyncTask;
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

public class GoogleSuggestion extends AsyncTask<String, Void , ArrayList> {

    private static final String GOOGLEAPIURL = "http://suggestqueries.google.com/complete/search?client=toolbar&q=";

    @Override
    protected ArrayList<String> doInBackground(String... query) {

        return getXML(getURL(query[0]));

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
            for (int i = 0; i < suggestions.getLength(); i++) {
                Element element = (Element) suggestions.item(i);
                result.add(element.getAttribute("data"));
            }
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

}