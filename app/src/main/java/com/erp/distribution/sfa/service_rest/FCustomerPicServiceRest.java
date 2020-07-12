package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FCustomerPic;
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

public class FCustomerPicServiceRest {
    protected static final String TAG = FCustomerPicServiceRest.class.getSimpleName();
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

    public FCustomerPicServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FCustomerPic getFCustomerPicById(int id) {
        FCustomerPicServiceRest.FCustomerPicCrudAsyncTask asyncTask = (FCustomerPicServiceRest.FCustomerPicCrudAsyncTask) new FCustomerPicServiceRest.FCustomerPicCrudAsyncTask(apiAuthenticationClient, id, true);
        FCustomerPic fCustomerPic = null;
        try {
            fCustomerPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fCustomerPic==null) fCustomerPic =new FCustomerPic();
        return fCustomerPic;
    }

    public List<FCustomerPic> getAllFCustomerPic() {
        FCustomerPicServiceRest.FCustomerPicAllAsyncTask asyncTask = (FCustomerPicServiceRest.FCustomerPicAllAsyncTask) new FCustomerPicServiceRest.FCustomerPicAllAsyncTask(apiAuthenticationClient);
        List<FCustomerPic> listFCustomerPic = new ArrayList<>();
        try {
            listFCustomerPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFCustomerPic;

    }
    public List<FCustomerPic> getAllFCustomerPicByParentId(int parentId) {
        FCustomerPicServiceRest.FCustomerPicAllAsyncTask asyncTask = (FCustomerPicServiceRest.FCustomerPicAllAsyncTask) new FCustomerPicServiceRest.FCustomerPicAllAsyncTask(apiAuthenticationClient, parentId);
        List<FCustomerPic> listFCustomerPic = new ArrayList<>();
        try {
            listFCustomerPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFCustomerPic;

    }

    public void createFCustomerPic(FCustomerPic fCustomerPic) {
//        new FCustomerPicCreateAsyncTask(apiAuthenticationClient, fCustomerPic).execute();
        new FCustomerPicServiceRest.FCustomerPicCrudAsyncTask(apiAuthenticationClient, fCustomerPic).execute();
    }
    public void updateFCustomerPic(Integer id, FCustomerPic fCustomerPic) {
//        new FCustomerPicUpdateAsyncTask(apiAuthenticationClient, id, fCustomerPic).execute();
        new FCustomerPicServiceRest.FCustomerPicCrudAsyncTask(apiAuthenticationClient, id, fCustomerPic).execute();
    }
    public void deleteFCustomerPic(Integer id) {
//        new FCustomerPicDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FCustomerPicServiceRest.FCustomerPicCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FCustomerPicCrudAsyncTask extends AsyncTask<Void, Void, FCustomerPic> {

        String operation = "";
        FCustomerPic newFCustomerPic = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FCustomerPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FCustomerPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FCustomerPic newFCustomerPic){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomerPic = newFCustomerPic;
            operation = "ADD_NEW";
        }
        private FCustomerPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FCustomerPic updateFCustomerPic){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFCustomerPic = updateFCustomerPic;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FCustomerPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }

        @Override
        protected FCustomerPic doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FCustomerPic> response = restTemplate.exchange(url, HttpMethod.POST, FCustomerPic.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFCustomerPic, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FCustomerPic> response = restTemplate.postForEntity(url, httpEntity,  FCustomerPic.class);
                ResponseEntity<FCustomerPic> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFCustomerPic";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFCustomerPic, apiAuthenticationClient.getRequestHeaders()), FCustomerPic.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFCustomerPicInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFCustomerPic, apiAuthenticationClient.getRequestHeaders()), FCustomerPic.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFCustomerPic/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerPic.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFCustomerPicById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerPic.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FCustomerPic();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomerPic();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FCustomerPic();
            }
        }

        @Override
        protected void onPostExecute(FCustomerPic result) {
            dismissProgressDialog();
//            if(result==null) result = new FCustomerPic();
//            displayResponseFCustomerPic(result);
        }
    }

    public class FCustomerPicAllAsyncTask extends  AsyncTask<Void, Void, List<FCustomerPic>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FCustomerPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FCustomerPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FCustomerPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FCustomerPic> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFCustomerPic";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FCustomerPic> list = new ArrayList<>();
            try {
                ResponseEntity<FCustomerPic[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFCustomerPic";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerPic[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFCustomerPicByDivision/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FCustomerPic[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFCustomerPic";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FCustomerPic[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomerPic>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FCustomerPic>();
            }
        }

        @Override
        protected void onPostExecute(List<FCustomerPic> result) {
            dismissProgressDialog();
        }
    }


}
