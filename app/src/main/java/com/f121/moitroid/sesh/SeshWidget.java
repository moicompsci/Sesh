package com.f121.moitroid.sesh;

import android.Manifest;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.RemoteViews;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Implementation of App Widget functionality.
 */
public class SeshWidget extends AppWidgetProvider implements OnMapReadyCallback {


    MapView mapView;
    GoogleMap map;
    Context globalContext;  // Global context to be updated consistently.
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sesh_widget);

        Intent hiInt = new Intent(context, SeshWidget.class);
        hiInt.setAction("Broadcast Sesh");
        PendingIntent hiPendingIntent = PendingIntent.getBroadcast(context, 0, hiInt,0);
        views.setOnClickPendingIntent(R.id.button_sesh, hiPendingIntent);

        Intent settingsInt = new Intent(context, MainActivity.class);
        PendingIntent settingsPendingIntent = PendingIntent.getActivity(context, 0, settingsInt,0);
        views.setOnClickPendingIntent(R.id.button_settings, settingsPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sesh_widget);
        ComponentName widget = new ComponentName(context, SeshWidget.class);

        if(intent.getAction().equals("Broadcast Sesh")){
            views.setTextViewText(R.id.groupname, "sending");
        }

        manager.updateAppWidget(widget, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        globalContext = context;
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        // Gets the MapView from the XML layout and creates it
        globalContext = context;
//        mapView = (MapView) mapView.findViewById(R.id.mapView);
//        mapView.getMapAsync(this);

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//        map.getUiSettings().setMyLocationButtonEnabled(false);
//        if (ActivityCompat.checkSelfPermission(globalContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(globalContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        map.setMyLocationEnabled(true);
//        mapView.getMapAsync(this);
//       /*
//       //in old Api Needs to call MapsInitializer before doing any CameraUpdateFactory call
//        try {
//            MapsInitializer.initialize(this.getActivity());
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//       */
//        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));




    }
}

