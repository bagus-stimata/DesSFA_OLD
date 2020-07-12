package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtPriceAlth;
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

public class FtPriceAlthServiceRest {
    protected static final String TAG = FtPriceAlthServiceRest.class.getSimpleName();
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

    public FtPriceAlthServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtPriceAlth getFtPriceAlthById(Long id) {
        FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask asyncTask = (FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask) new FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask(apiAuthenticationClient, id, true);
        FtPriceAlth ftPriceAlth = null;
        try {
            ftPriceAlth = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftPriceAlth==null) ftPriceAlth =new FtPriceAlth();
        return ftPriceAlth;
    }

    public List<FtPriceAlth> getAllFtPriceAlth() {
        FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask asyncTask = (FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask) new FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask(apiAuthenticationClient);
        List<FtPriceAlth> listFtPriceAlth = new ArrayList<>();
        try {
            listFtPriceAlth = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceAlth;

    }
    public List<FtPriceAlth> getAllFtPriceAlthByDivision(Long divisionBean) {
        FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask asyncTask = (FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask) new FtPriceAlthServiceRest.FtPriceAlthAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtPriceAlth> listFtPriceAlth = new ArrayList<>();
        try {
            listFtPriceAlth = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtPriceAlth;

    }


    public void createFtPriceAlth(FtPriceAlth ftPriceAlth) {
//        new FtPriceAlthCreateAsyncTask(apiAuthenticationClient, ftPriceAlth).execute();
        new FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask(apiAuthenticationClient, ftPriceAlth).execute();
    }
    public void updateFtPriceAlth(Long id, FtPriceAlth ftPriceAlth) {
//        new FtPriceAlthUpdateAsyncTask(apiAuthenticationClient, id, ftPriceAlth).execute();
        new FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask(apiAuthenticationClient, id, ftPriceAlth).execute();
    }
    public void deleteFtPriceAlth(Long id) {
//        new FtPriceAlthDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtPriceAlthServiceRest.FtPriceAlthCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FtPriceAlthCrudAsyncTask extends AsyncTask<Void, Void, FtPriceAlth> {

        String operation = "";
        FtPriceAlth newFtPriceAlth = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtPriceAlthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtPriceAlthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtPriceAlth newFtPriceAlth){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceAlth = newFtPriceAlth;
            operation = "ADD_NEW";
        }
        private FtPriceAlthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtPriceAlth updateFtPriceAlth){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtPriceAlth = updateFtPriceAlth;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtPriceAlthCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FtPriceAlth doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtPriceAlth> response = restTemplate.exchange(url, HttpMethod.POST, FtPriceAlth.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtPriceAlth, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtPriceAlth> response = restTemplate.postForEntity(url, httpEntity,  FtPriceAlth.class);
                ResponseEntity<FtPriceAlth> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtPriceAlth";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtPriceAlth, apiAuthenticationClient.getRequestHeaders()), FtPriceAlth.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtPriceAlthInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtPriceAlth, apiAuthenticationClient.getRequestHeaders()), FtPriceAlth.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtPriceAlth/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAlth.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtPriceAlthById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAlth.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtPriceAlth();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceAlth();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtPriceAlth();
            }
        }

        @Override
        protected void onPostExecute(FtPriceAlth result) {
            dismissProgressDialog();
//            if(result==null) result = new FtPriceAlth();
//            displayResponseFtPriceAlth(result);
        }
    }

    public class FtPriceAlthAllAsyncTask extends  AsyncTask<Void, Void, List<FtPriceAlth>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        long id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtPriceAlthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtPriceAlthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtPriceAlthAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtPriceAlth> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtPriceAlth";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtPriceAlth> list = new ArrayList<>();
            try {
                ResponseEntity<FtPriceAlth[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtPriceAlth";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAlth[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtPriceAlthByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtPriceAlth[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtPriceAlth";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtPriceAlth[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceAlth>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtPriceAlth>();
            }
        }

        @Override
        protected void onPostExecute(List<FtPriceAlth> result) {
            dismissProgressDialog();
        }
    }


}
