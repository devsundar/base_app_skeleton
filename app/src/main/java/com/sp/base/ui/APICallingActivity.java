package com.sp.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.sp.base.R;
import com.sp.base.countrypicker.Country;
import com.sp.base.countrypicker.CountryPickerCallbacks;
import com.sp.base.countrypicker.CountryPickerDialog;
import com.sp.base.databinding.ActivityMainBinding;
import com.sp.base.network.ApiResponse;
import com.sp.base.ui.adapter.AllNotificationListAdapter;
import com.sp.base.ui.model.DummyResponse;
import com.sp.base.ui.model.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class APICallingActivity extends AppCompatActivity {
    private static final String TAG = APICallingActivity.class.getSimpleName();
    ActivityMainBinding mBinding;
    MainViewModel viewModel;
    AllNotificationListAdapter allNotificationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        observeData();
    }

    private void observeData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rv.setLayoutManager(manager);
        mBinding.rv.setHasFixedSize(true);
        allNotificationListAdapter = new AllNotificationListAdapter(APICallingActivity.this);
        mBinding.rv.setAdapter(allNotificationListAdapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getResponse().observe(this, new androidx.lifecycle.Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                consumeResponse(apiResponse, "list");
            }
        });
//        viewModel.itemPagedList.observe(this, articles -> {
//            //submitting the articles to adapter
//            allNotificationListAdapter.submitList(articles);
//        });
        // finally setting adapter to the recyclerView
        viewModel.getDataList(1, 20);
    }


    private void consumeResponse(ApiResponse apiResponse, String tag) {
        switch (apiResponse.status) {
            case LOADING:
//                mBinding.progressIndicator.show();
//                mBinding.result.setText("");
                Log.e(TAG, "consumeResponse: loading");
                break;

            case SUCCESS:
//                mBinding.progressIndicator.hide();
                if (!apiResponse.data.isJsonNull()) {
                    Log.e("response:", apiResponse.data.toString());
                    if (tag.equalsIgnoreCase("list")) {
                        DummyResponse dummyResponse = new Gson().fromJson(apiResponse.data.toString(), DummyResponse.class);
                        Log.e("response", "consumeResponse: " + dummyResponse.toString());
                        allNotificationListAdapter.submitList(dummyResponse.getResults());

//                        allNotificationListAdapter = new AllNotificationListAdapter(this, dummyResponse.getResults());
//                        mBinding.rv.setAdapter(AllNotificationListAdapter);
                    }
                }

                break;
            case ERROR:
                Log.e(TAG, "consumeResponse: " + apiResponse.error);
//                mBinding.result.setText("");
//                mBinding.progressIndicator.hide();
                break;
            default:
//                mBinding.progressIndicator.hide();
//                mBinding.result.setText("");

                break;
        }
    }
}