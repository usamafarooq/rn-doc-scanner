package com.adityaarora.liveedgedetection;
 
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.adityaarora.liveedgedetection.DocumentScannerModule;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class DocumentScannerPackage implements ReactPackage {
 
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
 
    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        //this is where you register the module
        modules.add(new DocumentScannerModule(reactContext));
        return modules;
    }
}