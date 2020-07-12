package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FRegion;
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

public class FRegionServiceRest {
    protected static final String TAG = FRegionServiceRest.class.getSimpleName();
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

    public FRegionServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FRegion getFRegionById(int id) {
        FRegionServiceRest.FRegionCrudAsyncTask asyncTask = (FRegionServiceRest.FRegionCrudAsyncTask) new FRegionServiceRest.FRegionCrudAsyncTask(apiAuthenticationClient, id, true);
        FRegion fRegion = null;
        try {
            fRegion = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fRegion==null) fRegion =new FRegion();
        return fRegion;
    }
    
    public List<FRegion> getAllFRegion() {
        FRegionServiceRest.FRegionAllAsyncTask asyncTask = (FRegionServiceRest.FRegionAllAsyncTask) new FRegionServiceRest.FRegionAllAsyncTask(apiAuthenticationClient);
        List<FRegion> listFRegion = new ArrayList<>();
        try {
            listFRegion = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFRegion;

    }
    public List<FRegion> getAllFRegionByDivision(int divisionBean) {
        FRegionServiceRest.FRegionAllAsyncTask asyncTask = (FRegionServiceRest.FRegionAllAsyncTask) new FRegionServiceRest.FRegionAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FRegion> listFRegion = new ArrayList<>();
        try {
            listFRegion = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFRegion;

    }
    
    
    public void createFRegion(FRegion fRegion) {
//        new FRegionCreateAsyncTask(apiAuthenticationClient, fRegion).execute();
        new FRegionServiceRest.FRegionCrudAsyncTask(apiAuthenticationClient, fRegion).execute();
    }
    public void updateFRegion(Integer id, FRegion fRegion) {
//        new FRegionUpdateAsyncTask(apiAuthenticationClient, id, fRegion).execute();
        new FRegionServiceRest.FRegionCrudAsyncTask(apiAuthenticationClient, id, fRegion).execute();
    }
    public void deleteFRegion(Integer id) {
//        new FRegionDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FRegionServiceRest.FRegionCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FRegionCrudAsyncTask extends AsyncTask<Void, Void, FRegion> {

        String operation = "";
        FRegion newFRegion = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FRegionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FRegionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FRegion newFRegion){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFRegion = newFRegion;
            operation = "ADD_NEW";
        }
        private FRegionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FRegion updateFRegion){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFRegion = updateFRegion;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FRegionCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FRegion doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FRegion> response = restTemplate.exchange(url, HttpMethod.POST, FRegion.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFRegion, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FRegion> response = restTemplate.postForEntity(url, httpEntity,  FRegion.class);
                ResponseEntity<FRegion> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFRegion";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFRegion, apiAuthenticationClient.getRequestHeaders()), FRegion.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFRegionInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFRegion, apiAuthenticationClient.getRequestHeaders()), FRegion.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFRegion/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FRegion.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFRegionById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FRegion.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FRegion();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FRegion();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FRegion();
            }
        }

        @Override
        protected void onPostExecute(FRegion result) {
            dismissProgressDialog();
//            if(result==null) result = new FRegion();
//            displayResponseFRegion(result);
        }
    }

    public class FRegionAllAsyncTask extends  AsyncTask<Void, Void, List<FRegion>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FRegionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FRegionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FRegionAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FRegion> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFRegion";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FRegion> list = new ArrayList<>();
            try {
                ResponseEntity<FRegion[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFRegion";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FRegion[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFRegionByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FRegion[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFRegion";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FRegion[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FRegion>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FRegion>();
            }
        }

        @Override
        protected void onPostExecute(List<FRegion> result) {
            dismissProgressDialog();
        }
    }


}
