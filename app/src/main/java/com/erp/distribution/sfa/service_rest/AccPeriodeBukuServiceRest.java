package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model_acc_cb.AccPeriodeBuku;
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

public class AccPeriodeBukuServiceRest {
    protected static final String TAG = AccPeriodeBukuServiceRest.class.getSimpleName();
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

    public AccPeriodeBukuServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public AccPeriodeBuku getAccPeriodeBukuById(int id) {
        AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask asyncTask = (AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask) new AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask(apiAuthenticationClient, id, true);
        AccPeriodeBuku accPeriodeBuku = null;
        try {
            accPeriodeBuku = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (accPeriodeBuku==null) accPeriodeBuku =new AccPeriodeBuku();
        return accPeriodeBuku;
    }
    public List<AccPeriodeBuku> getAllAccPeriodeBuku() {
        AccPeriodeBukuServiceRest.AccPeriodeBukuAllAsyncTask asyncTask = (AccPeriodeBukuServiceRest.AccPeriodeBukuAllAsyncTask) new AccPeriodeBukuServiceRest.AccPeriodeBukuAllAsyncTask(apiAuthenticationClient);
        List<AccPeriodeBuku> listAccPeriodeBuku = new ArrayList<>();
        try {
            listAccPeriodeBuku = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listAccPeriodeBuku;

    }
    public void createAccPeriodeBuku(AccPeriodeBuku fArea) {
//        new AccPeriodeBukuCreateAsyncTask(apiAuthenticationClient, fArea).execute();
        new AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask(apiAuthenticationClient, fArea).execute();
    }
    public void updateAccPeriodeBuku(Integer id, AccPeriodeBuku fArea) {
//        new AccPeriodeBukuUpdateAsyncTask(apiAuthenticationClient, id, fArea).execute();
        new AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask(apiAuthenticationClient, id, fArea).execute();
    }
    public void deleteAccPeriodeBuku(Integer id) {
//        new AccPeriodeBukuDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new AccPeriodeBukuServiceRest.AccPeriodeBukuCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class AccPeriodeBukuCrudAsyncTask extends AsyncTask<Void, Void, AccPeriodeBuku> {

        String operation = "";
        AccPeriodeBuku newAccPeriodeBuku = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccPeriodeBukuCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private AccPeriodeBukuCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  AccPeriodeBuku newAccPeriodeBuku){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccPeriodeBuku = newAccPeriodeBuku;
            operation = "ADD_NEW";
        }
        private AccPeriodeBukuCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, AccPeriodeBuku updateAccPeriodeBuku){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newAccPeriodeBuku = updateAccPeriodeBuku;
            this.id = id_update;
            operation = "UPDATE";
        }
        private AccPeriodeBukuCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected AccPeriodeBuku doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<AccPeriodeBuku> response = restTemplate.exchange(url, HttpMethod.POST, AccPeriodeBuku.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newAccPeriodeBuku, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<AccPeriodeBuku> response = restTemplate.postForEntity(url, httpEntity,  AccPeriodeBuku.class);
                ResponseEntity<AccPeriodeBuku> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createAccPeriodeBuku";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newAccPeriodeBuku, apiAuthenticationClient.getRequestHeaders()), AccPeriodeBuku.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateAccPeriodeBukuInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newAccPeriodeBuku, apiAuthenticationClient.getRequestHeaders()), AccPeriodeBuku.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteAccPeriodeBuku/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccPeriodeBuku.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getAccPeriodeBukuById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccPeriodeBuku.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new AccPeriodeBuku();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccPeriodeBuku();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new AccPeriodeBuku();
            }
        }

        @Override
        protected void onPostExecute(AccPeriodeBuku result) {
            dismissProgressDialog();
//            if(result==null) result = new AccPeriodeBuku();
//            displayResponseAccPeriodeBuku(result);
        }
    }

    public class AccPeriodeBukuAllAsyncTask extends  AsyncTask<Void, Void, List<AccPeriodeBuku>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private AccPeriodeBukuAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<AccPeriodeBuku> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllAccPeriodeBuku";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<AccPeriodeBuku[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), AccPeriodeBuku[].class);
                List<AccPeriodeBuku> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccPeriodeBuku>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<AccPeriodeBuku>();
            }
        }

        @Override
        protected void onPostExecute(List<AccPeriodeBuku> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new AccPeriodeBuku();
//            displayResponseAccPeriodeBuku(result);
        }


    }


}
