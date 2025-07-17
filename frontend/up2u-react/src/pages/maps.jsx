import React from 'react'
import { GoogleMap, useJsApiLoader } from '@react-google-maps/api'
import '../style/Maps.css'

const containerStyle = {
  width: 'auto',
  height: '500px',
}

const center = {
  lat: 1.2970400194243092, 
  lng: 103.8495403289578,
}

export function Maps() {
  const map_api_key = import.meta.env.VITE_MAPS_API;
  const { isLoaded } = useJsApiLoader({
    id: 'google-map-script',
    googleMapsApiKey: map_api_key,
  })

  const [map, setMap] = React.useState(null)

  const onLoad = React.useCallback(function callback(map) {
    // This is just an example of getting and using the map instance!!! don't just blindly copy!
    const bounds = new window.google.maps.LatLngBounds(center)
    map.fitBounds(bounds)

    setMap(map)
  }, [])

  const onUnmount = React.useCallback(function callback(map) {
    setMap(null)
  }, [])

  return isLoaded ? (
    <div className="map-body">
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={center}
        zoom={10}
        onLoad={onLoad}
        onUnmount={onUnmount}
        className="map"
      >
        {/* Child components, such as markers, info windows, etc. */}
        <></>
      </GoogleMap>
    </div>
  ) : (
    <></>
  )
}