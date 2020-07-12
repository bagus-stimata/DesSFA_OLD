package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FPromotionRulesdValidCusts;
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

public class FPromotionRulesdValidCustsServiceRest {
    protected static final String TAG = FPromotionRulesdValidCustsServiceRest.class.getSimpleName();
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

    public FPromotionRulesdValidCustsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FPromotionRulesdValidCusts getFPromotionRulesdValidCustsById(int id) {
        FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask asyncTask = (FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask) new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask(apiAuthenticationClient, id, true);
        FPromotionRulesdValidCusts fPromotionRulesdValidCusts = null;
        try {
            fPromotionRulesdValidCusts = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fPromotionRulesdValidCusts==null) fPromotionRulesdValidCusts =new FPromotionRulesdValidCusts();
        return fPromotionRulesdValidCusts;
    }

    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCusts() {
        FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask asyncTask = (FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask) new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask(apiAuthenticationClient);
        List<FPromotionRulesdValidCusts> listFPromotionRulesdValidCusts = new ArrayList<>();
        try {
            listFPromotionRulesdValidCusts = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdValidCusts;

    }
    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCustsByParentId(int parentId) {
        FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask asyncTask = (FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask) new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FPromotionRulesdValidCusts> listFPromotionRulesdValidCusts = new ArrayList<>();
        try {
            listFPromotionRulesdValidCusts = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdValidCusts;
    }
    public List<FPromotionRulesdValidCusts> getAllFPromotionRulesdValidCustsByListParentId(List<Integer> listParentId) {
        FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask asyncTask = (FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask) new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FPromotionRulesdValidCusts> listFPromotionRulesdValidCusts = new ArrayList<>();
        try {
            listFPromotionRulesdValidCusts = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdValidCusts;
    }

    public void createFPromotionRulesdValidCusts(FPromotionRulesdValidCusts fPromotionRulesdValidCusts) {
//        new FPromotionRulesdValidCustsCreateAsyncTask(apiAuthenticationClient, fPromotionRulesdValidCusts).execute();
        new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask(apiAuthenticationClient, fPromotionRulesdValidCusts).execute();
    }
    public void updateFPromotionRulesdValidCusts(Integer id, FPromotionRulesdValidCusts fPromotionRulesdValidCusts) {
//        new FPromotionRulesdValidCustsUpdateAsyncTask(apiAuthenticationClient, id, fPromotionRulesdValidCusts).execute();
        new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask(apiAuthenticationClient, id, fPromotionRulesdValidCusts).execute();
    }
    public void deleteFPromotionRulesdValidCusts(Integer id) {
//        new FPromotionRulesdValidCustsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FPromotionRulesdValidCustsServiceRest.FPromotionRulesdValidCustsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FPromotionRulesdValidCustsCrudAsyncTask extends AsyncTask<Void, Void, FPromotionRulesdValidCusts> {

        String operation = "";
        FPromotionRulesdValidCusts newFPromotionRulesdValidCusts = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FPromotionRulesdValidCustsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FPromotionRulesdValidCustsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FPromotionRulesdValidCusts newFPromotionRulesdValidCusts){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesdValidCusts = newFPromotionRulesdValidCusts;
            operation = "ADD_NEW";
        }
        private FPromotionRulesdValidCustsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FPromotionRulesdValidCusts updateFPromotionRulesdValidCusts){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesdValidCusts = updateFPromotionRulesdValidCusts;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FPromotionRulesdValidCustsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FPromotionRulesdValidCusts doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FPromotionRulesdValidCusts> response = restTemplate.exchange(url, HttpMethod.POST, FPromotionRulesdValidCusts.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFPromotionRulesdValidCusts, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FPromotionRulesdValidCusts> response = restTemplate.postForEntity(url, httpEntity,  FPromotionRulesdValidCusts.class);
                ResponseEntity<FPromotionRulesdValidCusts> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFPromotionRulesdValidCusts";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFPromotionRulesdValidCusts, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFPromotionRulesdValidCustsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFPromotionRulesdValidCusts, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFPromotionRulesdValidCusts/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFPromotionRulesdValidCustsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FPromotionRulesdValidCusts();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesdValidCusts();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesdValidCusts();
            }
        }

        @Override
        protected void onPostExecute(FPromotionRulesdValidCusts result) {
            dismissProgressDialog();
//            if(result==null) result = new FPromotionRulesdValidCusts();
//            displayResponseFPromotionRulesdValidCusts(result);
        }
    }

    public class FPromotionRulesdValidCustsAllAsyncTask extends  AsyncTask<Void, Void, List<FPromotionRulesdValidCusts>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FPromotionRulesdValidCustsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FPromotionRulesdValidCustsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FPromotionRulesdValidCustsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FPromotionRulesdValidCusts> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFPromotionRulesdValidCusts";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FPromotionRulesdValidCusts> list = new ArrayList<>();
            try {
                ResponseEntity<FPromotionRulesdValidCusts[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFPromotionRulesdValidCusts";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFPromotionRulesdValidCustsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFPromotionRulesdValidCustsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdValidCusts[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesdValidCusts>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesdValidCusts>();
            }
        }

        @Override
        protected void onPostExecute(List<FPromotionRulesdValidCusts> result) {
            dismissProgressDialog();
        }
    }


}
