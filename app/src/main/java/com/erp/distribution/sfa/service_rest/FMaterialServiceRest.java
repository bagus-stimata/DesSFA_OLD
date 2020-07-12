package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterial;
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

public class FMaterialServiceRest {
    protected static final String TAG = FMaterialServiceRest.class.getSimpleName();
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

    public FMaterialServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterial getFMaterialById(int id) {
        FMaterialServiceRest.FMaterialCrudAsyncTask asyncTask = (FMaterialServiceRest.FMaterialCrudAsyncTask) new FMaterialServiceRest.FMaterialCrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterial fMaterial = null;
        try {
            fMaterial = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterial==null) fMaterial =new FMaterial();
        return fMaterial;
    }

    public List<FMaterial> getAllFMaterial() {
        FMaterialServiceRest.FMaterialAllAsyncTask asyncTask = (FMaterialServiceRest.FMaterialAllAsyncTask) new FMaterialServiceRest.FMaterialAllAsyncTask(apiAuthenticationClient);
        List<FMaterial> listFMaterial = new ArrayList<>();
        try {
            listFMaterial = asyncTask.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return listFMaterial;
    }
    public List<FMaterial> getAllFMaterialByDivision(int divisionBean) {
        FMaterialServiceRest.FMaterialAllAsyncTask asyncTask = (FMaterialServiceRest.FMaterialAllAsyncTask) new FMaterialServiceRest.FMaterialAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FMaterial> listFMaterial = new ArrayList<>();
        try {
            listFMaterial = asyncTask.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return listFMaterial;

    }


    public void createFMaterial(FMaterial fMaterial) {
//        new FMaterialCreateAsyncTask(apiAuthenticationClient, fMaterial).execute();
        new FMaterialServiceRest.FMaterialCrudAsyncTask(apiAuthenticationClient, fMaterial).execute();
    }
    public void updateFMaterial(Integer id, FMaterial fMaterial) {
//        new FMaterialUpdateAsyncTask(apiAuthenticationClient, id, fMaterial).execute();
        new FMaterialServiceRest.FMaterialCrudAsyncTask(apiAuthenticationClient, id, fMaterial).execute();
    }
    public void deleteFMaterial(Integer id) {
//        new FMaterialDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialServiceRest.FMaterialCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialCrudAsyncTask extends AsyncTask<Void, Void, FMaterial> {

        String operation = "";
        FMaterial newFMaterial = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterial newFMaterial){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterial = newFMaterial;
            operation = "ADD_NEW";
        }
        private FMaterialCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterial updateFMaterial){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterial = updateFMaterial;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FMaterial doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterial> response = restTemplate.exchange(url, HttpMethod.POST, FMaterial.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterial, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterial> response = restTemplate.postForEntity(url, httpEntity,  FMaterial.class);
                ResponseEntity<FMaterial> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterial";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterial, apiAuthenticationClient.getRequestHeaders()), FMaterial.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterial, apiAuthenticationClient.getRequestHeaders()), FMaterial.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterial/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterial.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterial.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterial();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterial();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterial();
            }
        }

        @Override
        protected void onPostExecute(FMaterial result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterial();
//            displayResponseFMaterial(result);
        }
    }

    public class FMaterialAllAsyncTask extends  AsyncTask<Void, Void, List<FMaterial>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterial> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterial";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterial> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterial[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterial";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterial[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterial[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterial";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterial[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterial>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterial>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterial> result) {
            dismissProgressDialog();
        }
    }


}
