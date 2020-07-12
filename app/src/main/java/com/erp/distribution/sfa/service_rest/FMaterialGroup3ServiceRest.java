package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterialGroup3;
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

public class FMaterialGroup3ServiceRest {
    protected static final String TAG = FMaterialGroup3ServiceRest.class.getSimpleName();
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

    public FMaterialGroup3ServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterialGroup3 getFMaterialGroup3ById(int id) {
        FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask asyncTask = (FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask) new FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterialGroup3 fMaterialGroup3 = null;
        try {
            fMaterialGroup3 = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterialGroup3==null) fMaterialGroup3 =new FMaterialGroup3();
        return fMaterialGroup3;
    }

    public List<FMaterialGroup3> getAllFMaterialGroup3() {
        FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask asyncTask = (FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask) new FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask(apiAuthenticationClient);
        List<FMaterialGroup3> listFMaterialGroup3 = new ArrayList<>();
        try {
            listFMaterialGroup3 = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup3;

    }
    public List<FMaterialGroup3> getAllFMaterialGroup3ByParentId(int parentId) {
        FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask asyncTask = (FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask) new FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask(apiAuthenticationClient, parentId);
        List<FMaterialGroup3> listFMaterialGroup3 = new ArrayList<>();
        try {
            listFMaterialGroup3 = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup3;
    }
    public List<FMaterialGroup3> getAllFMaterialGroup3ByListParentId(List<Integer> listParentId) {
        FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask asyncTask = (FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask) new FMaterialGroup3ServiceRest.FMaterialGroup3AllAsyncTask(apiAuthenticationClient, listParentId);
        List<FMaterialGroup3> listFMaterialGroup3 = new ArrayList<>();
        try {
            listFMaterialGroup3 = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup3;
    }
  
  
    public void createFMaterialGroup3(FMaterialGroup3 fMaterialGroup3) {
//        new FMaterialGroup3CreateAsyncTask(apiAuthenticationClient, fMaterialGroup3).execute();
        new FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask(apiAuthenticationClient, fMaterialGroup3).execute();
    }
    public void updateFMaterialGroup3(Integer id, FMaterialGroup3 fMaterialGroup3) {
//        new FMaterialGroup3UpdateAsyncTask(apiAuthenticationClient, id, fMaterialGroup3).execute();
        new FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask(apiAuthenticationClient, id, fMaterialGroup3).execute();
    }
    public void deleteFMaterialGroup3(Integer id) {
//        new FMaterialGroup3DeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialGroup3ServiceRest.FMaterialGroup3CrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialGroup3CrudAsyncTask extends AsyncTask<Void, Void, FMaterialGroup3> {

        String operation = "";
        FMaterialGroup3 newFMaterialGroup3 = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialGroup3CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialGroup3CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterialGroup3 newFMaterialGroup3){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup3 = newFMaterialGroup3;
            operation = "ADD_NEW";
        }
        private FMaterialGroup3CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterialGroup3 updateFMaterialGroup3){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup3 = updateFMaterialGroup3;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialGroup3CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FMaterialGroup3 doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterialGroup3> response = restTemplate.exchange(url, HttpMethod.POST, FMaterialGroup3.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterialGroup3, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterialGroup3> response = restTemplate.postForEntity(url, httpEntity,  FMaterialGroup3.class);
                ResponseEntity<FMaterialGroup3> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterialGroup3";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterialGroup3, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialGroup3Info/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterialGroup3, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterialGroup3/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialGroup3ById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterialGroup3();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup3();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup3();
            }
        }

        @Override
        protected void onPostExecute(FMaterialGroup3 result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterialGroup3();
//            displayResponseFMaterialGroup3(result);
        }
    }

    public class FMaterialGroup3AllAsyncTask extends  AsyncTask<Void, Void, List<FMaterialGroup3>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialGroup3AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialGroup3AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialGroup3AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterialGroup3> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterialGroup3";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterialGroup3> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterialGroup3[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterialGroup3";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialGroup3ByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterialGroup3ByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup3[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup3>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup3>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterialGroup3> result) {
            dismissProgressDialog();
        }
    }


}
