package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FExpedisi;
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

public class FExpedisiServiceRest {
    protected static final String TAG = FExpedisiServiceRest.class.getSimpleName();
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

    public FExpedisiServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FExpedisi getFExpedisiById(int id) {
        FExpedisiServiceRest.FExpedisiCrudAsyncTask asyncTask = (FExpedisiServiceRest.FExpedisiCrudAsyncTask) new FExpedisiServiceRest.FExpedisiCrudAsyncTask(apiAuthenticationClient, id, true);
        FExpedisi fExpedisi = null;
        try {
            fExpedisi = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fExpedisi==null) fExpedisi =new FExpedisi();
        return fExpedisi;
    }

    public List<FExpedisi> getAllFExpedisi() {
        FExpedisiServiceRest.FExpedisiAllAsyncTask asyncTask = (FExpedisiServiceRest.FExpedisiAllAsyncTask) new FExpedisiServiceRest.FExpedisiAllAsyncTask(apiAuthenticationClient);
        List<FExpedisi> listFExpedisi = new ArrayList<>();
        try {
            listFExpedisi = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFExpedisi;

    }
    public List<FExpedisi> getAllFExpedisiByDivision(int divisionBean) {
        FExpedisiServiceRest.FExpedisiAllAsyncTask asyncTask = (FExpedisiServiceRest.FExpedisiAllAsyncTask) new FExpedisiServiceRest.FExpedisiAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FExpedisi> listFExpedisi = new ArrayList<>();
        try {
            listFExpedisi = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFExpedisi;

    }


    public void createFExpedisi(FExpedisi fExpedisi) {
//        new FExpedisiCreateAsyncTask(apiAuthenticationClient, fExpedisi).execute();
        new FExpedisiServiceRest.FExpedisiCrudAsyncTask(apiAuthenticationClient, fExpedisi).execute();
    }
    public void updateFExpedisi(Integer id, FExpedisi fExpedisi) {
//        new FExpedisiUpdateAsyncTask(apiAuthenticationClient, id, fExpedisi).execute();
        new FExpedisiServiceRest.FExpedisiCrudAsyncTask(apiAuthenticationClient, id, fExpedisi).execute();
    }
    public void deleteFExpedisi(Integer id) {
//        new FExpedisiDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FExpedisiServiceRest.FExpedisiCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FExpedisiCrudAsyncTask extends AsyncTask<Void, Void, FExpedisi> {

        String operation = "";
        FExpedisi newFExpedisi = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FExpedisiCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FExpedisiCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FExpedisi newFExpedisi){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFExpedisi = newFExpedisi;
            operation = "ADD_NEW";
        }
        private FExpedisiCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FExpedisi updateFExpedisi){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFExpedisi = updateFExpedisi;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FExpedisiCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FExpedisi doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FExpedisi> response = restTemplate.exchange(url, HttpMethod.POST, FExpedisi.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFExpedisi, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FExpedisi> response = restTemplate.postForEntity(url, httpEntity,  FExpedisi.class);
                ResponseEntity<FExpedisi> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFExpedisi";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFExpedisi, apiAuthenticationClient.getRequestHeaders()), FExpedisi.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFExpedisiInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFExpedisi, apiAuthenticationClient.getRequestHeaders()), FExpedisi.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFExpedisi/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FExpedisi.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFExpedisiById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FExpedisi.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FExpedisi();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FExpedisi();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FExpedisi();
            }
        }

        @Override
        protected void onPostExecute(FExpedisi result) {
            dismissProgressDialog();
//            if(result==null) result = new FExpedisi();
//            displayResponseFExpedisi(result);
        }
    }

    public class FExpedisiAllAsyncTask extends  AsyncTask<Void, Void, List<FExpedisi>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FExpedisiAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FExpedisiAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FExpedisiAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FExpedisi> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFExpedisi";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FExpedisi> list = new ArrayList<>();
            try {
                ResponseEntity<FExpedisi[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFExpedisi";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FExpedisi[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFExpedisiByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FExpedisi[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFExpedisi";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FExpedisi[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FExpedisi>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FExpedisi>();
            }
        }

        @Override
        protected void onPostExecute(List<FExpedisi> result) {
            dismissProgressDialog();
        }
    }


}
