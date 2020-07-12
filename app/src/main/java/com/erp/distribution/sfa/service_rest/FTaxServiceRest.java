package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FTax;
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

public class FTaxServiceRest {
    protected static final String TAG = FTaxServiceRest.class.getSimpleName();
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

    public FTaxServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FTax getFTaxById(int id) {
        FTaxServiceRest.FTaxCrudAsyncTask asyncTask = (FTaxServiceRest.FTaxCrudAsyncTask) new FTaxServiceRest.FTaxCrudAsyncTask(apiAuthenticationClient, id, true);
        FTax fTax = null;
        try {
//            fTax = asyncTask.execute().get();
            fTax = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        if (fTax==null) fTax =new FTax();
        return fTax;
    }
    public List<FTax> getAllFTax() {
        FTaxServiceRest.FTaxAllAsyncTask asyncTask = (FTaxServiceRest.FTaxAllAsyncTask) new FTaxServiceRest.FTaxAllAsyncTask(apiAuthenticationClient);
        List<FTax> listFTax = new ArrayList<>();
        try {
//            fTax = asyncTask.execute().get();
            listFTax = asyncTask.execute().get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
//        if (fTax==null) fTax =new FTax();
        return listFTax;

    }
    public void createFTax(FTax fTax) {
//        new FTaxCreateAsyncTask(apiAuthenticationClient, fTax).execute();
        new FTaxServiceRest.FTaxCrudAsyncTask(apiAuthenticationClient, fTax).execute();
    }
    public void updateFTax(Integer id, FTax fTax) {
//        new FTaxUpdateAsyncTask(apiAuthenticationClient, id, fTax).execute();
        new FTaxServiceRest.FTaxCrudAsyncTask(apiAuthenticationClient, id, fTax).execute();
    }
    public void deleteFTax(Integer id) {
//        new FTaxDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FTaxServiceRest.FTaxCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FTaxCrudAsyncTask extends AsyncTask<Void, Void, FTax> {

        String operation = "";
        FTax newFTax = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FTaxCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FTaxCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FTax newFTax){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFTax = newFTax;
            operation = "ADD_NEW";
        }
        private FTaxCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FTax updateFTax){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFTax = updateFTax;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FTaxCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FTax doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FTax> response = restTemplate.exchange(url, HttpMethod.POST, FTax.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFTax, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FTax> response = restTemplate.postForEntity(url, httpEntity,  FTax.class);
                ResponseEntity<FTax> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFTax";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFTax, apiAuthenticationClient.getRequestHeaders()), FTax.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFTaxInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFTax, apiAuthenticationClient.getRequestHeaders()), FTax.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFTax/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FTax.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFTaxById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FTax.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FTax();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FTax();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FTax();
            }
        }

        @Override
        protected void onPostExecute(FTax result) {
            dismissProgressDialog();
//            if(result==null) result = new FTax();
//            displayResponseFTax(result);
        }
    }

    public class FTaxAllAsyncTask extends  AsyncTask<Void, Void, List<FTax>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FTaxAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FTax> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFTax";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FTax[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FTax[].class);
                List<FTax> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FTax>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FTax>();
            }
        }

        @Override
        protected void onPostExecute(List<FTax> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FTax();
//            displayResponseFTax(result);
        }


    }


}
