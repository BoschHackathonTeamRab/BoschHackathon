package com.thermodemo.bosch.thermodemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.thermodemo.bosch.thermodemo.Constants;
import com.thermodemo.bosch.thermodemo.R;
import com.thermodemo.bosch.thermodemo.async.SchedulerProvider;
import com.thermodemo.bosch.thermodemo.http.HttpRequester;
import com.thermodemo.bosch.thermodemo.models.DeviceID;
import com.thermodemo.bosch.thermodemo.models.TemperatureDto;
import com.thermodemo.bosch.thermodemo.models.Value;
import com.thermodemo.bosch.thermodemo.parsers.JsonParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ThermoActivity extends AppCompatActivity {
    private SchedulerProvider mSchedulerProvider;
    private HttpRequester mHttpRequester;
    private JsonParser<TemperatureDto> mJsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermo);

        ButterKnife.bind(this);
        mSchedulerProvider = new SchedulerProvider();
        mHttpRequester = new HttpRequester();
        mJsonParser = new JsonParser<>(TemperatureDto.class, TemperatureDto[].class);


    }

    @OnClick({R.id.btn_on, R.id.btn_off, R.id.btn_compare})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_on:
                turnLedOn();
                break;
            case R.id.btn_off:
                turnLedOff();
                break;
            case R.id.btn_compare:
                compareTemperatures();
                break;
        }
    }

    private void compareTemperatures() {
        Disposable disposable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            String jsonValue = mHttpRequester.get(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GET_TEMPERATURE);
            Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]");
            Matcher matcher = pattern.matcher(jsonValue);

            String result = null;
            if(matcher.find()) {
                result = matcher.group(0);
            }
            Double firstTemperature = Double.parseDouble(result);


            jsonValue = mHttpRequester.get(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GET_TEMPERATURE);
            matcher = pattern.matcher(jsonValue);

            result = null;
            if(matcher.find()) {
                result = matcher.group(0);
            }
            Double secondTemperature = Double.parseDouble(result);

            TemperatureDto firstTempDto = createTempInfo(firstTemperature);
            firstTempDto.setDeviceID(DeviceID.RP1);
            firstTempDto.setDesireableTemperature(25.1);
            TemperatureDto secondTempDto = createTempInfo(secondTemperature);
            secondTempDto.setDeviceID(DeviceID.RP2);
            secondTempDto.setDesireableTemperature(25.1);

            mHttpRequester.post(Constants.TEMPERATURE_POST_URL, mJsonParser.toJson(firstTempDto));
            mHttpRequester.post(Constants.TEMPERATURE_POST_URL, mJsonParser.toJson(secondTempDto));

            if(Math.abs(firstTemperature - secondTemperature) < 1) {
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
            } else if(secondTemperature > firstTemperature) {
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_OFF, "{}");
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_OFF, "{}");
            } else {
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_OFF, "{}");
                mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
                mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_OFF, "{}");
            }
            emitter.onNext("First temperature: " + firstTemperature + "\n"
                    + "Second temperature: " + secondTemperature);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::makeToast, this::showError);
    }

    private TemperatureDto createTempInfo(Double temperature) {
        TemperatureDto temp = new TemperatureDto(temperature);
        return temp;
    }

    private void makeToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void turnLedOff() {
        Disposable disposable = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            Integer integer = 1;
            mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_OFF, "{}");
            mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_OFF, "{}");
            mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_OFF, "{}");
            mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_OFF, "{}");
            emitter.onNext(integer);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::successMessage, this::showError);
    }

    private void turnLedOn() {
        Disposable disposable = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            Integer integer = 1;
            mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
            mHttpRequester.put(Constants.SECOND_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
            mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_RED_LED_ON, "{}");
            mHttpRequester.put(Constants.FIRST_BASE_PREFFIX + Constants.BASE_SUFFIX_GREEN_LED_ON, "{}");
            emitter.onNext(integer);
            emitter.onComplete();
        }).subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::successMessage, this::showError);
    }

    private void showError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void successMessage(Integer integer) {
        Toast.makeText(this, "Bravo!", Toast.LENGTH_SHORT).show();
    }
}
