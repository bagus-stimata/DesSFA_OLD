package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccJournalh;
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

public class AccJournalhServiceRest {
    protected static final String TAG = AccJournalhServiceRest.class.getSimpleName();
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

    public AccJournalhServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccJournalh getAccJournalhById(int id) {
        AccJournalhServiceRest.AccJournalhCrudAsyncTask asyncTask = (AccJournalhServiceRest.AccJournalhCrudAsyncTask) new AccJournalhServiceRest.AccJournalhCrudAsyncTask(apiAuthenticationClient, id, true);
        AccJournalh fArea = null;
        try {
            fArea = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fArea==null) fArea =new AccJournalh();
        return fArea;
    }
    public List<AccJournalh> getAllAccJournalh() {
        AccJournalhServiceRest.AccJournalhAllAsyncTask asyncTask = (AccJournalhServiceRest.AccJournalhAllAsyncTask) new AccJournalhServiceRest.AccJournalhAllAsyncTask(apiAuthenticationClient);
        List<AccJournalh> listAccJournalh = new ArrayList<>();
        try {
            listAccJournalh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listAccJournalh;

    }
    public void createAccJournalh(AccJournalh fArea) {
//        new AccJournalhCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccJournalhServiceRest.AccJournalhCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccJournalh(Integer id, AccJournalh fArea) {
//        new AccJournalhUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccJournalhServiceRest.AccJournalhCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccJournalh(Integer id) {
//        new AccJournalhDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccJournalhServiceRest.AccJournalhCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class AccJournalhCrudAsyncTask extends AsyncTask<Void, Void, AccJournalh> {

        String operation = "";
        AccJournalh newAccJournalh = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccJournalhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccJournalhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccJournalh newAccJournalh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccJournalh = newAccJournalh;
            operation = "ADD_NEW";
        }
        private AccJournalhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccJournalh updateAccJournalh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccJournalh = updateAccJournalh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccJournalhCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected AccJournalh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccJournalh> response = restTemplate.exchange(url, HttpMethod.POST, AccJournalh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccJournalh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccJournalh> response = restTemplate.postForEntity(url, httpEntity,  AccJournalh.class);
                ResponseEntity<AccJournalh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccJournalh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccJournalh, apiAuthenticationClient.getRequestHeaders()), AccJournalh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccJournalhInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccJournalh, apiAuthenticationClient.getRequestHeaders()), AccJournalh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccJournalh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournalh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccJournalhById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournalh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccJournalh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccJournalh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccJournalh();
            }
        }

        @Override
        protected void onPostExecute(AccJournalh result) {
            dismissProgressDialog();
//            if(result==null) result = new AccJournalh();
//            displayResponseAccJournalh(result);
        }
    }

    public class AccJournalhAllAsyncTask extends  AsyncTask<Void, Void, List<AccJournalh>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccJournalhAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccJournalh> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccJournalh";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccJournalh[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccJournalh[].class);
                List<AccJournalh> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccJournalh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccJournalh>();
            }
        }

        @Override
        protected void onPostExecute(List<AccJournalh> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccJournalh();
//            displayResponseAccJournalh(result);
        }


    }


}
