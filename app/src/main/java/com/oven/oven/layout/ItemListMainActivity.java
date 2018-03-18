package com.oven.oven.layout;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oven.oven.R;
import com.oven.oven.component.TestActivity;

public class ItemListMainActivity extends AppCompatActivity { // 우체국 오픈api 인증키
    private String _key = "9716101b972fa7fd01517314519730";
    private TextView _addressEdit;
    private Button _searchBtn;
    private ListView _addressListView;
    private ArrayAdapter<String> _addressListAdapter; // 사용자가 입력한 주소
    private String _putAddress; // 우체국으로부터 반환 받은 우편주소 리스트
    private ArrayList<String> _addressSearchResultArr = new ArrayList<String>();
    private ArrayList<String> roadAddressList = new ArrayList<String>();
    private ArrayList<String> zipCodeList = new ArrayList<String>();
    private ArrayList<String> jibunAddressList = new ArrayList<String>();
    private String[] addressStrArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_main);
        _addressEdit = (EditText) findViewById(R.id.addressedit);
        _searchBtn = (Button) findViewById(R.id.btnsearch);
        _addressListView = (ListView) findViewById(R.id.addresslist);



        _searchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddress(_addressEdit.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ItemListMainActivity.this, ModifyUserInfoActivity.class);
        setResult(Activity.RESULT_CANCELED,intent);
        finish();
    }

    public void getAddress(String kAddress) {
        _putAddress = kAddress;
        new GetAddressDataTask().execute();
    }

    private class GetAddressDataTask extends AsyncTask<String, Void, HttpResponse> {
        @Override
        protected HttpResponse doInBackground(String... urls) {
            HttpResponse response = null;
            final String apiurl = "http://biz.epost.go.kr/KpostPortal/openapi";
            ArrayList<String> addressInfo = new ArrayList<String>();
            HttpURLConnection conn = null;
            try {
                StringBuffer sb = new StringBuffer(3);
                sb.append(apiurl);
                sb.append("?regkey=" + _key + "&target=postNew&countPerPage=50&query=");
                sb.append(URLEncoder.encode(_putAddress, "EUC-KR"));
                String query = sb.toString();
                URL url = new URL(query);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("accept-language", "ko");
                DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                byte[] bytes = new byte[4096];
                InputStream in = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while (true) {
                    int red = in.read(bytes);
                    if (red < 0) break;
                    baos.write(bytes, 0, red);
                }
                String xmlData = baos.toString("utf-8");
                baos.close();
                in.close();
                conn.disconnect();
                Document doc = docBuilder.parse(new InputSource(new StringReader(xmlData)));

                Element el = (Element) doc.getElementsByTagName("itemlist").item(0);
                for (int i = 0; i < ((Node) el).getChildNodes().getLength(); i++) {
                    Node node = ((Node) el).getChildNodes().item(i);
                    if (!node.getNodeName().equals("item")) {
                        continue;
                    }
                    String post = node.getChildNodes().item(1).getFirstChild().getNodeValue();
                    String address = node.getChildNodes().item(3).getFirstChild().getNodeValue();
                    String jibunAddress = node.getChildNodes().item(5).getFirstChild().getNodeValue();
                    zipCodeList.add(post);
                    jibunAddressList.add(jibunAddress);
                    roadAddressList.add(address);
                    addressInfo.add("("+post+")\n"+address+"\n"+jibunAddress);
                }
                _addressSearchResultArr = addressInfo;
                publishProgress();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) conn.disconnect();
                } catch (Exception e) {
                }
            }
            return response;
        }

        @Override
        protected void onProgressUpdate(Void... values) { // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            addressStrArray = new String[_addressSearchResultArr.size()];
            addressStrArray = _addressSearchResultArr.toArray(addressStrArray);
            _addressListAdapter = new ArrayAdapter<String>(ItemListMainActivity.this, android.R.layout.simple_list_item_1, addressStrArray);
            _addressListView.setAdapter(_addressListAdapter);
            _addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String tmp = Arrays.asList(addressStrArray).get(i);
                    //Toast.makeText(ItemListMainActivity.this, tmp, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.putExtra("zipCode", zipCodeList.get(i));
                    intent.putExtra("roadAddress", roadAddressList.get(i));
                    intent.putExtra("jibunAddress", jibunAddressList.get(i));
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            });
        }
    }
}