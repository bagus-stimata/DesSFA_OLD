package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterialSalesBrand;
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

public class FMaterialSalesBrandServiceRest {
    protected static final String TAG = FMaterialSalesBrandServiceRest.class.getSimpleName();
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

    public FMaterialSalesBrandServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterialSalesBrand getFMaterialSalesBrandById(int id) {
        FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask asyncTask = (FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask) new FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterialSalesBrand fMaterialSalesBrand = null;
        try {
            fMaterialSalesBrand = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterialSalesBrand==null) fMaterialSalesBrand =new FMaterialSalesBrand();
        return fMaterialSalesBrand;
    }

    public List<FMaterialSalesBrand> getAllFMaterialSalesBrand() {
        FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask asyncTask = (FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask) new FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask(apiAuthenticationClient);
        List<FMaterialSalesBrand> listFMaterialSalesBrand = new ArrayList<>();
        try {
            listFMaterialSalesBrand = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialSalesBrand;

    }
    public List<FMaterialSalesBrand> getAllFMaterialSalesBrandByDivision(int divisionBean) {
        FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask asyncTask = (FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask) new FMaterialSalesBrandServiceRest.FMaterialSalesBrandAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FMaterialSalesBrand> listFMaterialSalesBrand = new ArrayList<>();
        try {
            listFMaterialSalesBrand = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialSalesBrand;

    }


    public void createFMaterialSalesBrand(FMaterialSalesBrand fMaterialSalesBrand) {
//        new FMaterialSalesBrandCreateAsyncTask(apiAuthenticationClient, fMaterialSalesBrand).execute();
        new FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask(apiAuthenticationClient, fMaterialSalesBrand).execute();
    }
    public void updateFMaterialSalesBrand(Integer id, FMaterialSalesBrand fMaterialSalesBrand) {
//        new FMaterialSalesBrandUpdateAsyncTask(apiAuthenticationClient, id, fMaterialSalesBrand).execute();
        new FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask(apiAuthenticationClient, id, fMaterialSalesBrand).execute();
    }
    public void deleteFMaterialSalesBrand(Integer id) {
//        new FMaterialSalesBrandDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialSalesBrandServiceRest.FMaterialSalesBrandCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialSalesBrandCrudAsyncTask extends AsyncTask<Void, Void, FMaterialSalesBrand> {

        String operation = "";
        FMaterialSalesBrand newFMaterialSalesBrand = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialSalesBrandCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialSalesBrandCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterialSalesBrand newFMaterialSalesBrand){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialSalesBrand = newFMaterialSalesBrand;
            operation = "ADD_NEW";
        }
        private FMaterialSalesBrandCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterialSalesBrand updateFMaterialSalesBrand){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialSalesBrand = updateFMaterialSalesBrand;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialSalesBrandCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FMaterialSalesBrand doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterialSalesBrand> response = restTemplate.exchange(url, HttpMethod.POST, FMaterialSalesBrand.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterialSalesBrand, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterialSalesBrand> response = restTemplate.postForEntity(url, httpEntity,  FMaterialSalesBrand.class);
                ResponseEntity<FMaterialSalesBrand> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterialSalesBrand";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterialSalesBrand, apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialSalesBrandInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterialSalesBrand, apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterialSalesBrand/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialSalesBrandById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterialSalesBrand();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialSalesBrand();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialSalesBrand();
            }
        }

        @Override
        protected void onPostExecute(FMaterialSalesBrand result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterialSalesBrand();
//            displayResponseFMaterialSalesBrand(result);
        }
    }

    public class FMaterialSalesBrandAllAsyncTask extends  AsyncTask<Void, Void, List<FMaterialSalesBrand>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialSalesBrandAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialSalesBrandAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialSalesBrandAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterialSalesBrand> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterialSalesBrand";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterialSalesBrand> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterialSalesBrand[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterialSalesBrand";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialSalesBrandByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterialSalesBrand";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterialSalesBrand[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialSalesBrand>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialSalesBrand>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterialSalesBrand> result) {
            dismissProgressDialog();
        }
    }


}
