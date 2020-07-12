package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FtSalesh;
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

public class FtSaleshServiceRest {
    protected static final String TAG = FtSaleshServiceRest.class.getSimpleName();
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

    public FtSaleshServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FtSalesh getFtSaleshById(Long id) {
        FtSaleshServiceRest.FtSaleshCrudAsyncTask asyncTask = (FtSaleshServiceRest.FtSaleshCrudAsyncTask) new FtSaleshServiceRest.FtSaleshCrudAsyncTask(apiAuthenticationClient, id, true);
        FtSalesh ftSalesh = null;
        try {
            ftSalesh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (ftSalesh==null) ftSalesh =new FtSalesh();
        return ftSalesh;
    }

    public List<FtSalesh> getAllFtSalesh() {
        FtSaleshServiceRest.FtSaleshAllAsyncTask asyncTask = (FtSaleshServiceRest.FtSaleshAllAsyncTask) new FtSaleshServiceRest.FtSaleshAllAsyncTask(apiAuthenticationClient);
        List<FtSalesh> listFtSalesh = new ArrayList<>();
        try {
            listFtSalesh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesh;

    }
    public List<FtSalesh> getAllFtSaleshByDivision(Integer divisionBean) {
        FtSaleshServiceRest.FtSaleshAllAsyncTask asyncTask = (FtSaleshServiceRest.FtSaleshAllAsyncTask) new FtSaleshServiceRest.FtSaleshAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FtSalesh> listFtSalesh = new ArrayList<>();
        try {
            listFtSalesh = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFtSalesh;

    }


    public void createFtSalesh(FtSalesh ftSalesh) {
//        new FtSaleshCreateAsyncTask(apiAuthenticationClient, ftSalesh).execute();
        new FtSaleshServiceRest.FtSaleshCrudAsyncTask(apiAuthenticationClient, ftSalesh).execute();
    }
    public void updateFtSalesh(Long id, FtSalesh ftSalesh) {
//        new FtSaleshUpdateAsyncTask(apiAuthenticationClient, id, ftSalesh).execute();
        new FtSaleshServiceRest.FtSaleshCrudAsyncTask(apiAuthenticationClient, id, ftSalesh).execute();
    }
    public void deleteFtSalesh(Long id) {
//        new FtSaleshDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FtSaleshServiceRest.FtSaleshCrudAsyncTask(apiAuthenticationClient, id).execute();
    }





    public class FtSaleshCrudAsyncTask extends AsyncTask<Void, Void, FtSalesh> {

        String operation = "";
        FtSalesh newFtSalesh = null;
        long id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FtSaleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FtSaleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FtSalesh newFtSalesh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesh = newFtSalesh;
            operation = "ADD_NEW";
        }
        private FtSaleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_update, FtSalesh updateFtSalesh){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFtSalesh = updateFtSalesh;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FtSaleshCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Long id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FtSalesh doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FtSalesh> response = restTemplate.exchange(url, HttpMethod.POST, FtSalesh.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFtSalesh, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FtSalesh> response = restTemplate.postForEntity(url, httpEntity,  FtSalesh.class);
                ResponseEntity<FtSalesh> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFtSalesh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFtSalesh, apiAuthenticationClient.getRequestHeaders()), FtSalesh.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFtSaleshInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFtSalesh, apiAuthenticationClient.getRequestHeaders()), FtSalesh.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFtSalesh/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesh.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFtSaleshById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesh.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FtSalesh();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesh();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FtSalesh();
            }
        }

        @Override
        protected void onPostExecute(FtSalesh result) {
            dismissProgressDialog();
//            if(result==null) result = new FtSalesh();
//            displayResponseFtSalesh(result);
        }
    }

    public class FtSaleshAllAsyncTask extends  AsyncTask<Void, Void, List<FtSalesh>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        int id = 0;
        List<Long> listParentId = new ArrayList<>();

        private FtSaleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FtSaleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FtSaleshAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Long> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FtSalesh> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFtSalesh";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FtSalesh> list = new ArrayList<>();
            try {
                ResponseEntity<FtSalesh[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFtSalesh";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesh[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFtSaleshByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FtSalesh[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFtSalesh";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FtSalesh[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesh>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FtSalesh>();
            }
        }

        @Override
        protected void onPostExecute(List<FtSalesh> result) {
            dismissProgressDialog();
        }
    }


}
