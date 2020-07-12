package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPurchasedExpenses;
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

public class FtPurchasedExpensesServiceRest {
    protected static final String TAG = FtPurchasedExpensesServiceRest.class.getSimpleName();
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

    public FtPurchasedExpensesServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPurchasedExpenses getFtPurchasedExpensesById(Long id) {
        FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask asyncTask = (FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask) new FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPurchasedExpenses ftPurchasedExpenses = null;
        try {
            ftPurchasedExpenses = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPurchasedExpenses==null) ftPurchasedExpenses =new FtPurchasedExpenses();
        return ftPurchasedExpenses;
    }
    public List<FtPurchasedExpenses> getAllFtPurchasedExpenses() {
        FtPurchasedExpensesServiceRest.FtPurchasedExpensesAllAsyncTask asyncTask = (FtPurchasedExpensesServiceRest.FtPurchasedExpensesAllAsyncTask) new FtPurchasedExpensesServiceRest.FtPurchasedExpensesAllAsyncTask(apiAuthenticationClient);
        List<FtPurchasedExpenses> listFtPurchasedExpenses = new ArrayList<>();
        try {
            listFtPurchasedExpenses = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPurchasedExpenses;

    }
    public void createFtPurchasedExpenses(FtPurchasedExpenses ftPurchasedExpenses) {
//        new FtPurchasedExpensesCreateAsyncTask(apiAuthenticationClient, ftPurchasedExpenses).execute();
        new FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask(apiAuthenticationClient, ftPurchasedExpenses).execute();
    }
    public void updateFtPurchasedExpenses(Long id, FtPurchasedExpenses ftPurchasedExpenses) {
//        new FtPurchasedExpensesUpdateAsyncTask(apiAuthenticationClient, id, ftPurchasedExpenses).execute();
        new FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask(apiAuthenticationClient, id, ftPurchasedExpenses).execute();
    }
    public void deleteFtPurchasedExpenses(Long id) {
//        new FtPurchasedExpensesDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPurchasedExpensesServiceRest.FtPurchasedExpensesCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPurchasedExpensesCrudAsyncTask extends AsyncTask<Void, Void, FtPurchasedExpenses> {

        String operation = "";
        FtPurchasedExpenses newFtPurchasedExpenses = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPurchasedExpensesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPurchasedExpensesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPurchasedExpenses newFtPurchasedExpenses){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchasedExpenses = newFtPurchasedExpenses;
            operation = "ADD_NEW";
        }
        private FtPurchasedExpensesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPurchasedExpenses updateFtPurchasedExpenses){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPurchasedExpenses = updateFtPurchasedExpenses;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPurchasedExpensesCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtPurchasedExpenses doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPurchasedExpenses> response = restTemplate.exchange(url, HttpMethod.POST, FtPurchasedExpenses.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPurchasedExpenses, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPurchasedExpenses> response = restTemplate.postForEntity(url, httpEntity,  FtPurchasedExpenses.class);
                ResponseEntity<FtPurchasedExpenses> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPurchasedExpenses";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPurchasedExpenses, apiAuthenticationClient.getRequestHeaders()), FtPurchasedExpenses.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPurchasedExpensesInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPurchasedExpenses, apiAuthenticationClient.getRequestHeaders()), FtPurchasedExpenses.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPurchasedExpenses/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedExpenses.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPurchasedExpensesById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedExpenses.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPurchasedExpenses();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchasedExpenses();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPurchasedExpenses();
            }
        }

        @Override
        protected void onPostExecute(FtPurchasedExpenses result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPurchasedExpenses();
//            displayResponseFtPurchasedExpenses(result);
        }
    }

    public class FtPurchasedExpensesAllAsyncTask extends  AsyncTask<Void, Void, List<FtPurchasedExpenses>>{
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPurchasedExpensesAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPurchasedExpenses> doInBackground(Void... voids) {
            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPurchasedExpenses";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<FtPurchasedExpenses[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPurchasedExpenses[].class);
                List<FtPurchasedExpenses> list = Arrays.asList(response.getBody());
                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchasedExpenses>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPurchasedExpenses>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPurchasedExpenses> result) {
            dismissProgressDialog();
//            if(result.size()==0) result = new FtPurchasedExpenses();
//            displayResponseFtPurchasedExpenses(result);
        }


    }


}
