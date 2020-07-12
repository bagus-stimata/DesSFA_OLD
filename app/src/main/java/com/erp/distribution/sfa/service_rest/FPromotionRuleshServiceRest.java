package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FPromotionRulesh;
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

public class FPromotionRuleshServiceRest {
    protected static final String TAG = FPromotionRuleshServiceRest.class.getSimpleName();
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

    public FPromotionRuleshServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FPromotionRulesh getFPromotionRuleshById(int id) {
        FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask asyncTask = (FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask) new FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask(apiAuthenticationClient, id, true);
        FPromotionRulesh fPromotionRulesh = null;
        try {
            fPromotionRulesh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fPromotionRulesh==null) fPromotionRulesh =new FPromotionRulesh();
        return fPromotionRulesh;
    }
    
    public List<FPromotionRulesh> getAllFPromotionRulesh() {
        FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask asyncTask = (FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask) new FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask(apiAuthenticationClient);
        List<FPromotionRulesh> listFPromotionRulesh = new ArrayList<>();
        try {
            listFPromotionRulesh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesh;

    }
    public List<FPromotionRulesh> getAllFPromotionRuleshByDivision(int divisionBean) {
        FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask asyncTask = (FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask) new FPromotionRuleshServiceRest.FPromotionRuleshAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FPromotionRulesh> listFPromotionRulesh = new ArrayList<>();
        try {
            listFPromotionRulesh = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesh;

    }
    
    
    public void createFPromotionRulesh(FPromotionRulesh fPromotionRulesh) {
//        new FPromotionRuleshCreateAsyncTask(apiAuthenticationClient, fPromotionRulesh).execute();
        new FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask(apiAuthenticationClient, fPromotionRulesh).execute();
    }
    public void updateFPromotionRulesh(Integer id, FPromotionRulesh fPromotionRulesh) {
//        new FPromotionRuleshUpdateAsyncTask(apiAuthenticationClient, id, fPromotionRulesh).execute();
        new FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask(apiAuthenticationClient, id, fPromotionRulesh).execute();
    }
    public void deleteFPromotionRulesh(Integer id) {
//        new FPromotionRuleshDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FPromotionRuleshServiceRest.FPromotionRuleshCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FPromotionRuleshCrudAsyncTask extends AsyncTask<Void, Void, FPromotionRulesh> {

        String operation = "";
        FPromotionRulesh newFPromotionRulesh = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FPromotionRuleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FPromotionRuleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FPromotionRulesh newFPromotionRulesh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesh = newFPromotionRulesh;
            operation = "ADD_NEW";
        }
        private FPromotionRuleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FPromotionRulesh updateFPromotionRulesh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesh = updateFPromotionRulesh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FPromotionRuleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FPromotionRulesh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FPromotionRulesh> response = restTemplate.exchange(url, HttpMethod.POST, FPromotionRulesh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFPromotionRulesh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FPromotionRulesh> response = restTemplate.postForEntity(url, httpEntity,  FPromotionRulesh.class);
                ResponseEntity<FPromotionRulesh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFPromotionRulesh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFPromotionRulesh, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFPromotionRuleshInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFPromotionRulesh, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFPromotionRulesh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFPromotionRuleshById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FPromotionRulesh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesh();
            }
        }

        @Override
        protected void onPostExecute(FPromotionRulesh result) {
            dismissProgressDialog();
//            if(result==null) result = new FPromotionRulesh();
//            displayResponseFPromotionRulesh(result);
        }
    }

    public class FPromotionRuleshAllAsyncTask extends  AsyncTask<Void, Void, List<FPromotionRulesh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FPromotionRuleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FPromotionRuleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FPromotionRuleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FPromotionRulesh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFPromotionRulesh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FPromotionRulesh> list = new ArrayList<>();
            try {
                ResponseEntity<FPromotionRulesh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFPromotionRulesh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFPromotionRuleshByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFPromotionRulesh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesh>();
            }
        }

        @Override
        protected void onPostExecute(List<FPromotionRulesh> result) {
            dismissProgressDialog();
        }
    }


}
