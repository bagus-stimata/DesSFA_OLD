package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtApPaymenth;
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

public class FtApPaymenthServiceRest {
    protected static final String TAG = FtApPaymenthServiceRest.class.getSimpleName();
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

    public FtApPaymenthServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtApPaymenth getFtApPaymenthById(Long id) {
        FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask asyncTask = (FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask) new FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask(apiAuthenticationClient, id, true);
        FtApPaymenth ftApPaymenth = null;
        try {
            ftApPaymenth = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (ftApPaymenth==null) ftApPaymenth =new FtApPaymenth();
        return ftApPaymenth;
    }

    public List<FtApPaymenth> getAllFtApPaymenth() {
        FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask asyncTask = (FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask) new FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask(apiAuthenticationClient);
        List<FtApPaymenth> listFtApPaymenth = new ArrayList<>();
        try {
            listFtApPaymenth = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtApPaymenth;

    }
    public List<FtApPaymenth> getAllFtApPaymenthByDivision(Long divisionBean) {
        FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask asyncTask = (FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask) new FtApPaymenthServiceRest.FtApPaymenthAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtApPaymenth> listFtApPaymenth = new ArrayList<>();
        try {
            listFtApPaymenth = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFtApPaymenth;

    }

    public void createFtApPaymenth(FtApPaymenth ftApPaymenth) {
//        new FtApPaymenthCreateAsyncTask(apiAuthenticationClient, ftApPaymenth).execute();
        new FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask(apiAuthenticationClient, ftApPaymenth).execute();
    }
    public void updateFtApPaymenth(Long id, FtApPaymenth ftApPaymenth) {
//        new FtApPaymenthUpdateAsyncTask(apiAuthenticationClient, id, ftApPaymenth).execute();
        new FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask(apiAuthenticationClient, id, ftApPaymenth).execute();
    }
    public void deleteFtApPaymenth(Long id) {
//        new FtApPaymenthDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtApPaymenthServiceRest.FtApPaymenthCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtApPaymenthCrudAsyncTask extends AsyncTask<Void, Void, FtApPaymenth> {

        String operation = "";
        FtApPaymenth newFtApPaymenth = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtApPaymenthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtApPaymenthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtApPaymenth newFtApPaymenth){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtApPaymenth = newFtApPaymenth;
            operation = "ADD_NEW";
        }
        private FtApPaymenthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtApPaymenth updateFtApPaymenth){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtApPaymenth = updateFtApPaymenth;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtApPaymenthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtApPaymenth doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtApPaymenth> response = restTemplate.exchange(url, HttpMethod.POST, FtApPaymenth.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtApPaymenth, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtApPaymenth> response = restTemplate.postForEntity(url, httpEntity,  FtApPaymenth.class);
                ResponseEntity<FtApPaymenth> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtApPaymenth";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtApPaymenth, apiAuthenticationClient.getRequestHeaders()), FtApPaymenth.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtApPaymenthInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtApPaymenth, apiAuthenticationClient.getRequestHeaders()), FtApPaymenth.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtApPaymenth/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymenth.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtApPaymenthById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymenth.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtApPaymenth();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtApPaymenth();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtApPaymenth();
            }
        }

        @Override
        protected void onPostExecute(FtApPaymenth result) {
            dismissProgressDialog();
//            if(result==null) result = new FtApPaymenth();
//            displayResponseFtApPaymenth(result);
        }
    }

    public class FtApPaymenthAllAsyncTask extends  AsyncTask<Void, Void, List<FtApPaymenth>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtApPaymenthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtApPaymenthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtApPaymenthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtApPaymenth> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtApPaymenth";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtApPaymenth> list = new ArrayList<>();
            try {
                ResponseEntity<FtApPaymenth[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtApPaymenth";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymenth[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtApPaymenthByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtApPaymenth[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtApPaymenth";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtApPaymenth[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtApPaymenth>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtApPaymenth>();
            }
        }

        @Override
        protected void onPostExecute(List<FtApPaymenth> result) {
            dismissProgressDialog();
        }
    }


}
