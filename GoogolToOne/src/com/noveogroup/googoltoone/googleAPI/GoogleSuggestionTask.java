package com.noveogroup.googoltoone.googleAPI;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.fragment.QueryFragment;
import com.noveogroup.googoltoone.gamelogic.RoundInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//CRDone must have postfix "Task"
public class GoogleSuggestionTask extends AsyncTask<String, Void , List<String>> {

    private static final String GOOGLEAPIURL = "http://suggestqueries.google.com/complete/search?client=toolbar&q=";
    private final TextView suggestions;
    private final Button gameStartButton;
    private final EditText query;
    //CRDone Use Java Naming Standards for Constant values
    private static final int NUMBER_OF_SUGGESTIONS = 5;
    private static final String LOG_TAG = "GoogolToOne";

    private RoundInfo roundInfo;

    public GoogleSuggestionTask(QueryFragment fragment, RoundInfo roundInfo) {
        this.suggestions = (TextView) fragment.getView().findViewById(R.id.results);
        this.query = (EditText) fragment.getView().findViewById(R.id.query);
        this.gameStartButton = (Button) fragment.getView().findViewById(R.id.continue_button);

        this.roundInfo = roundInfo;
    }

    @Override
    protected List<String> doInBackground(String... query) {
        //CRDone Simplify: return getXML(getURL(query[0]))
        return getXML(getURL(query[0]));

    }

    @Override
    protected void onPostExecute(List<String> arrayList) {

        //CRDone Use TextUtils.isEmpty()
        if (!isCancelled()) {
            if (TextUtils.isEmpty(query.getText().toString())) {
                suggestions.setText(R.string.query_empty);
                gameStartButton.setEnabled(false);
                roundInfo.setGoogleAnswers(null);
            } else if (arrayList != null) {
                roundInfo.setGoogleAnswers(new Vector<String>(arrayList));

                suggestions.setText("");
                for (String suggestion : arrayList) {
                    suggestions.append(suggestion + "\n");
                }
                gameStartButton.setEnabled(true);
            } else {
                suggestions.setText(R.string.query_invalid);
                gameStartButton.setEnabled(false);
                roundInfo.setGoogleAnswers(null);
            }
        }
    }

    private static String getURL(String query) {
        try {
            return GOOGLEAPIURL + URLEncoder.encode(query, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            //CRDone Add logging
            Log.i(LOG_TAG, "Unsupported encoding");
            return null;
        }
    }

    //CRDone Use List<String> as return statement
    private List<String> getXML (String queryURL) {
        InputStream inputStream = null;
        try {
            URL url = new URL(queryURL);
            URLConnection connection = url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            Document doc = parseXML(inputStream); //CRDone Close input stream in finally block
            NodeList suggestions = doc.getElementsByTagName("suggestion");
            return checkResults(suggestions);
        }
        catch (Exception e){
            Log.i(LOG_TAG, "Connection error");
            return null;     //CRDone Add logging
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    Log.i(LOG_TAG, "Closing stream error");
                }
            }
        }
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

    private List<String> checkResults(NodeList suggestions) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < suggestions.getLength(); i++) {
            Element element = (Element) suggestions.item(i);
            if (checkElement(element)) {
                result.add(element.getAttribute("data"));
                if (result.size() >= NUMBER_OF_SUGGESTIONS) {
                    break;
                }
            }
        }
        if (result.size() < NUMBER_OF_SUGGESTIONS) {
            return null;
        }
        else {
            return result;
        }
    }

    private Boolean checkElement (Element element) {
        String result = element.getAttribute("data");
        String queryString = query.getText().toString().toLowerCase();
        if (!result.toLowerCase().contains(queryString + " ")) {
            return false;
        }
        else if(result.toLowerCase().indexOf(queryString + " ") > 0) {
            return false;
        }
        else if (result.toLowerCase().equals(queryString)) {
            return false;
        }
        else {
            return true;
        }
    }

}