package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccJournaldItems;
import com.erp.distribution.sfa.security_config.ApiAuthenticationClient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccJournaldItemsServiceRest {
    protected static final String TAG = AccJournaldItemsServiceRest.class.getSimpleName();
    private ApiAuthenticationClient apiAuthenticationClient;

    private Context context;
    private ProgressDialog progressDialog;

    public void showProgressDialog(CharSequence message) {
        if (context !=null) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setIndeterminate(true);
            }
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    public void showLoadingProgressDialog() {
        this.showProgressDialog("Loading. Please wait...");
    }
    public void dismissProgressDialog() {
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
    }

    public AccJournaldItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccJournaldItems getAccJournaldItemsById(int id) {
        AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask asyncTask = (AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask) new AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        AccJournaldItems accJournaldItems = null;
        try {
            accJournaldItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (accJournaldItems==null) accJournaldItems =new AccJournaldItems();
        return accJournaldItems;
    }
    public List<AccJournaldItems> getAllAccJournaldItems() {
        AccJournaldItemsServiceRest.AccJournaldItemsAllAsyncTask asyncTask = (AccJournaldItemsServiceRest.AccJournaldItemsAllAsyncTask) new AccJournaldItemsServiceRest.AccJournaldItemsAllAsyncTask(apiAuthenticationClient);
        List<AccJournaldItems> listAccJournaldItems = new ArrayList<>();
        try {
            listAccJournaldItems = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listAccJournaldItems;

    }
    public void createAccJournaldItems(AccJournaldItems fArea) {
//        new AccJournaldItemsCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccJournaldItems(Integer id, AccJournaldItems fArea) {
//        new AccJournaldItemsUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccJournaldItems(Integer id) {
//        new AccJournaldItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccJournaldItemsServiceRest.AccJournaldItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class AccJournaldItemsCrudAsyncTask extends AsyncTask<Void, Void, AccJournaldItems> {

        String operation = "";
        AccJournaldItems newAccJournaldItems = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccJournaldItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccJournaldItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccJournaldItems newAccJournaldItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccJournaldItems = newAccJournaldItems;
            operation = "ADD_NEW";
        }
        private AccJournaldItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccJournaldItems updateAccJournaldItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccJournaldItems = updateAccJournaldItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccJournaldItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected AccJournaldItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccJournaldItems> response = restTemplate.exchange(url, HttpMethod.POST, AccJournaldItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccJournaldItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccJournaldItems> response = restTemplate.postForEntity(url, httpEntity,  AccJournaldItems.class);
                ResponseEntity<AccJournaldItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccJournaldItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccJournaldItems, apiAuthenticationClient.getRequestHeaders()), AccJournaldItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccJournaldItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccJournaldItems, apiAuthenticationClient.getRequestHeaders()), AccJournaldItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccJournaldItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournaldItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccJournaldItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournaldItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccJournaldItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccJournaldItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccJournaldItems();
            }
        }

        @Override
        protected void onPostExecute(AccJournaldItems result) {
            dismissProgressDialog();
//            if(result==null) result = new AccJournaldItems();
//            displayResponseAccJournaldItems(result);
        }
    }

    public class AccJournaldItemsAllAsyncTask extends  AsyncTask<Void, Void, List<AccJournaldItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccJournaldItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccJournaldItems> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccJournaldItems";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccJournaldItems[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournaldItems[].class);
                List<AccJournaldItems> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccJournaldItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccJournaldItems>();
            }
        }

        @Override
        protected void onPostExecute(List<AccJournaldItems> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccJournaldItems();
//            displayResponseAccJournaldItems(result);
        }


    }


}
