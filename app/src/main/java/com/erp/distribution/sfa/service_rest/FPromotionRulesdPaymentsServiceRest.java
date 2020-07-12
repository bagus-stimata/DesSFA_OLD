package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FPromotionRulesdPayments;
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

public class FPromotionRulesdPaymentsServiceRest {
    protected static final String TAG = FPromotionRulesdPaymentsServiceRest.class.getSimpleName();
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

    public FPromotionRulesdPaymentsServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FPromotionRulesdPayments getFPromotionRulesdPaymentsById(int id) {
        FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask asyncTask = (FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask) new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask(apiAuthenticationClient, id, true);
        FPromotionRulesdPayments fPromotionRulesdPayments = null;
        try {
            fPromotionRulesdPayments = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fPromotionRulesdPayments==null) fPromotionRulesdPayments =new FPromotionRulesdPayments();
        return fPromotionRulesdPayments;
    }

    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPayments() {
        FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask asyncTask = (FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask) new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask(apiAuthenticationClient);
        List<FPromotionRulesdPayments> listFPromotionRulesdPayments = new ArrayList<>();
        try {
            listFPromotionRulesdPayments = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdPayments;

    }
    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPaymentsByParentId(int parentId) {
        FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask asyncTask = (FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask) new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask(apiAuthenticationClient, parentId);
        List<FPromotionRulesdPayments> listFPromotionRulesdPayments = new ArrayList<>();
        try {
            listFPromotionRulesdPayments = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdPayments;
    }
    public List<FPromotionRulesdPayments> getAllFPromotionRulesdPaymentsByListParentId(List<Integer> listParentId) {
        FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask asyncTask = (FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask) new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FPromotionRulesdPayments> listFPromotionRulesdPayments = new ArrayList<>();
        try {
            listFPromotionRulesdPayments = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFPromotionRulesdPayments;
    }


    public void createFPromotionRulesdPayments(FPromotionRulesdPayments fPromotionRulesdPayments) {
//        new FPromotionRulesdPaymentsCreateAsyncTask(apiAuthenticationClient, fPromotionRulesdPayments).execute();
        new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask(apiAuthenticationClient, fPromotionRulesdPayments).execute();
    }
    public void updateFPromotionRulesdPayments(Integer id, FPromotionRulesdPayments fPromotionRulesdPayments) {
//        new FPromotionRulesdPaymentsUpdateAsyncTask(apiAuthenticationClient, id, fPromotionRulesdPayments).execute();
        new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask(apiAuthenticationClient, id, fPromotionRulesdPayments).execute();
    }
    public void deleteFPromotionRulesdPayments(Integer id) {
//        new FPromotionRulesdPaymentsDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FPromotionRulesdPaymentsServiceRest.FPromotionRulesdPaymentsCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FPromotionRulesdPaymentsCrudAsyncTask extends AsyncTask<Void, Void, FPromotionRulesdPayments> {

        String operation = "";
        FPromotionRulesdPayments newFPromotionRulesdPayments = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FPromotionRulesdPaymentsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FPromotionRulesdPaymentsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FPromotionRulesdPayments newFPromotionRulesdPayments){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesdPayments = newFPromotionRulesdPayments;
            operation = "ADD_NEW";
        }
        private FPromotionRulesdPaymentsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FPromotionRulesdPayments updateFPromotionRulesdPayments){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFPromotionRulesdPayments = updateFPromotionRulesdPayments;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FPromotionRulesdPaymentsCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FPromotionRulesdPayments doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FPromotionRulesdPayments> response = restTemplate.exchange(url, HttpMethod.POST, FPromotionRulesdPayments.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFPromotionRulesdPayments, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FPromotionRulesdPayments> response = restTemplate.postForEntity(url, httpEntity,  FPromotionRulesdPayments.class);
                ResponseEntity<FPromotionRulesdPayments> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFPromotionRulesdPayments";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFPromotionRulesdPayments, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFPromotionRulesdPaymentsInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFPromotionRulesdPayments, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFPromotionRulesdPayments/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFPromotionRulesdPaymentsById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FPromotionRulesdPayments();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesdPayments();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FPromotionRulesdPayments();
            }
        }

        @Override
        protected void onPostExecute(FPromotionRulesdPayments result) {
            dismissProgressDialog();
//            if(result==null) result = new FPromotionRulesdPayments();
//            displayResponseFPromotionRulesdPayments(result);
        }
    }

    public class FPromotionRulesdPaymentsAllAsyncTask extends  AsyncTask<Void, Void, List<FPromotionRulesdPayments>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FPromotionRulesdPaymentsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FPromotionRulesdPaymentsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FPromotionRulesdPaymentsAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FPromotionRulesdPayments> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFPromotionRulesdPayments";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FPromotionRulesdPayments> list = new ArrayList<>();
            try {
                ResponseEntity<FPromotionRulesdPayments[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFPromotionRulesdPayments";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFPromotionRulesdPaymentsByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFPromotionRulesdPaymentsByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FPromotionRulesdPayments[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesdPayments>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FPromotionRulesdPayments>();
            }
        }

        @Override
        protected void onPostExecute(List<FPromotionRulesdPayments> result) {
            dismissProgressDialog();
        }
    }


}
