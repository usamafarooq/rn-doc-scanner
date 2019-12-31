package com.adityaarora.liveedgedetection;
 
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.adityaarora.liveedgedetection.activity.ScanActivity;
import com.adityaarora.liveedgedetection.constants.ScanConstants;
import com.adityaarora.liveedgedetection.util.ScanUtils;


 
public class DocumentScannerModule extends ReactContextBaseJavaModule {
 
    public DocumentScannerModule(ReactApplicationContext reactContext) {
        super(reactContext); //required by React Native
    }
 
    @Override
    //getName is required to define the name of the module represented in JavaScript
    public String getName() { 
        return "DocumentScanner";
    }
 
    // @ReactMethod
    // public void sayHi(Callback errorCallback, Callback successCallback) {
    //     try {
    //         System.out.println("Greetings from Java");
    //         successCallback.invoke("Callback : Greetings from Java");
    //     } catch (IllegalViewOperationException e) {
    //         errorCallback.invoke(e.getMessage());
    //     }
    // }

    private static final int REQUEST_CODE = 101;
    private ImageView scannedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannedImageView = findViewById(R.id.scanned_image);
        startScan();
    }

    @ReactMethod
    private void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                if(null != data && null != data.getExtras()) {
                    String filePath = data.getExtras().getString(ScanConstants.SCANNED_RESULT);
                    Bitmap baseBitmap = ScanUtils.decodeBitmapFromFile(filePath, ScanConstants.IMAGE_NAME);
                    scannedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    scannedImageView.setImageBitmap(baseBitmap);
                }
            } else if(resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }
    }
}