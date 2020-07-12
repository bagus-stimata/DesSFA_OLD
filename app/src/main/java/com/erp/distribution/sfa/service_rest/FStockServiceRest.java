package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FStock;
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

public class FStockServiceRest {
    protected static final String TAG = FStockServiceRest.class.getSimpleName();
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

    public FStockServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FStock getFStockById(int id) {
        FStockServiceRest.FStockCrudAsyncTask asyncTask = (FStockServiceRest.FStockCrudAsyncTask) new FStockServiceRest.FStockCrudAsyncTask(apiAuthenticationClient, id, true);
        FStock fStock = null;
        try {
            fStock = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fStock==null) fStock =new FStock();
        return fStock;
    }
    public List<FStock> getAllFStock() {
        FStockServiceRest.FStockAllAsyncTask asyncTask = (FStockServiceRest.FStockAllAsyncTask) new FStockServiceRest.FStockAllAsyncTask(apiAuthenticationClient);
        List<FStock> listFStock = new ArrayList<>();
        try {
            listFStock = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFStock;

    }
    public void createFStock(FStock fStock) {
//        new FStockCreateAsyncTask(apiAuthenticationClient, fStock).execute();
        new FStockServiceRest.FStockCrudAsyncTask(apiAuthenticationClient, fStock).execute();
    }
    public void updateFStock(Integer id, FStock fStock) {
//        new FStockUpdateAsyncTask(apiAuthenticationClient, id, fStock).execute();
        new FStockServiceRest.FStockCrudAsyncTask(apiAuthenticationClient, id, fStock).execute();
    }
    public void deleteFStock(Integer id) {
//        new FStockDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FStockServiceRest.FStockCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FStockCrudAsyncTask extends AsyncTask<Void, Void, FStock> {

        String operation = "";
        FStock newFStock = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FStockCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FStockCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FStock newFStock){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFStock = newFStock;
            operation = "ADD_NEW";
        }
        private FStockCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FStock updateFStock){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFStock = updateFStock;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FStockCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FStock doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FStock> response = restTemplate.exchange(url, HttpMethod.POST, FStock.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFStock, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FStock> response = restTemplate.postForEntity(url, httpEntity,  FStock.class);
                ResponseEntity<FStock> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFStock";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFStock, apiAuthenticationClient.getRequestHeaders()), FStock.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFStockInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFStock, apiAuthenticationClient.getRequestHeaders()), FStock.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFStock/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FStock.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFStockById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FStock.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FStock();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FStock();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FStock();
            }
        }

        @Override
        protected void onPostExecute(FStock result) {
            dismissProgressDialog();
//            if(result==null) result = new FStock();
//            displayResponseFStock(result);
        }
    }

    public class FStockAllAsyncTask extends  AsyncTask<Void, Void, List<FStock>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FStockAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FStock> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFStock";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FStock[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FStock[].class);
                List<FStock> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FStock>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FStock>();
            }
        }

        @Override
        protected void onPostExecute(List<FStock> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FStock();
//            displayResponseFStock(result);
        }


    }


}
