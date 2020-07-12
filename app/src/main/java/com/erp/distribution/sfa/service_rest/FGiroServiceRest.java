package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FGiro;
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

public class FGiroServiceRest {
    protected static final String TAG = FGiroServiceRest.class.getSimpleName();
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

    public FGiroServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FGiro getFGiroById(int id) {
        FGiroServiceRest.FGiroCrudAsyncTask asyncTask = (FGiroServiceRest.FGiroCrudAsyncTask) new FGiroServiceRest.FGiroCrudAsyncTask(apiAuthenticationClient, id, true);
        FGiro fGiro = null;
        try {
            fGiro = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fGiro==null) fGiro =new FGiro();
        return fGiro;
    }

    public List<FGiro> getAllFGiro() {
        FGiroServiceRest.FGiroAllAsyncTask asyncTask = (FGiroServiceRest.FGiroAllAsyncTask) new FGiroServiceRest.FGiroAllAsyncTask(apiAuthenticationClient);
        List<FGiro> listFGiro = new ArrayList<>();
        try {
            listFGiro = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFGiro;

    }
    public List<FGiro> getAllFGiroByDivision(int divisionBean) {
        FGiroServiceRest.FGiroAllAsyncTask asyncTask = (FGiroServiceRest.FGiroAllAsyncTask) new FGiroServiceRest.FGiroAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FGiro> listFGiro = new ArrayList<>();
        try {
            listFGiro = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFGiro;

    }

    public void createFGiro(FGiro fGiro) {
//        new FGiroCreateAsyncTask(apiAuthenticationClient, fGiro).execute();
        new FGiroServiceRest.FGiroCrudAsyncTask(apiAuthenticationClient, fGiro).execute();
    }
    public void updateFGiro(Integer id, FGiro fGiro) {
//        new FGiroUpdateAsyncTask(apiAuthenticationClient, id, fGiro).execute();
        new FGiroServiceRest.FGiroCrudAsyncTask(apiAuthenticationClient, id, fGiro).execute();
    }
    public void deleteFGiro(Integer id) {
//        new FGiroDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FGiroServiceRest.FGiroCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FGiroCrudAsyncTask extends AsyncTask<Void, Void, FGiro> {

        String operation = "";
        FGiro newFGiro = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FGiroCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FGiroCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FGiro newFGiro){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFGiro = newFGiro;
            operation = "ADD_NEW";
        }
        private FGiroCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FGiro updateFGiro){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFGiro = updateFGiro;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FGiroCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FGiro doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FGiro> response = restTemplate.exchange(url, HttpMethod.POST, FGiro.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFGiro, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FGiro> response = restTemplate.postForEntity(url, httpEntity,  FGiro.class);
                ResponseEntity<FGiro> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFGiro";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFGiro, apiAuthenticationClient.getRequestHeaders()), FGiro.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFGiroInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFGiro, apiAuthenticationClient.getRequestHeaders()), FGiro.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFGiro/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FGiro.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFGiroById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FGiro.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FGiro();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FGiro();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FGiro();
            }
        }

        @Override
        protected void onPostExecute(FGiro result) {
            dismissProgressDialog();
//            if(result==null) result = new FGiro();
//            displayResponseFGiro(result);
        }
    }

    public class FGiroAllAsyncTask extends  AsyncTask<Void, Void, List<FGiro>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FGiroAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FGiroAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FGiroAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FGiro> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFGiro";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FGiro> list = new ArrayList<>();
            try {
                ResponseEntity<FGiro[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFGiro";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FGiro[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFGiroByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FGiro[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFGiro";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FGiro[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FGiro>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FGiro>();
            }
        }

        @Override
        protected void onPostExecute(List<FGiro> result) {
            dismissProgressDialog();
        }
    }


}
