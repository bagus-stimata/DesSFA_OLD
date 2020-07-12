package com.erp.distribution.sfa.service_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.erp.distribution.sfa.model.FMaterialPic;
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

public class FMaterialPicServiceRest {
    protected static final String TAG = FMaterialPicServiceRest.class.getSimpleName();
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

    public FMaterialPicServiceRest(Context contex) {
        this.context = context;
        this.apiAuthenticationClient =  ApiAuthenticationClient.getInstance();;
    }

    public FMaterialPic getFMaterialPicById(int id) {
        FMaterialPicServiceRest.FMaterialPicCrudAsyncTask asyncTask = (FMaterialPicServiceRest.FMaterialPicCrudAsyncTask) new FMaterialPicServiceRest.FMaterialPicCrudAsyncTask(apiAuthenticationClient, id, true);
        FMaterialPic fMaterialPic = null;
        try {
            fMaterialPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        if (fMaterialPic==null) fMaterialPic =new FMaterialPic();
        return fMaterialPic;
    }

    public List<FMaterialPic> getAllFMaterialPic() {
        FMaterialPicServiceRest.FMaterialPicAllAsyncTask asyncTask = (FMaterialPicServiceRest.FMaterialPicAllAsyncTask) new FMaterialPicServiceRest.FMaterialPicAllAsyncTask(apiAuthenticationClient);
        List<FMaterialPic> listFMaterialPic = new ArrayList<>();
        try {
            listFMaterialPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialPic;

    }
    public List<FMaterialPic> getAllFMaterialPicByParentId(int parentId) {
        FMaterialPicServiceRest.FMaterialPicAllAsyncTask asyncTask = (FMaterialPicServiceRest.FMaterialPicAllAsyncTask) new FMaterialPicServiceRest.FMaterialPicAllAsyncTask(apiAuthenticationClient, parentId);
        List<FMaterialPic> listFMaterialPic = new ArrayList<>();
        try {
            listFMaterialPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialPic;
    }
    public List<FMaterialPic> getAllFMaterialPicByListParentId(List<Integer> listParentId) {
        FMaterialPicServiceRest.FMaterialPicAllAsyncTask asyncTask = (FMaterialPicServiceRest.FMaterialPicAllAsyncTask) new FMaterialPicServiceRest.FMaterialPicAllAsyncTask(apiAuthenticationClient, listParentId);
        List<FMaterialPic> listFMaterialPic = new ArrayList<>();
        try {
            listFMaterialPic = asyncTask.execute().get();
        } catch (InterruptedException |ExecutionException e) {
            e.printStackTrace();
        }
        return listFMaterialPic;
    }


    public void createFMaterialPic(FMaterialPic fMaterialPic) {
//        new FMaterialPicCreateAsyncTask(apiAuthenticationClient, fMaterialPic).execute();
        new FMaterialPicServiceRest.FMaterialPicCrudAsyncTask(apiAuthenticationClient, fMaterialPic).execute();
    }
    public void updateFMaterialPic(Integer id, FMaterialPic fMaterialPic) {
//        new FMaterialPicUpdateAsyncTask(apiAuthenticationClient, id, fMaterialPic).execute();
        new FMaterialPicServiceRest.FMaterialPicCrudAsyncTask(apiAuthenticationClient, id, fMaterialPic).execute();
    }
    public void deleteFMaterialPic(Integer id) {
//        new FMaterialPicDeleteAsyncTask(apiAuthenticationClient, id).execute();
        new FMaterialPicServiceRest.FMaterialPicCrudAsyncTask(apiAuthenticationClient, id).execute();
    }






    public class FMaterialPicCrudAsyncTask extends AsyncTask<Void, Void, FMaterialPic> {

        String operation = "";
        FMaterialPic newFMaterialPic = null;
        Integer id = 0;
        private ApiAuthenticationClient apiAuthenticationClient;

        private FMaterialPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_find, boolean isGetById ) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            if (isGetById) {
                this.id = id_find;
                operation = "GET_BY_ID";
            }
        }
        private FMaterialPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  FMaterialPic newFMaterialPic){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialPic = newFMaterialPic;
            operation = "ADD_NEW";
        }
        private FMaterialPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_update, FMaterialPic updateFMaterialPic){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.newFMaterialPic = updateFMaterialPic;
            this.id = id_update;
            operation = "UPDATE";
        }
        private FMaterialPicCrudAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer id_delete){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = id_delete;
            operation = "DELETE";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }


        @Override
        protected FMaterialPic doInBackground(Void... voids) {
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {

//                ResponseEntity<FMaterialPic> response = restTemplate.exchange(url, HttpMethod.POST, FMaterialPic.class);
//                HttpEntity<Object> httpEntity = new HttpEntity<Object>(newFMaterialPic, apiAuthenticationClient.getRequestHeaders());
//                ResponseEntity<FMaterialPic> response = restTemplate.postForEntity(url, httpEntity,  FMaterialPic.class);
                ResponseEntity<FMaterialPic> response = null;
                try {
                    if (operation.equals("ADD_NEW")) {
                        url += "createFMaterialPic";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(newFMaterialPic, apiAuthenticationClient.getRequestHeaders()), FMaterialPic.class);
                    } else if (operation.equals("UPDATE")) {
                        url += "updateFMaterialPicInfo/" + id;
                        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Object>(newFMaterialPic, apiAuthenticationClient.getRequestHeaders()), FMaterialPic.class);
                    } else if (operation.equals("DELETE")) {
                        url += "deleteFMaterialPic/" + id;
                        response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialPic.class);
                    } else if (operation.equals("GET_BY_ID")) {
                        url += "getFMaterialPicById/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialPic.class);
                    }

                    Log.d(TAG, url + " >> " + response.toString());
                }catch (Exception ex){
                }

                return response!=null? response.getBody(): new FMaterialPic();

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialPic();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new FMaterialPic();
            }
        }

        @Override
        protected void onPostExecute(FMaterialPic result) {
            dismissProgressDialog();
//            if(result==null) result = new FMaterialPic();
//            displayResponseFMaterialPic(result);
        }
    }

    public class FMaterialPicAllAsyncTask extends  AsyncTask<Void, Void, List<FMaterialPic>>{
        private ApiAuthenticationClient apiAuthenticationClient;
        String operation = "";
        Integer id = 0;
        List<Integer> listParentId = new ArrayList<>();

        private FMaterialPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient){
            this.apiAuthenticationClient = apiAuthenticationClient;
            operation = "GETALL";
        }
        private FMaterialPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  Integer divOrParentId) {
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.id = divOrParentId;
            operation = "GETALL_BY_PARENTID";
        }
        private FMaterialPicAllAsyncTask(ApiAuthenticationClient apiAuthenticationClient,  List<Integer> listParentId){
            this.apiAuthenticationClient = apiAuthenticationClient;
            this.listParentId = listParentId;
            operation = "GETALL_BY_LISTPARENTID";
        }

        @Override
        protected void onPreExecute() {
            showLoadingProgressDialog();
        }
        @Override
        protected List<FMaterialPic> doInBackground(Void... voids) {
//            final String url = apiAuthenticationClient.getBaseUrl() + "getAllFMaterialPic";
            String url = apiAuthenticationClient.getBaseUrl();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            List<FMaterialPic> list = new ArrayList<>();
            try {
                ResponseEntity<FMaterialPic[]> response = null;
                try{
                    if (operation.equals("GETALL")) {
                        url += "getAllFMaterialPic";
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialPic[].class);
                        list = Arrays.asList(response.getBody());

                    }else if (operation.equals("GETALL_BY_PARENTID")) {
                        url += "getAllFMaterialPicByParentId/" + id;
                        response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(apiAuthenticationClient.getRequestHeaders()), FMaterialPic[].class);
                        list = Arrays.asList(response.getBody());
                    }else if (operation.equals("GETALL_BY_LISTPARENTID")) {
                        url += "getAllFMaterialPicByListParentId";
                        response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(listParentId, apiAuthenticationClient.getRequestHeaders()), FMaterialPic[].class);
                        list = Arrays.asList(response.getBody());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return list;

            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialPic>();
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new ArrayList<FMaterialPic>();
            }
        }

        @Override
        protected void onPostExecute(List<FMaterialPic> result) {
            dismissProgressDialog();
        }
    }


}
