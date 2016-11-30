# Proximity & Database
Created by Alex Kraunsøe and David Blum S

## Proximity App

In the proximity app we use google play services to find our geo location on a map.
We just used google maps to display our location as a blue marker, as well as a predefined location (sydney), in order to have some contrast.

```kotlin

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

override fun onMapReady(map: GoogleMap) {

        mMap = map

        mMap!!.setOnMyLocationButtonClickListener(this);

        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

        mMap!!.setMyLocationEnabled(true)

        val sydney = LatLng(-34.0, 151.0)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Sydney"))
    }
```


## Database App
something something, 3 activities, that do something something
