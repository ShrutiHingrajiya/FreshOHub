package test.practical.com.androidprojectsstructuredemo.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.practical.com.androidprojectsstructuredemo.R;
import test.practical.com.androidprojectsstructuredemo.RetroFit.ApiInterface;
import test.practical.com.androidprojectsstructuredemo.Utils.GPSTracker;

public class Customer_Edit_Add_Address_Activity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    double currentLat = 0.0;
    double currentLng = 0.0;
    GoogleMap gMap;
    boolean flagFromSearch = false;
    @BindView(R.id.img_drawer_icon)
    ImageView imgDrawerIcon;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.ll_drawer_icon)
    LinearLayout llDrawerIcon;
    @BindView(R.id.edtSelectedAddress)
    EditText edtSelectedAddress;
    @BindView(R.id.lnHome)
    LinearLayout lnHome;
    @BindView(R.id.lnWork)
    LinearLayout lnWork;
    @BindView(R.id.lnOther)
    LinearLayout lnOther;
    @BindView(R.id.txtSaveOrEdit)
    TextView txtSaveOrEdit;
    @BindView(R.id.edtSelectedTitle)
    EditText edtSelectedTitle;
    @BindView(R.id.lnTitle)
    LinearLayout lnTitle;
    @BindView(R.id.ViewHome)
    View ViewHome;
    @BindView(R.id.ViewWork)
    View ViewWork;
    @BindView(R.id.ViewOther)
    View ViewOther;
    @BindView(R.id.lnSelectCurrentLocation)
    LinearLayout lnSelectCurrentLocation;
    private String strSelectedAddress;
    double selectedFinalLat = 0.0;
    double selectedFinalLng = 0.0;
    double selectedLat = 0.0;
    double selectedLng = 0.0;
    String strFromActivity = "", strSelectedType, strAddressId;
    private ApiInterface apiService;
    String hashToken, registerId;
    boolean flagHome1 = true;
    boolean flagWork1 = true;
    boolean flagOther1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__edit__add__address_);
        ButterKnife.bind(this);


        this.setMemoryAllocation();
        this.setListeners();
    }

    private void setMemoryAllocation() {

        Bundle b = getIntent().getExtras();

        if (b != null) {
            strFromActivity = b.getString("ADDRESS");
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAddAddress);

            if (strFromActivity.equals("ADD")) {

                boolean flagHome = b.getBoolean("FLAG_HOME");
                boolean flagWork = b.getBoolean("FLAG_WORK");

                lnHome.setClickable(true);
                lnOther.setClickable(true);
                lnWork.setClickable(true);

                flagHome1 = true;
                flagWork1 = true;
                flagOther1 = true;

                ViewHome.setBackground(getDrawable(R.color.grey_600));
                ViewWork.setBackground(getDrawable(R.color.grey_600));
                ViewOther.setBackground(getDrawable(R.color.grey_600));

                if (flagHome) {
                    lnHome.setClickable(false);
                    lnOther.setClickable(true);
                    flagHome1 = false;

                }

                if (flagWork) {
                    lnWork.setClickable(false);
                    lnOther.setClickable(true);
                    flagWork1 = false;
                }

                fetchCurrentLocation();

            } else if (strFromActivity.equals("EDIT")) {


                flagWork1 = false;
                flagHome1 = false;
                flagOther1 = false;

                edtSelectedAddress.setText("804 Elite Buisness Hub , Sola , Ahmedabad");
                strSelectedType = "Home";


                    lnWork.setClickable(false);
                    lnOther.setClickable(false);
                    lnHome.setClickable(false);
                    lnTitle.setVisibility(View.GONE);

                    selectedLat = Double.parseDouble(String.valueOf(0.0));
                    selectedLng = Double.parseDouble(String.valueOf(0.0));

                    selectedFinalLat = selectedLat;
                    selectedFinalLng = selectedLng;


                    Log.e("selectedLat__", selectedLat + "____HOME");

                    ViewHome.setBackground(getDrawable(R.color.colorPrimary));
                    ViewWork.setBackground(getDrawable(R.color.grey_600));
                    ViewOther.setBackground(getDrawable(R.color.grey_600));

                    if (gMap != null)
                        gMap.clear();

                    flagFromSearch = true;

                    mapFragment.getMapAsync(Customer_Edit_Add_Address_Activity.this);



            }
        }
    }

    private void setListeners() {

        lnSelectCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagFromSearch = false;
                mapFragment.getMapAsync(Customer_Edit_Add_Address_Activity.this);
            }
        });

        edtSelectedAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d_edit_start = new Dialog(Customer_Edit_Add_Address_Activity.this, R.style.AppTheme);
                d_edit_start.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d_edit_start.setContentView(R.layout.dialog_search_location);

                ImageView imgBackDialog = (ImageView) d_edit_start.findViewById(R.id.imgBack);
                TextView txtStartLocation = (TextView) d_edit_start.findViewById(R.id.txtStartLocation);

                TextView txtSavedAddress = (TextView) d_edit_start.findViewById(R.id.txtSavedAddress);
                txtSavedAddress.setVisibility(View.GONE);

                txtStartLocation.setText("Enter your location");

                final PlaceAutocompleteFragment places = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
                places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {

                        selectedLat = place.getLatLng().latitude;
                        selectedLng = place.getLatLng().longitude;

                        selectedFinalLat = place.getLatLng().latitude;
                        selectedFinalLng = place.getLatLng().longitude;

                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(Customer_Edit_Add_Address_Activity.this, Locale.getDefault());

                        try {

                            addresses = geocoder.getFromLocation(selectedFinalLat, selectedFinalLng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName();

                            if (!city.equalsIgnoreCase("ahmedabad")) {
                                Toast.makeText(Customer_Edit_Add_Address_Activity.this,
                                        "We don't provide service on " + city, Toast.LENGTH_SHORT).show();

                                selectedFinalLat = 0.0;
                                selectedFinalLng = 0.0;

                            } else {

                                strSelectedAddress = address + " " + city + " " + country;
                                Log.e("strSelectedAddress_", strSelectedAddress + "..." + address);
                                edtSelectedAddress.setText(strSelectedAddress);
                                if (gMap != null)
                                    gMap.clear();

                                flagFromSearch = true;
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mapFragment.getMapAsync(Customer_Edit_Add_Address_Activity.this);
                        if (places != null)
                            getFragmentManager().beginTransaction().remove(places).commit();
                        d_edit_start.dismiss();

                    }

                    @Override
                    public void onError(Status status) {
                        Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                AutocompleteFilter filter = new AutocompleteFilter.Builder()
                        .setCountry("IN")
                        .build();

                places.setFilter(filter);

                imgBackDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (places != null)
                            getFragmentManager().beginTransaction().remove(places).commit();
                        d_edit_start.dismiss();
                    }
                });

                d_edit_start.show();
            }
        });

        txtSaveOrEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strLocation = edtSelectedAddress.getText().toString();
                Log.e("FINAL_LAT__", selectedFinalLat + "______");
                Log.e("FINAL_LNG__", selectedFinalLng + "______");
                Log.e("FINAL_ADDRESS__", strLocation + "______");

                if (strFromActivity.equals("ADD")) {
                    // call API for Add new Address

                    if (lnTitle.getVisibility() == View.VISIBLE) {

                        strSelectedType = edtSelectedTitle.getText().toString().trim();
                        if (strSelectedType.equals("")) {

                            Toast.makeText(Customer_Edit_Add_Address_Activity.this, "First enter title of your location.", Toast.LENGTH_SHORT).show();
                        } else {
                            callForNewAddress();
                        }

                    } else {
                        strSelectedType = strSelectedType;
                        Log.e("Respoense", strSelectedType + "");

                        if (strSelectedType.equals("") || strSelectedType.equals(null)) {


                            Toast.makeText(Customer_Edit_Add_Address_Activity.this, "First select location Type.", Toast.LENGTH_SHORT).show();
                        } else callForNewAddress();
                    }
                }

                if (strFromActivity.equals("EDIT")) {
                    // call API for edit Address

                    if (lnTitle.getVisibility() == View.VISIBLE) {
                        strSelectedType = edtSelectedTitle.getText().toString().trim();

                        if (strSelectedType.equals("")) {

                            Toast.makeText(Customer_Edit_Add_Address_Activity.this, "First enter title of your location.", Toast.LENGTH_SHORT).show();
                        } else {
                            callForEditAddress();
                        }

                    } else {
                        strSelectedType = strSelectedType;
                        callForEditAddress();
                    }
                }
            }
        });

        lnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagHome1) {
                    strSelectedType = "Home";
                    lnTitle.setVisibility(View.GONE);

                    ViewHome.setBackground(getDrawable(R.color.colorPrimary));
                    ViewWork.setBackground(getDrawable(R.color.grey_600));
                    ViewOther.setBackground(getDrawable(R.color.grey_600));

                } else {

                }
            }
        });

        lnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagWork1) {

                    strSelectedType = "Work";
                    lnTitle.setVisibility(View.GONE);

                    ViewHome.setBackground(getDrawable(R.color.grey_600));
                    ViewWork.setBackground(getDrawable(R.color.colorPrimary));
                    ViewOther.setBackground(getDrawable(R.color.grey_600));

                } else {

                }
            }
        });

        lnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (strFromActivity.equals("EDIT")) {

                } else {
                    lnTitle.setVisibility(View.VISIBLE);

                    ViewHome.setBackground(getDrawable(R.color.grey_600));
                    ViewWork.setBackground(getDrawable(R.color.grey_600));
                    ViewOther.setBackground(getDrawable(R.color.colorPrimary));
                }

            }
        });

        llDrawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void callForEditAddress() {
        Log.e("EDIT____", "EDIT__________");

        if (lnTitle.getVisibility() == View.VISIBLE) {
            strSelectedType = edtSelectedTitle.getText().toString().trim();

        } else {
            strSelectedType = strSelectedType;
        }




    }

    private void callForNewAddress() {

        if (lnTitle.getVisibility() == View.VISIBLE) {
            strSelectedType = edtSelectedTitle.getText().toString().trim();
        } else {
            strSelectedType = strSelectedType;
        }

        Log.e("NEW____", "NEW__________");

    }

    private void fetchCurrentLocation() {

        GPSTracker tracker = new GPSTracker(this);
        if (!tracker.canGetLocation()) {
            tracker.showSettingsAlert();

        } else {
            currentLat = tracker.getLatitude();
            currentLng = tracker.getLongitude();

            Log.e("currentLat___", currentLat + "___");
            flagFromSearch = false;
            mapFragment.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;

        Log.e("FLAG______", flagFromSearch + "______");

        if (!flagFromSearch) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            gMap.setMyLocationEnabled(true);
            gMap.getUiSettings().setMyLocationButtonEnabled(false);


            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLng), 13));

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(Customer_Edit_Add_Address_Activity.this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(currentLat, currentLng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                //   edtSelectedLocation.setText(address + " " + city + " " + country);
/*
                if (!city.equalsIgnoreCase("ahmedabad")) {
                    Toast.makeText(this, "We don't provide service on " + city, Toast.LENGTH_SHORT).show();
                }*/


                if (!city.equalsIgnoreCase("ahmedabad")) {
                    Toast.makeText(Customer_Edit_Add_Address_Activity.this,
                            "We don't provide service on " + city, Toast.LENGTH_SHORT).show();

                    selectedFinalLat = 0.0;
                    selectedFinalLng = 0.0;

                } else {

                    strSelectedAddress = address + " " + city + " " + country;
                    Log.e("strSelectedAddress_", strSelectedAddress + "..." + address);
                    edtSelectedAddress.setText(strSelectedAddress);

                    selectedFinalLat = currentLat;
                    selectedFinalLng = currentLng;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(currentLat, currentLng))
                    .zoom(19)
                    .build();


            gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        if (flagFromSearch) {

            gMap.getUiSettings().setMyLocationButtonEnabled(false);

            gMap.addMarker(new MarkerOptions()
                    .position(new LatLng(selectedLat, selectedLng))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));


            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(selectedLat, selectedLng), 13));


            CameraPosition cameraPosition1 = new CameraPosition.Builder()
                    .target(new LatLng(selectedLat, selectedLng))
                    .zoom(19)
                    /* .bearing(50)
                     .tilt(40)*/
                    .build();
            gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition1));

        }

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                if (ActivityCompat.checkSelfPermission(Customer_Edit_Add_Address_Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Customer_Edit_Add_Address_Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }


                //get latlng at the center by calling
                LatLng midLatLng = googleMap.getCameraPosition().target;
                // Log.e("midLatLng", midLatLng + "...");

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(Customer_Edit_Add_Address_Activity.this, Locale.getDefault());


                try {
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);

                    selectedFinalLat = midLatLng.latitude;
                    selectedFinalLng = midLatLng.longitude;

                    addresses = geocoder.getFromLocation(midLatLng.latitude, midLatLng.longitude, 1);
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    if (!city.equalsIgnoreCase("ahmedabad")) {
                        Toast.makeText(Customer_Edit_Add_Address_Activity.this,
                                "We don't provide service on " + city, Toast.LENGTH_SHORT).show();

                        selectedFinalLat = 0.0;
                        selectedFinalLng = 0.0;
                    } else {
                        strSelectedAddress = address + " " + city + " " + country;
                        Log.e("strSelectedAddress_", strSelectedAddress + "..." + address);
                        edtSelectedAddress.setText(strSelectedAddress);
                        if (gMap != null)
                            gMap.clear();
                        flagFromSearch = true;

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                // googleMap.addMarker(new MarkerOptions().position(midLatLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
