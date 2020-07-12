package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FCustomer;
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

public class FCustomerServiceRest {
    protected static final String TAG = FCustomerServiceRest.class.getSimpleName();
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

    public FCustomerServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FCustomer getFCustomerById(int id) {
        FCustomerServiceRest.FCustomerCrudAsyncTask asyncTask = (FCustomerServiceRest.FCustomerCrudAsyncTask) new FCustomerServiceRest.FCustomerCrudAsyncTask(apiAuthenticationClient, id, true);
        FCustomer fCustomer = null;
        try {
            fCustomer = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fCustomer==null) fCustomer =new FCustomer();
        return fCustomer;
    }

    public List<FCustomer> getAllFCustomer() {
        FCustomerServiceRest.FCustomerAllAsyncTask asyncTask = (FCustomerServiceRest.FCustomerAllAsyncTask) new FCustomerServiceRest.FCustomerAllAsyncTask(apiAuthenticationClient);
        List<FCustomer> listFCustomer = new ArrayList<>();
        try {
            listFCustomer = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFCustomer;

    }
    public List<FCustomer> getAllFCustomerByDivision(int divisionBean) {
        FCustomerServiceRest.FCustomerAllAsyncTask asyncTask = (FCustomerServiceRest.FCustomerAllAsyncTask) new FCustomerServiceRest.FCustomerAllAsyncTask(apiAuthenticationClient, divisionBean);
        List<FCustomer> listFCustomer = new ArrayList<>();
        try {
            listFCustomer = asyncTask.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return listFCustomer;

    }


    public void createFCustomer(FCustomer fCustomer) {
//        new FCustomerCreateAsyncTask(apiAuthenticationClient, fCustomer).execute();
        new FCustomerServiceRest.FCustomerCrudAsyncTask(apiAuthenticationClient, fCustomer).execute();
    }
    public void updateFCustomer(Integer id, FCustomer fCustomer) {
//        new FCustomerUpdateAsyncTask(apiAuthenticationClient, id, fCustomer).execute();
        new FCustomerServiceRest.FCustomerCrudAsyncTask(apiAuthenticationClient, id, fCustomer).execute();
    }
    public void deleteFCustomer(Integer id) {
//        new FCustomerDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FCustomerServiceRest.FCustomerCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FCustomerCrudAsyncTask extends AsyncTask<Void, Void, FCustomer> {

        String operation = "";
        FCustomer newFCustomer = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FCustomerCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FCustomerCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FCustomer newFCustomer){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomer = newFCustomer;
            operation = "ADD_NEW";
        }
        private FCustomerCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FCustomer updateFCustomer){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomer = updateFCustomer;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FCustomerCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FCustomer doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FCustomer> response = restTemplate.exchange(url, HttpMethod.POST, FCustomer.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFCustomer, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FCustomer> response = restTemplate.postForEntity(url, httpEntity,  FCustomer.class);
                ResponseEntity<FCustomer> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFCustomer";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFCustomer, apiAuthenticationClient.getRequestHeaders()), FCustomer.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFCustomerInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFCustomer, apiAuthenticationClient.getRequestHeaders()), FCustomer.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFCustomer/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomer.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFCustomerById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomer.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FCustomer();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomer();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomer();
            }
        }

        @Override
        protected void onPostExecute(FCustomer result) {
            dismissProgressDialog();
//            if(result==null) result = new FCustomer();
//            displayResponseFCustomer(result);
        }
    }

    public class FCustomerAllAsyncTask extends  AsyncTask<Void, Void, List<FCustomer>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FCustomerAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FCustomerAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FCustomerAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FCustomer> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFCustomer";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FCustomer> list = new ArrayList<>();
            try {
                ResponseEntity<FCustomer[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFCustomer";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomer[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFCustomerByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomer[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFCustomer";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FCustomer[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomer>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomer>();
            }
        }

        @Override
        protected void onPostExecute(List<FCustomer> result) {
            dismissProgressDialog();
        }
    }


}
