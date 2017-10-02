package me.treq.bt.android.biz.buses;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import org.apache.commons.lang3.Validate;

import java.util.List;

import me.treq.bt.android.service.BusWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusRepo {

    private final BusWebService busWebService;

    public BusRepo(BusWebService busWebService) {
        Validate.notNull(busWebService);

        this.busWebService = busWebService;
    }


    public void getBuses(MutableLiveData<List<Bus>> buses, String routeId) {
        this.busWebService.getBuses(routeId).enqueue(new Callback<List<Bus>>() {
            @Override
            public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                Log.i("BusRepo", "onResponse: got buses from bus service");
                buses.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Bus>> call, Throwable t) {
                Log.e("BusRepo", "onFailure: Failed to get buses from bus service", t);
            }
        });
    }
}