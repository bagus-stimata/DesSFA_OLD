package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FSubArea;
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

public class FSubAreaServiceRest {
    protected static final String TAG = FSubAreaServiceRest.class.getSimpleName();
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

    public FSubAreaServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FSubArea getFSubAreaById(int id) {
        FSubAreaServiceRest.FSubAreaCrudAsyncTask asyncTask = (FSubAreaServiceRest.FSubAreaCrudAsyncTask) new FSubAreaServiceRest.FSubAreaCrudAsyncTask(apiAuthenticationClient, id, true);
        FSubArea fSubArea = null;
        try {
            fSubArea = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fSubArea==null) fSubArea =new FSubArea();
        return fSubArea;
    }

    public List<FSubArea> getAllFSubArea() {
        FSubAreaServiceRest.FSubAreaAllAsyncTask asyncTask = (FSubAreaServiceRest.FSubAreaAllAsyncTask) new FSubAreaServiceRest.FSubAreaAllAsyncTask(apiAuthenticationClient);
        List<FSubArea> listFSubArea = new ArrayList<>();
        try {
            listFSubArea = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFSubArea;

    }
    public List<FSubArea> getAllFSubAreaByParentId(int parentId) {
        FSubAreaServiceRest.FSubAreaAllAsyncTask asyncTask = (FSubAreaServiceRest.FSubAreaAllAsyncTask) new FSubAreaServiceRest.FSubAreaAllAsyncTask(apiAuthenticationClient, parentId);
        List<FSubArea> listFSubArea = new ArrayList<>();
        try {
            listFSubArea = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFSubArea;
    }
    public List<FSubArea> getAllFSubAreaByListParentId(List<Integer> listParentId) {
        FSubAreaServiceRest.FSubAreaAllAsyncTask asyncTask = (FSubAreaServiceRest.FSubAreaAllAsyncTask) new FSubAreaServiceRest.FSubAreaAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FSubArea> listFSubArea = new ArrayList<>();
        try {
            listFSubArea = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFSubArea;
    }

    public void createFSubArea(FSubArea fSubArea) {
//        new FSubAreaCreateAsyncTask(apiAuthenticationClient, fSubArea).execute();
        new FSubAreaServiceRest.FSubAreaCrudAsyncTask(apiAuthenticationClient, fSubArea).execute();
    }
    public void updateFSubArea(Integer id, FSubArea fSubArea) {
//        new FSubAreaUpdateAsyncTask(apiAuthenticationClient, id, fSubArea).execute();
        new FSubAreaServiceRest.FSubAreaCrudAsyncTask(apiAuthenticationClient, id, fSubArea).execute();
    }
    public void deleteFSubArea(Integer id) {
//        new FSubAreaDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FSubAreaServiceRest.FSubAreaCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FSubAreaCrudAsyncTask extends AsyncTask<Void, Void, FSubArea> {

        String operation = "";
        FSubArea newFSubArea = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FSubAreaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FSubAreaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FSubArea newFSubArea){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFSubArea = newFSubArea;
            operation = "ADD_NEW";
        }
        private FSubAreaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FSubArea updateFSubArea){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFSubArea = updateFSubArea;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FSubAreaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FSubArea doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FSubArea> response = restTemplate.exchange(url, HttpMethod.POST, FSubArea.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFSubArea, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FSubArea> response = restTemplate.postForEntity(url, httpEntity,  FSubArea.class);
                ResponseEntity<FSubArea> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFSubArea";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFSubArea, apiAuthenticationClient.getRequestHeaders()), FSubArea.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFSubAreaInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFSubArea, apiAuthenticationClient.getRequestHeaders()), FSubArea.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFSubArea/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSubArea.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFSubAreaById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSubArea.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FSubArea();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FSubArea();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FSubArea();
            }
        }

        @Override
        protected void onPostExecute(FSubArea result) {
            dismissProgressDialog();
//            if(result==null) result = new FSubArea();
//            displayResponseFSubArea(result);
        }
    }

    public class FSubAreaAllAsyncTask extends  AsyncTask<Void, Void, List<FSubArea>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FSubAreaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FSubAreaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FSubAreaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FSubArea> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFSubArea";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FSubArea> list = new ArrayList<>();
            try {
                ResponseEntity<FSubArea[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFSubArea";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSubArea[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFSubAreaByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSubArea[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFSubAreaByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FSubArea[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FSubArea>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FSubArea>();
            }
        }

        @Override
        protected void onPostExecute(List<FSubArea> result) {
            dismissProgressDialog();
        }
    }


}
