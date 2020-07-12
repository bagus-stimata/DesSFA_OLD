package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterialGroup1;
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

public class FMaterialGroup1ServiceRest {
    protected static final String TAG = FMaterialGroup1ServiceRest.class.getSimpleName();
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

    public FMaterialGroup1ServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterialGroup1 getFMaterialGroup1ById(int id) {
        FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask asyncTask = (FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask) new FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterialGroup1 fMaterialGroup1 = null;
        try {
            fMaterialGroup1 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterialGroup1==null) fMaterialGroup1 =new FMaterialGroup1();
        return fMaterialGroup1;
    }

    public List<FMaterialGroup1> getAllFMaterialGroup1() {
        FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask asyncTask = (FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask) new FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask(apiAuthenticationClient);
        List<FMaterialGroup1> listFMaterialGroup1 = new ArrayList<>();
        try {
            listFMaterialGroup1 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup1;

    }
    public List<FMaterialGroup1> getAllFMaterialGroup1ByDivision(int divisionBean) {
        FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask asyncTask = (FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask) new FMaterialGroup1ServiceRest.FMaterialGroup1AllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FMaterialGroup1> listFMaterialGroup1 = new ArrayList<>();
        try {
            listFMaterialGroup1 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup1;

    }

    public void createFMaterialGroup1(FMaterialGroup1 fMaterialGroup1) {
//        new FMaterialGroup1CreateAsyncTask(apiAuthenticationClient, fMaterialGroup1).execute();
        new FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask(apiAuthenticationClient, fMaterialGroup1).execute();
    }
    public void updateFMaterialGroup1(Integer id, FMaterialGroup1 fMaterialGroup1) {
//        new FMaterialGroup1UpdateAsyncTask(apiAuthenticationClient, id, fMaterialGroup1).execute();
        new FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask(apiAuthenticationClient, id, fMaterialGroup1).execute();
    }
    public void deleteFMaterialGroup1(Integer id) {
//        new FMaterialGroup1DeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialGroup1ServiceRest.FMaterialGroup1CrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialGroup1CrudAsyncTask extends AsyncTask<Void, Void, FMaterialGroup1> {

        String operation = "";
        FMaterialGroup1 newFMaterialGroup1 = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialGroup1CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialGroup1CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterialGroup1 newFMaterialGroup1){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup1 = newFMaterialGroup1;
            operation = "ADD_NEW";
        }
        private FMaterialGroup1CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterialGroup1 updateFMaterialGroup1){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup1 = updateFMaterialGroup1;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialGroup1CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FMaterialGroup1 doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterialGroup1> response = restTemplate.exchange(url, HttpMethod.POST, FMaterialGroup1.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterialGroup1, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterialGroup1> response = restTemplate.postForEntity(url, httpEntity,  FMaterialGroup1.class);
                ResponseEntity<FMaterialGroup1> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterialGroup1";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterialGroup1, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialGroup1Info/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterialGroup1, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterialGroup1/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialGroup1ById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterialGroup1();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup1();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup1();
            }
        }

        @Override
        protected void onPostExecute(FMaterialGroup1 result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterialGroup1();
//            displayResponseFMaterialGroup1(result);
        }
    }

    public class FMaterialGroup1AllAsyncTask extends  AsyncTask<Void, Void, List<FMaterialGroup1>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialGroup1AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialGroup1AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialGroup1AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterialGroup1> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterialGroup1";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterialGroup1> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterialGroup1[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterialGroup1";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialGroup1ByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterialGroup1";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup1[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup1>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup1>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterialGroup1> result) {
            dismissProgressDialog();
        }
    }


}
