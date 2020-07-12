package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FParamDiskonNota;
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

public class FParamDiskonNotaServiceRest {
    protected static final String TAG = FParamDiskonNotaServiceRest.class.getSimpleName();
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

    public FParamDiskonNotaServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FParamDiskonNota getFParamDiskonNotaById(int id) {
        FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask asyncTask = (FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask) new FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask(apiAuthenticationClient, id, true);
        FParamDiskonNota fParamDiskonNota = null;
        try {
            fParamDiskonNota = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fParamDiskonNota==null) fParamDiskonNota =new FParamDiskonNota();
        return fParamDiskonNota;
    }
   
    public List<FParamDiskonNota> getAllFParamDiskonNota() {
        FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask asyncTask = (FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask) new FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask(apiAuthenticationClient);
        List<FParamDiskonNota> listFParamDiskonNota = new ArrayList<>();
        try {
            listFParamDiskonNota = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
        }
        return listFParamDiskonNota;

    }
    public List<FParamDiskonNota> getAllFParamDiskonNotaByDivision(int divisionBean) {
        FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask asyncTask = (FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask) new FParamDiskonNotaServiceRest.FParamDiskonNotaAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FParamDiskonNota> listFParamDiskonNota = new ArrayList<>();
        try {
            listFParamDiskonNota = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
        }
        return listFParamDiskonNota;

    }
   
   
    public void createFParamDiskonNota(FParamDiskonNota fParamDiskonNota) {
//        new FParamDiskonNotaCreateAsyncTask(apiAuthenticationClient, fParamDiskonNota).execute();
        new FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask(apiAuthenticationClient, fParamDiskonNota).execute();
    }
    public void updateFParamDiskonNota(Integer id, FParamDiskonNota fParamDiskonNota) {
//        new FParamDiskonNotaUpdateAsyncTask(apiAuthenticationClient, id, fParamDiskonNota).execute();
        new FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask(apiAuthenticationClient, id, fParamDiskonNota).execute();
    }
    public void deleteFParamDiskonNota(Integer id) {
//        new FParamDiskonNotaDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FParamDiskonNotaServiceRest.FParamDiskonNotaCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FParamDiskonNotaCrudAsyncTask extends AsyncTask<Void, Void, FParamDiskonNota> {

        String operation = "";
        FParamDiskonNota newFParamDiskonNota = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FParamDiskonNotaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FParamDiskonNotaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FParamDiskonNota newFParamDiskonNota){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonNota = newFParamDiskonNota;
            operation = "ADD_NEW";
        }
        private FParamDiskonNotaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FParamDiskonNota updateFParamDiskonNota){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFParamDiskonNota = updateFParamDiskonNota;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FParamDiskonNotaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FParamDiskonNota doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FParamDiskonNota> response = restTemplate.exchange(url, HttpMethod.POST, FParamDiskonNota.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFParamDiskonNota, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FParamDiskonNota> response = restTemplate.postForEntity(url, httpEntity,  FParamDiskonNota.class);
                ResponseEntity<FParamDiskonNota> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFParamDiskonNota";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFParamDiskonNota, apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFParamDiskonNotaInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFParamDiskonNota, apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFParamDiskonNota/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFParamDiskonNotaById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FParamDiskonNota();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonNota();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FParamDiskonNota();
            }
        }

        @Override
        protected void onPostExecute(FParamDiskonNota result) {
            dismissProgressDialog();
//            if(result==null) result = new FParamDiskonNota();
//            displayResponseFParamDiskonNota(result);
        }
    }

    public class FParamDiskonNotaAllAsyncTask extends  AsyncTask<Void, Void, List<FParamDiskonNota>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FParamDiskonNotaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FParamDiskonNotaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FParamDiskonNotaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FParamDiskonNota> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFParamDiskonNota";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FParamDiskonNota> list = new ArrayList<>();
            try {
                ResponseEntity<FParamDiskonNota[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFParamDiskonNota";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFParamDiskonNotaByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFParamDiskonNota";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FParamDiskonNota[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonNota>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FParamDiskonNota>();
            }
        }

        @Override
        protected void onPostExecute(List<FParamDiskonNota> result) {
            dismissProgressDialog();
        }
    }


}
