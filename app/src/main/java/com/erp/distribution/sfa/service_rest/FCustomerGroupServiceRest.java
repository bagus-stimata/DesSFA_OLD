package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FCustomerGroup;
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

public class FCustomerGroupServiceRest {
    protected static final String TAG = FCustomerGroupServiceRest.class.getSimpleName();
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

    public FCustomerGroupServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FCustomerGroup getFCustomerGroupById(int id) {
        FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask asyncTask = (FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask) new FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask(apiAuthenticationClient, id, true);
        FCustomerGroup fCustomerGroup = null;
        try {
            fCustomerGroup = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fCustomerGroup==null) fCustomerGroup =new FCustomerGroup();
        return fCustomerGroup;
    }
   
    public List<FCustomerGroup> getAllFCustomerGroup() {
        FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask asyncTask = (FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask) new FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask(apiAuthenticationClient);
        List<FCustomerGroup> listFCustomerGroup = new ArrayList<>();
        try {
            listFCustomerGroup = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFCustomerGroup;

    }
    public List<FCustomerGroup> getAllFCustomerGroupByDivision(int divisionBean) {
        FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask asyncTask = (FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask) new FCustomerGroupServiceRest.FCustomerGroupAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FCustomerGroup> listFCustomerGroup = new ArrayList<>();
        try {
//            fCustomerGroup = asyncTask.execute().get();
            listFCustomerGroup = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFCustomerGroup;

    }


    public void createFCustomerGroup(FCustomerGroup fCustomerGroup) {
//        new FCustomerGroupCreateAsyncTask(apiAuthenticationClient, fCustomerGroup).execute();
        new FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask(apiAuthenticationClient, fCustomerGroup).execute();
    }
    public void updateFCustomerGroup(Integer id, FCustomerGroup fCustomerGroup) {
//        new FCustomerGroupUpdateAsyncTask(apiAuthenticationClient, id, fCustomerGroup).execute();
        new FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask(apiAuthenticationClient, id, fCustomerGroup).execute();
    }
    public void deleteFCustomerGroup(Integer id) {
//        new FCustomerGroupDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FCustomerGroupServiceRest.FCustomerGroupCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FCustomerGroupCrudAsyncTask extends AsyncTask<Void, Void, FCustomerGroup> {

        String operation = "";
        FCustomerGroup newFCustomerGroup = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FCustomerGroupCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FCustomerGroupCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FCustomerGroup newFCustomerGroup){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomerGroup = newFCustomerGroup;
            operation = "ADD_NEW";
        }
        private FCustomerGroupCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FCustomerGroup updateFCustomerGroup){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomerGroup = updateFCustomerGroup;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FCustomerGroupCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FCustomerGroup doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FCustomerGroup> response = restTemplate.exchange(url, HttpMethod.POST, FCustomerGroup.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFCustomerGroup, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FCustomerGroup> response = restTemplate.postForEntity(url, httpEntity,  FCustomerGroup.class);
                ResponseEntity<FCustomerGroup> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFCustomerGroup";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFCustomerGroup, apiAuthenticationClient.getRequestHeaders()), FCustomerGroup.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFCustomerGroupInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFCustomerGroup, apiAuthenticationClient.getRequestHeaders()), FCustomerGroup.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFCustomerGroup/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerGroup.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFCustomerGroupById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerGroup.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FCustomerGroup();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomerGroup();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomerGroup();
            }
        }

        @Override
        protected void onPostExecute(FCustomerGroup result) {
            dismissProgressDialog();
//            if(result==null) result = new FCustomerGroup();
//            displayResponseFCustomerGroup(result);
        }
    }


    public class FCustomerGroupAllAsyncTask extends  AsyncTask<Void, Void, List<FCustomerGroup>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FCustomerGroupAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FCustomerGroupAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FCustomerGroupAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FCustomerGroup> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFCustomerGroup";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FCustomerGroup> list = new ArrayList<>();
            try {
                ResponseEntity<FCustomerGroup[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFCustomerGroup";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerGroup[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFCustomerGroupByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerGroup[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFCustomerGroup";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FCustomerGroup[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomerGroup>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomerGroup>();
            }
        }

        @Override
        protected void onPostExecute(List<FCustomerGroup> result) {
            dismissProgressDialog();
        }
    }


}
