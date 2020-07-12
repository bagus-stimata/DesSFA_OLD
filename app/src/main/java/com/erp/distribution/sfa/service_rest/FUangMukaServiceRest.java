package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FUangMuka;
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

public class FUangMukaServiceRest {
    protected static final String TAG = FUangMukaServiceRest.class.getSimpleName();
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

    public FUangMukaServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FUangMuka getFUangMukaById(int id) {
        FUangMukaServiceRest.FUangMukaCrudAsyncTask asyncTask = (FUangMukaServiceRest.FUangMukaCrudAsyncTask) new FUangMukaServiceRest.FUangMukaCrudAsyncTask(apiAuthenticationClient, id, true);
        FUangMuka fUangMuka = null;
        try {
            fUangMuka = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (fUangMuka==null) fUangMuka =new FUangMuka();
        return fUangMuka;
    }

    public List<FUangMuka> getAllFUangMuka() {
        FUangMukaServiceRest.FUangMukaAllAsyncTask asyncTask = (FUangMukaServiceRest.FUangMukaAllAsyncTask) new FUangMukaServiceRest.FUangMukaAllAsyncTask(apiAuthenticationClient);
        List<FUangMuka> listFUangMuka = new ArrayList<>();
        try {
            listFUangMuka = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFUangMuka;

    }
    public List<FUangMuka> getAllFUangMukaByDivision(int divisionBean) {
        FUangMukaServiceRest.FUangMukaAllAsyncTask asyncTask = (FUangMukaServiceRest.FUangMukaAllAsyncTask) new FUangMukaServiceRest.FUangMukaAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FUangMuka> listFUangMuka = new ArrayList<>();
        try {
            listFUangMuka = asyncTask.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return listFUangMuka;

    }


    public void createFUangMuka(FUangMuka fUangMuka) {
//        new FUangMukaCreateAsyncTask(apiAuthenticationClient, fUangMuka).execute();
        new FUangMukaServiceRest.FUangMukaCrudAsyncTask(apiAuthenticationClient, fUangMuka).execute();
    }
    public void updateFUangMuka(Integer id, FUangMuka fUangMuka) {
//        new FUangMukaUpdateAsyncTask(apiAuthenticationClient, id, fUangMuka).execute();
        new FUangMukaServiceRest.FUangMukaCrudAsyncTask(apiAuthenticationClient, id, fUangMuka).execute();
    }
    public void deleteFUangMuka(Integer id) {
//        new FUangMukaDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FUangMukaServiceRest.FUangMukaCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FUangMukaCrudAsyncTask extends AsyncTask<Void, Void, FUangMuka> {

        String operation = "";
        FUangMuka newFUangMuka = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FUangMukaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FUangMukaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FUangMuka newFUangMuka){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFUangMuka = newFUangMuka;
            operation = "ADD_NEW";
        }
        private FUangMukaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FUangMuka updateFUangMuka){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFUangMuka = updateFUangMuka;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FUangMukaCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FUangMuka doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FUangMuka> response = restTemplate.exchange(url, HttpMethod.POST, FUangMuka.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFUangMuka, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FUangMuka> response = restTemplate.postForEntity(url, httpEntity,  FUangMuka.class);
                ResponseEntity<FUangMuka> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFUangMuka";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFUangMuka, apiAuthenticationClient.getRequestHeaders()), FUangMuka.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFUangMukaInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFUangMuka, apiAuthenticationClient.getRequestHeaders()), FUangMuka.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFUangMuka/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUangMuka.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFUangMukaById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUangMuka.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FUangMuka();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUangMuka();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FUangMuka();
            }
        }

        @Override
        protected void onPostExecute(FUangMuka result) {
            dismissProgressDialog();
//            if(result==null) result = new FUangMuka();
//            displayResponseFUangMuka(result);
        }
    }

    public class FUangMukaAllAsyncTask extends  AsyncTask<Void, Void, List<FUangMuka>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FUangMukaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FUangMukaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FUangMukaAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FUangMuka> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFUangMuka";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FUangMuka> list = new ArrayList<>();
            try {
                ResponseEntity<FUangMuka[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFUangMuka";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUangMuka[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFUangMukaByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FUangMuka[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFUangMuka";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FUangMuka[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FUangMuka>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FUangMuka>();
            }
        }

        @Override
        protected void onPostExecute(List<FUangMuka> result) {
            dismissProgressDialog();
        }
    }


}
