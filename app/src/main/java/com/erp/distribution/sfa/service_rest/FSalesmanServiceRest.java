package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FSalesman;
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

public class FSalesmanServiceRest {
    protected static final String TAG = FSalesmanServiceRest.class.getSimpleName();
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

    public FSalesmanServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FSalesman getFSalesmanById(int id) {
        FSalesmanServiceRest.FSalesmanCrudAsyncTask asyncTask = (FSalesmanServiceRest.FSalesmanCrudAsyncTask) new FSalesmanServiceRest.FSalesmanCrudAsyncTask(apiAuthenticationClient, id, true);
        FSalesman fSalesman = null;
        try {
            fSalesman = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fSalesman==null) fSalesman =new FSalesman();
        return fSalesman;
    }
    
    public List<FSalesman> getAllFSalesman() {
        FSalesmanServiceRest.FSalesmanAllAsyncTask asyncTask = (FSalesmanServiceRest.FSalesmanAllAsyncTask) new FSalesmanServiceRest.FSalesmanAllAsyncTask(apiAuthenticationClient);
        List<FSalesman> listFSalesman = new ArrayList<>();
        try {
            listFSalesman = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFSalesman;

    }
    public List<FSalesman> getAllFSalesmanByDivision(int divisionBean) {
        FSalesmanServiceRest.FSalesmanAllAsyncTask asyncTask = (FSalesmanServiceRest.FSalesmanAllAsyncTask) new FSalesmanServiceRest.FSalesmanAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FSalesman> listFSalesman = new ArrayList<>();
        try {
            listFSalesman = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFSalesman;

    }
    
    
    public void createFSalesman(FSalesman fSalesman) {
//        new FSalesmanCreateAsyncTask(apiAuthenticationClient, fSalesman).execute();
        new FSalesmanServiceRest.FSalesmanCrudAsyncTask(apiAuthenticationClient, fSalesman).execute();
    }
    public void updateFSalesman(Integer id, FSalesman fSalesman) {
//        new FSalesmanUpdateAsyncTask(apiAuthenticationClient, id, fSalesman).execute();
        new FSalesmanServiceRest.FSalesmanCrudAsyncTask(apiAuthenticationClient, id, fSalesman).execute();
    }
    public void deleteFSalesman(Integer id) {
//        new FSalesmanDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FSalesmanServiceRest.FSalesmanCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FSalesmanCrudAsyncTask extends AsyncTask<Void, Void, FSalesman> {

        String operation = "";
        FSalesman newFSalesman = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FSalesmanCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FSalesmanCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FSalesman newFSalesman){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFSalesman = newFSalesman;
            operation = "ADD_NEW";
        }
        private FSalesmanCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FSalesman updateFSalesman){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFSalesman = updateFSalesman;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FSalesmanCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FSalesman doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FSalesman> response = restTemplate.exchange(url, HttpMethod.POST, FSalesman.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFSalesman, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FSalesman> response = restTemplate.postForEntity(url, httpEntity,  FSalesman.class);
                ResponseEntity<FSalesman> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFSalesman";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFSalesman, apiAuthenticationClient.getRequestHeaders()), FSalesman.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFSalesmanInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFSalesman, apiAuthenticationClient.getRequestHeaders()), FSalesman.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFSalesman/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSalesman.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFSalesmanById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSalesman.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FSalesman();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FSalesman();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FSalesman();
            }
        }

        @Override
        protected void onPostExecute(FSalesman result) {
            dismissProgressDialog();
//            if(result==null) result = new FSalesman();
//            displayResponseFSalesman(result);
        }
    }

    public class FSalesmanAllAsyncTask extends  AsyncTask<Void, Void, List<FSalesman>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FSalesmanAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FSalesmanAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FSalesmanAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FSalesman> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFSalesman";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FSalesman> list = new ArrayList<>();
            try {
                ResponseEntity<FSalesman[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFSalesman";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSalesman[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFSalesmanByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FSalesman[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFSalesman";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FSalesman[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FSalesman>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FSalesman>();
            }
        }

        @Override
        protected void onPostExecute(List<FSalesman> result) {
            dismissProgressDialog();
        }
    }


}
