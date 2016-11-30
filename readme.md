# Proximity & Database
Created by Alex Kraunsøe and David Blum S

## Proximity App

In the proximity app we use google play services to find our geo location on a map.
We just used google maps to display our location as a blue marker, as well as a predefined location (sydney), in order to have some contrast.

The imports we used from Google:

```kotlin

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
```
We overide the onMapReady function and show our location and a marker for Syndey

```kotlin
override fun onMapReady(map: GoogleMap) {

        mMap = map

        mMap!!.setOnMyLocationButtonClickListener(this);

        ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

        mMap!!.setMyLocationEnabled(true)

        val sydney = LatLng(-34.0, 151.0)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Sydney"))
    }
```

We used Google Maps demos to put everything together:
https://github.com/googlemaps/android-samples/tree/master/ApiDemos/app/src/main/java/com/example/mapdemo

## Database App

For the Database App, using SQLite we made a task app that saves tasks to the database and displays them.
We use a content provider to handle the database.

```kotlin
override fun query(uri: Uri?, projection: Array<out String>?,
                       selection: String?, selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor? {
        val db: SQLiteDatabase = mOpenHelper?.readableDatabase as SQLiteDatabase
        val match: Int = sUriMatcher.match(uri)
        var cursor: Cursor?

        when (match) {
            TASK -> {
                cursor = db.query(TaskContract.TaskEntry.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, sortOrder)
            }
            TASK_WITH_ID -> {
                val id: Long = TaskContract.TaskEntry.getIdFromUri(uri as Uri)
                cursor = db.query(TaskContract.TaskEntry.TABLE_NAME, projection,
                        "${TaskContract.TaskEntry._ID} = ?", arrayOf(id.toString()), null, null, sortOrder)
            }
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        cursor?.setNotificationUri(context.contentResolver, uri)
        return cursor
    }
```

We followed this guide on how to make the content provider and its functions:
https://www.sitepoint.com/using-androids-content-providers-manage-app-data/
