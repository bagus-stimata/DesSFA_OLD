package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FDivision;
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

public class FDivisionServiceRest {
    protected static final String TAG = FDivisionServiceRest.class.getSimpleName();
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

    public FDivisionServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FDivision getFDivisionById(int id) {
        FDivisionServiceRest.FDivisionCrudAsyncTask asyncTask = (FDivisionServiceRest.FDivisionCrudAsyncTask) new FDivisionServiceRest.FDivisionCrudAsyncTask(apiAuthenticationClient, id, true);
        FDivision fDivision = null;
        try {
            fDivision = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fDivision==null) fDivision =new FDivision();
        return fDivision;
    }

    public List<FDivision> getAllFDivision() {
        FDivisionServiceRest.FDivisionAllAsyncTask asyncTask = (FDivisionServiceRest.FDivisionAllAsyncTask) new FDivisionServiceRest.FDivisionAllAsyncTask(apiAuthenticationClient);
        List<FDivision> listFDivision = new ArrayList<>();
        try {
            listFDivision = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFDivision;

    }
    public List<FDivision> getAllFDivisionByParentId(int parentId) {
        FDivisionServiceRest.FDivisionAllAsyncTask asyncTask = (FDivisionServiceRest.FDivisionAllAsyncTask) new FDivisionServiceRest.FDivisionAllAsyncTask(apiAuthenticationClient, parentId);
        List<FDivision> listFDivision = new ArrayList<>();
        try {
            listFDivision = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFDivision;
    }
    public List<FDivision> getAllFDivisionByListParentId(List<Integer> listParentId) {
        FDivisionServiceRest.FDivisionAllAsyncTask asyncTask = (FDivisionServiceRest.FDivisionAllAsyncTask) new FDivisionServiceRest.FDivisionAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FDivision> listFDivision = new ArrayList<>();
        try {
            listFDivision = asyncTask.execute().get(7, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return listFDivision;
    }

    public void createFDivision(FDivision fDivision) {
//        new FDivisionCreateAsyncTask(apiAuthenticationClient, fDivision).execute();
        new FDivisionServiceRest.FDivisionCrudAsyncTask(apiAuthenticationClient, fDivision).execute();
    }
    public void updateFDivision(Integer id, FDivision fDivision) {
//        new FDivisionUpdateAsyncTask(apiAuthenticationClient, id, fDivision).execute();
        new FDivisionServiceRest.FDivisionCrudAsyncTask(apiAuthenticationClient, id, fDivision).execute();
    }
    public void deleteFDivision(Integer id) {
//        new FDivisionDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FDivisionServiceRest.FDivisionCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FDivisionCrudAsyncTask extends AsyncTask<Void, Void, FDivision> {

        String operation = "";
        FDivision newFDivision = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FDivisionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FDivisionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FDivision newFDivision){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFDivision = newFDivision;
            operation = "ADD_NEW";
        }
        private FDivisionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FDivision updateFDivision){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFDivision = updateFDivision;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FDivisionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FDivision doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FDivision> response = restTemplate.exchange(url, HttpMethod.POST, FDivision.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFDivision, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FDivision> response = restTemplate.postForEntity(url, httpEntity,  FDivision.class);
                ResponseEntity<FDivision> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFDivision";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFDivision, apiAuthenticationClient.getRequestHeaders()), FDivision.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFDivisionInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFDivision, apiAuthenticationClient.getRequestHeaders()), FDivision.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDivision.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFDivisionById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDivision.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FDivision();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FDivision();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FDivision();
            }
        }

        @Override
        protected void onPostExecute(FDivision result) {
            dismissProgressDialog();
//            if(result==null) result = new FDivision();
//            displayResponseFDivision(result);
        }
    }

    public class FDivisionAllAsyncTask extends  AsyncTask<Void, Void, List<FDivision>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FDivisionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FDivisionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FDivisionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FDivision> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFDivision";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FDivision> list = new ArrayList<>();
            try {
                ResponseEntity<FDivision[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFDivision";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDivision[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFDivisionByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FDivision[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFDivisionByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FDivision[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FDivision>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FDivision>();
            }
        }

        @Override
        protected void onPostExecute(List<FDivision> result) {
            dismissProgressDialog();
        }
    }


}
