package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterialGroup2;
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

public class FMaterialGroup2ServiceRest {
    protected static final String TAG = FMaterialGroup2ServiceRest.class.getSimpleName();
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

    public FMaterialGroup2ServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterialGroup2 getFMaterialGroup2ById(int id) {
        FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask asyncTask = (FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask) new FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterialGroup2 fMaterialGroup2 = null;
        try {
            fMaterialGroup2 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterialGroup2==null) fMaterialGroup2 =new FMaterialGroup2();
        return fMaterialGroup2;
    }

    public List<FMaterialGroup2> getAllFMaterialGroup2() {
        FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask asyncTask = (FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask) new FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask(apiAuthenticationClient);
        List<FMaterialGroup2> listFMaterialGroup2 = new ArrayList<>();
        try {
            listFMaterialGroup2 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup2;

    }
    public List<FMaterialGroup2> getAllFMaterialGroup2ByParentId(int parentId) {
        FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask asyncTask = (FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask) new FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask(apiAuthenticationClient, parentId);
        List<FMaterialGroup2> listFMaterialGroup2 = new ArrayList<>();
        try {
            listFMaterialGroup2 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup2;
    }
    public List<FMaterialGroup2> getAllFMaterialGroup2ByListParentId(List<Integer> listParentId) {
        FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask asyncTask = (FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask) new FMaterialGroup2ServiceRest.FMaterialGroup2AllAsyncTask(apiAuthenticationClient, listParentId);
        List<FMaterialGroup2> listFMaterialGroup2 = new ArrayList<>();
        try {
            listFMaterialGroup2 = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialGroup2;
    }

    public void createFMaterialGroup2(FMaterialGroup2 fMaterialGroup2) {
//        new FMaterialGroup2CreateAsyncTask(apiAuthenticationClient, fMaterialGroup2).execute();
        new FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask(apiAuthenticationClient, fMaterialGroup2).execute();
    }
    public void updateFMaterialGroup2(Integer id, FMaterialGroup2 fMaterialGroup2) {
//        new FMaterialGroup2UpdateAsyncTask(apiAuthenticationClient, id, fMaterialGroup2).execute();
        new FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask(apiAuthenticationClient, id, fMaterialGroup2).execute();
    }
    public void deleteFMaterialGroup2(Integer id) {
//        new FMaterialGroup2DeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialGroup2ServiceRest.FMaterialGroup2CrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialGroup2CrudAsyncTask extends AsyncTask<Void, Void, FMaterialGroup2> {

        String operation = "";
        FMaterialGroup2 newFMaterialGroup2 = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialGroup2CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialGroup2CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterialGroup2 newFMaterialGroup2){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup2 = newFMaterialGroup2;
            operation = "ADD_NEW";
        }
        private FMaterialGroup2CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterialGroup2 updateFMaterialGroup2){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialGroup2 = updateFMaterialGroup2;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialGroup2CrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FMaterialGroup2 doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterialGroup2> response = restTemplate.exchange(url, HttpMethod.POST, FMaterialGroup2.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterialGroup2, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterialGroup2> response = restTemplate.postForEntity(url, httpEntity,  FMaterialGroup2.class);
                ResponseEntity<FMaterialGroup2> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterialGroup2";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterialGroup2, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialGroup2Info/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterialGroup2, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterialGroup2/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialGroup2ById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterialGroup2();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup2();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialGroup2();
            }
        }

        @Override
        protected void onPostExecute(FMaterialGroup2 result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterialGroup2();
//            displayResponseFMaterialGroup2(result);
        }
    }

    public class FMaterialGroup2AllAsyncTask extends  AsyncTask<Void, Void, List<FMaterialGroup2>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialGroup2AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialGroup2AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialGroup2AllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterialGroup2> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterialGroup2";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterialGroup2> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterialGroup2[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterialGroup2";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialGroup2ByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterialGroup2ByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterialGroup2[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup2>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialGroup2>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterialGroup2> result) {
            dismissProgressDialog();
        }
    }


}
