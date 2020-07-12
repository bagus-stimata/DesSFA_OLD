package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.CbMutasiKasdItems;
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

public class CbMutasiKasdItemsServiceRest {
    protected static final String TAG = CbMutasiKasdItemsServiceRest.class.getSimpleName();
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

    public CbMutasiKasdItemsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public CbMutasiKasdItems getCbMutasiKasdItemsById(int id) {
        CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask asyncTask = (CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask) new CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask(apiAuthenticationClient, id, true);
        CbMutasiKasdItems cbMutasiKasdItems = null;
        try {
            cbMutasiKasdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (cbMutasiKasdItems==null) cbMutasiKasdItems =new CbMutasiKasdItems();
        return cbMutasiKasdItems;
    }
    public List<CbMutasiKasdItems> getAllCbMutasiKasdItems() {
        CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsAllAsyncTask asyncTask = (CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsAllAsyncTask) new CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsAllAsyncTask(apiAuthenticationClient);
        List<CbMutasiKasdItems> listCbMutasiKasdItems = new ArrayList<>();
        try {
            listCbMutasiKasdItems = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listCbMutasiKasdItems;

    }
    public void createCbMutasiKasdItems(CbMutasiKasdItems fArea) {
//        new CbMutasiKasdItemsCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateCbMutasiKasdItems(Integer id, CbMutasiKasdItems fArea) {
//        new CbMutasiKasdItemsUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteCbMutasiKasdItems(Integer id) {
//        new CbMutasiKasdItemsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new CbMutasiKasdItemsServiceRest.CbMutasiKasdItemsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }




    public class CbMutasiKasdItemsCrudAsyncTask extends AsyncTask<Void, Void, CbMutasiKasdItems> {

        String operation = "";
        CbMutasiKasdItems newCbMutasiKasdItems = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private CbMutasiKasdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private CbMutasiKasdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  CbMutasiKasdItems newCbMutasiKasdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newCbMutasiKasdItems = newCbMutasiKasdItems;
            operation = "ADD_NEW";
        }
        private CbMutasiKasdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, CbMutasiKasdItems updateCbMutasiKasdItems){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newCbMutasiKasdItems = updateCbMutasiKasdItems;
            this.id = id_update;
            operation = "UPDATE";
        }
        private CbMutasiKasdItemsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected CbMutasiKasdItems doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<CbMutasiKasdItems> response = restTemplate.exchange(url, HttpMethod.POST, CbMutasiKasdItems.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newCbMutasiKasdItems, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<CbMutasiKasdItems> response = restTemplate.postForEntity(url, httpEntity,  CbMutasiKasdItems.class);
                ResponseEntity<CbMutasiKasdItems> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createCbMutasiKasdItems";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newCbMutasiKasdItems, apiAuthenticationClient.getRequestHeaders()), CbMutasiKasdItems.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateCbMutasiKasdItemsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newCbMutasiKasdItems, apiAuthenticationClient.getRequestHeaders()), CbMutasiKasdItems.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteCbMutasiKasdItems/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKasdItems.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getCbMutasiKasdItemsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKasdItems.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new CbMutasiKasdItems();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new CbMutasiKasdItems();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new CbMutasiKasdItems();
            }
        }

        @Override
        protected void onPostExecute(CbMutasiKasdItems result) {
            dismissProgressDialog();
//            if(result==null) result = new CbMutasiKasdItems();
//            displayResponseCbMutasiKasdItems(result);
        }
    }

    public class CbMutasiKasdItemsAllAsyncTask extends  AsyncTask<Void, Void, List<CbMutasiKasdItems>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private CbMutasiKasdItemsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<CbMutasiKasdItems> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllCbMutasiKasdItems";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<CbMutasiKasdItems[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), CbMutasiKasdItems[].class);
                List<CbMutasiKasdItems> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<CbMutasiKasdItems>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<CbMutasiKasdItems>();
            }
        }

        @Override
        protected void onPostExecute(List<CbMutasiKasdItems> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new CbMutasiKasdItems();
//            displayResponseCbMutasiKasdItems(result);
        }


    }


}
